package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.OperationDto;
import com.fatechnologies.domaine.dto.TypeOperation;
import com.fatechnologies.domaine.entity.ArticleEntity;
import com.fatechnologies.domaine.entity.ArticleOperation;
import com.fatechnologies.domaine.entity.OperationEntity;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.OperationMapper;
import com.fatechnologies.repository.*;
import com.fatechnologies.security.adapter.repository.jpa.UserJpa;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.exception.Exception;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	private final OperationRepository operationRepository;
	private final OperationMapper operationMapper;
	private final ArticleRepository articleRepository;
	private final ArticleMapper articleMapper;
	private final AccountBankRepository accountBankRepository;
	private final BalanceRepository balanceRepository;
	private final ProspectRepository prospectRepository;
	private final UserJpa userJpa;

	public OperationServiceImpl(OperationRepository operationRepository,
								ArticleRepository articleRepository,
								AccountBankRepository accountBankRepository, BalanceRepository balanceRepository, ProspectRepository prospectRepository,
								UserJpa userJpa) {
		this.operationRepository = operationRepository;
		this.operationMapper = OperationMapper.INSTANCE;
		this.articleRepository = articleRepository;
		this.accountBankRepository = accountBankRepository;
		this.balanceRepository = balanceRepository;
		this.prospectRepository = prospectRepository;
		this.userJpa = userJpa;
		this.articleMapper = ArticleMapper.INSTANCE;
	}


	@Override
	public OperationDto getById(UUID id) {
		var operation = operationRepository.findById(id).orElseThrow(BasicException::new);

		var dto = operationMapper.modelToDto(operation);
		dto.setAmountTemp(dto.getAmount());
		setArticles(operation, dto);

		return dto;
	}

	private void setArticles(OperationEntity operation, OperationDto dto) {
		for (ArticleOperation ao : operation.getArticles()) {

			var art = articleRepository.findById(ao.getArticle().getId()).orElseThrow(BasicException::new);
			var artDto = articleMapper.modelToDto(art);
			artDto.setQuantityTemp(ao.getQuantity());
			artDto.setQuantityArtDel(ao.getQuantity());
			artDto.setPriceArtDel(ao.getPrice());
			dto.getArticles().add(artDto);
		}
	}

	@Override
	public void inStock(OperationDto dto) {
		double amount = 0;
		List<ArticleOperation> artLiv = new ArrayList<>();
		var operation = operationMapper.dtoToModel(dto);
		operation.setReference(operation.getReference() != null ? operation.getReference() :  String.valueOf(idGen()));
		for (ArticleDto art : dto.getArticles()) {
			var articleOptional = this.articleRepository.findById(art.getId());
			if(articleOptional.isPresent()){
				ArticleOperation ao = new ArticleOperation();
				ao.setArticle(articleOptional.get());
				ao.setOperation(operation);
				ao.setType(operation.getType());
				ao.setQuantity(art.getQuantityArtDel());
				ao.setPrice(art.getPriceArtDel());
				amount += art.getPriceArtDel() * art.getQuantityArtDel();

				//mise à jour du stock
				articleOptional.get().setQuantityOld(articleOptional.get().getQuantity());
				articleOptional.get().less(art.getQuantityTemp());
				articleOptional.get().more(art.getQuantityArtDel());

				articleRepository.saveAndFlush(articleOptional.get());
				artLiv.add(ao);
			}
		}

		operation.getArticles().clear();
		operation.getArticles().addAll(artLiv);
		operation.setAmount(amount);
		operation.setCreatedAt(LocalDateTime.now());

		operationRepository.saveAndFlush(operation);
	}

	@Override
	public void outStock(OperationDto dto) {
		double amount = 0;
		List<ArticleOperation> artLiv = new ArrayList<>();
		var operation = operationMapper.dtoToModel(dto);
		var client = prospectRepository.findById(dto.getClientId()).orElseThrow(BasicException::new);
		operation.setClient(client);
		var clientBalance = balanceRepository.findById(client.getBalance().getId()).orElseThrow(BasicException::new);
		var userBalance   = balanceRepository.findOneBalanceByUserId(dto.getUserId()).orElseThrow(BasicException::new);
		operation.setReference(operation.getReference() != null ? operation.getReference() :  String.valueOf(idGen()));

		for (ArticleDto art : dto.getArticles()) {
			Optional<ArticleEntity> articleOptional = this.articleRepository.findById(art.getId());
			if(articleOptional.isPresent()){

				//vérifions si l'article est disponible en stock
				if (articleOptional.get().getQuantity() + art.getQuantityTemp() < art.getQuantityArtDel()) {
					throw new Exception("Vérifiez votre stock de marchandise");
				}

				ArticleOperation ao = new ArticleOperation();
				ao.setArticle(articleOptional.get());
				ao.setOperation(operation);
				ao.setType(operation.getType());
				ao.setQuantity(art.getQuantityArtDel());
				ao.setPrice(art.getPriceArtDel());
				amount += art.getPriceArtDel() * art.getQuantityArtDel();

				//mise à jour du stock
				articleOptional.get().setQuantityOld(articleOptional.get().getQuantity());
				articleOptional.get().more(art.getQuantityTemp());
				articleOptional.get().less(art.getQuantityArtDel());
				articleRepository.saveAndFlush(articleOptional.get());

				artLiv.add(ao);
			}
		}

		operation.getArticles().clear();
		operation.getArticles().addAll(artLiv);
		operation.setAmount(amount);

		//mise à jour de la caisse
		clientBalance.withdrawal(dto.getAmountTemp());
		clientBalance.deposit(amount);
		userBalance.withdrawal(dto.getAmountTemp());
		userBalance.deposit(amount);
		operation.setCreatedAt(LocalDateTime.now());
		balanceRepository.saveAndFlush(userBalance);
		balanceRepository.saveAndFlush(clientBalance);
		operationRepository.saveAndFlush(operation);
	}

	@Override
	public void delete(UUID id) {
		operationRepository.deleteById(id);
	}

	@Override
	public List<OperationDto> getAllInStockHistory() {
		var operations = operationRepository.findAllByType(TypeOperation.ADD);
		return getOperationDtos(operations);
	}

	@Override
	public List<OperationDto> getAllOutStockHistory() {
		var operations = operationRepository.findAllByType(TypeOperation.OUT);
		return getOperationDtos(operations);
	}

	@NotNull
	private List<OperationDto> getOperationDtos(List<OperationEntity> operations) {
		List<OperationDto> dtos = new ArrayList<>();
		for (var op : operations) {
			OperationDto dto = operationMapper.modelToDto(op);
			dto.setAmountTemp(dto.getAmount());
			setArticles(op, dto);
			dtos.add(dto);
		}
		dtos.sort(Comparator.comparing(OperationDto::getCreatedAt).reversed());
		return dtos;
	}

	public int idGen(){
		var nbre = operationRepository.nbre();
		if (nbre == 0)
			return 1;
		else return operationRepository.max() + 1;
	}
	@Scheduled(cron="0 0 0 * * *")
	public void closeOperation(){
		var operations = operationRepository.findAllByStatus(false);
		operations.forEach(operation -> {
			operation.setStatus(true);
			operationRepository.save(operation);
		});
	}


}
