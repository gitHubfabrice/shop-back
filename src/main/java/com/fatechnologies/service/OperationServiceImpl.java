package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.OperationDto;
import com.fatechnologies.domaine.entity.ArticleEntity;
import com.fatechnologies.domaine.entity.ArticleOperation;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.OperationMapper;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.repository.ArticleRepository;
import com.fatechnologies.repository.BalanceRepository;
import com.fatechnologies.repository.OperationRepository;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.exception.Exception;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private OperationMapper operationMapper;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private AccountBankRepository accountBankRepository;
	@Autowired
	private BalanceRepository balanceRepository;


	@Override
	public void inStock(OperationDto dto) {
		double amount = 0;
		List<ArticleOperation> artLiv = new ArrayList<>();
		var operation = operationMapper.dtoToModel(dto);
		var accountBank =  accountBankRepository.findOneByReference(dto.getAccountBankReference()).orElseThrow(BasicException::new);

		for (ArticleDto art : dto.getArticles()) {
			Optional<ArticleEntity> articleOptional = this.articleRepository.findById(art.getId());
			if(articleOptional.isPresent()){

				ArticleOperation ao = new ArticleOperation();
				ao.setArticle(articleOptional.get());
				ao.setOperation(operation);
				ao.setType(operation.getType());
				ao.setQuantity(art.getQuantityArtDel());
				ao.setPrice(art.getPriceArtDel());
				amount += art.getPriceArtDel() * art.getQuantityArtDel();

				//mise à jour du stock
				articleOptional.get().less(art.getQuantityTemp());
				articleOptional.get().more(art.getQuantityArtDel());

				articleRepository.saveAndFlush(articleOptional.get());
				artLiv.add(ao);
			}
		}

		operation.getArticles().clear();
		operation.getArticles().addAll(artLiv);
		operation.setAmount(amount);

		//mise à jour de la caisse
		accountBank.deposit(dto.getAmountTemp());
		accountBank.withdrawal(amount);

		accountBankRepository.save(accountBank);
		operationRepository.saveAndFlush(operation);
	}

	@Override
	public void outStock(OperationDto dto) {
		double amount = 0;
		List<ArticleOperation> artLiv = new ArrayList<>();
		var operation = operationMapper.dtoToModel(dto);
		var accountBank =  accountBankRepository.findOneByReference(dto.getAccountBankReference()).orElseThrow(BasicException::new);
		var clientBalance = balanceRepository.findById(dto.getClientBalanceId()).orElseThrow(BasicException::new);

		for (ArticleDto art : dto.getArticles()) {
			Optional<ArticleEntity> articleOptional = this.articleRepository.findById(art.getId());
			if(articleOptional.isPresent()){

				//vérifions si l'article est disponible en stock
				if (articleOptional.get().getQuantity() < art.getQuantityArtDel() + art.getQuantityTemp()) {
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
		accountBank.deposit(dto.getAmountTemp());
		accountBank.withdrawal(amount);
		accountBankRepository.save(accountBank);
		operationRepository.saveAndFlush(operation);
	}

	@Override
	public void delete(UUID id) {
		operationRepository.deleteById(id);
	}

	@Override
	public List<OperationDto> getAll() {
		var operations = operationRepository.findAll();
		List<OperationDto> dtos = new ArrayList<>();
		for (var op : operations) {
			OperationDto dto = operationMapper.modelToDto(op);
			dto.setAmountTemp(dto.getAmount());
			for (ArticleOperation ao : op.getArticles()) {
				ArticleDto art = articleMapper.modelToDto(ao.getArticle());
				art.setQuantityTemp(ao.getQuantity());
				art.setQuantityArtDel(ao.getQuantity());
				art.setPriceArtDel(ao.getPrice());
				dto.getArticles().add(art);
			}
			dtos.add(dto);

		}
		return dtos;
	}
}
