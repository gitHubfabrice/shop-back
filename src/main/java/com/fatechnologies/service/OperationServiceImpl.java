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
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.exception.Exception;
import com.fatechnologies.security.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

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

    public OperationServiceImpl(OperationRepository operationRepository, ArticleRepository articleRepository, AccountBankRepository accountBankRepository, BalanceRepository balanceRepository, ProspectRepository prospectRepository) {
        this.operationRepository = operationRepository;
        this.operationMapper = OperationMapper.INSTANCE;
        this.articleRepository = articleRepository;
        this.articleMapper = ArticleMapper.INSTANCE;
        this.accountBankRepository = accountBankRepository;
        this.balanceRepository = balanceRepository;
        this.prospectRepository = prospectRepository;
    }


    @Override
    public OperationDto getById(UUID id) {
        var operation = operationRepository.findById(id).orElseThrow(BasicException::new);

        var dto = operationMapper.modelToDto(operation);
        dto.setAmountTemp(dto.getAmount());
        dto.setAmountBeneficeTemp(dto.getAmountBenefice());
        getAllArticle(operation, dto);

        return dto;
    }

    private void getAllArticle(OperationEntity operation, OperationDto dto) {
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
        double benefice = 0;
        List<ArticleOperation> artLiv = new ArrayList<>();
        var operation = operationMapper.dtoToModel(dto);
        operation.setReference(operation.getReference() != null ? operation.getReference() : idGen());
        amount = getAmount(dto, operation, amount, artLiv);

        debitAccount(dto, operation, amount);

        operation.getArticles().clear();
        operation.getArticles().addAll(artLiv);

        operation.setAmount(amount);
        operation.setCreatedAt(LocalDateTime.now());

        operationRepository.saveAndFlush(operation);
    }

    private void debitAccount(OperationDto dto, OperationEntity operation, double amount) {
        if (operation.isDebtor()) {
            var accountBank = accountBankRepository.findOneByReferenceIgnoreCase(Constants.ACCOUNT_PRINCIPAL).orElseThrow(BasicException::new);
            accountBank.deposit(dto.getAmountTemp());
            accountBank.withdrawal(amount);
            accountBankRepository.saveAndFlush(accountBank);
        }
    }

    private double getAmount(OperationDto dto, OperationEntity operation, double amount, List<ArticleOperation> artLiv) {
        for (ArticleDto art : dto.getArticles()) {
            var articleOptional = this.articleRepository.findById(art.getId());
            if (articleOptional.isPresent()) {
                var article = articleOptional.get();

                ArticleOperation ao = new ArticleOperation();
                ao.setArticle(article);
                ao.setOperation(operation);
                ao.setType(operation.getType());
                ao.setQuantity(art.getQuantityArtDel());
                ao.setPrice(art.getPriceArtDel());
                amount += art.getPriceArtDel() * art.getQuantityArtDel();

                //mise à jour du stock
                article.setQuantityOld(article.getQuantity());
                article.less(art.getQuantityTemp());
                article.more(art.getQuantityArtDel());

                articleRepository.saveAndFlush(article);
                artLiv.add(ao);
            }
        }
        return amount;
    }

    @Override
    public void outStock(OperationDto dto) {
        double amount = 0;
        double benefice = 0;
        List<ArticleOperation> artLiv = new ArrayList<>();
        var operation = operationMapper.dtoToModel(dto);
        var client = prospectRepository.findById(dto.getClientId()).orElseThrow(BasicException::new);
        operation.setClient(client);
        var clientBalance = balanceRepository.findById(client.getBalance().getId()).orElseThrow(BasicException::new);
        var userBalance = balanceRepository.findOneBalanceByUserId(dto.getUserId()).orElseThrow(BasicException::new);
        operation.setReference(operation.getReference() != null ? operation.getReference() : idGen());

        for (ArticleDto art : dto.getArticles()) {
            Optional<ArticleEntity> articleOptional = this.articleRepository.findById(art.getId());
            if (articleOptional.isPresent()) {

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
                benefice += art.getQuantityArtDel() * (art.getPriceArtDel() - articleOptional.map(ArticleEntity::getPrice).orElse(0.0));

                //mise à jour du stock
                articleOptional.get().setQuantityOld(articleOptional.get().getQuantity());
                articleOptional.get().more(art.getQuantityTemp());
                articleOptional.get().less(art.getQuantityArtDel());
                articleRepository.saveAndFlush(articleOptional.get());

                artLiv.add(ao);
            }
        }

        Result result = new Result(amount, benefice);

        operation.getArticles().clear();
        operation.getArticles().addAll(artLiv);

        operation.setAmount(result.amount());
        operation.setAmountBenefice(Math.max(0, result.benefice()));

        //mise à jour du compte client
        clientBalance.withdrawal(dto.getAmountTemp());
        clientBalance.deposit(result.amount());

        //mise à jour du compte opérateur
        userBalance.withdrawal(dto.getAmountTemp());
        userBalance.deposit(result.amount());

        //mise à jour du profit
        var accountBenefice = accountBankRepository.findOneByReferenceIgnoreCase(Constants.ACCOUNT_BENEFICE).orElseThrow(BasicException::new);
        accountBenefice.withdrawal(dto.getAmountBeneficeTemp());
        accountBenefice.deposit(Math.max(0, result.benefice()));

        operation.setCreatedAt(LocalDateTime.now());
        operation.setDebtor(true);
        balanceRepository.saveAndFlush(userBalance);
        balanceRepository.saveAndFlush(clientBalance);
        operationRepository.saveAndFlush(operation);
    }

    private record Result(double amount, double benefice) {
    }

    @Override
    public void delete(UUID id) {
        operationRepository.deleteById(id);
    }

    @Override
    public List<OperationDto> getAllInStockHistory() {
        var operations = operationRepository.findAllByTypeOrderByCreatedAtDesc(TypeOperation.ADD);
        return getOperationDtos(operations);
    }

    @Override
    public List<OperationDto> getAllOutStockHistory() {
        var operations = operationRepository.findAllByTypeOrderByCreatedAtDesc(TypeOperation.OUT);
        return getOperationDtos(operations);
    }

    @Override
    public Map<Object, Object> getBenefice() {
        return operationRepository.getBenefice();
    }

    @Override
    public Page<OperationDto> getAllOutStockHistory(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<OperationEntity> page = operationRepository.findAllByTypeOrderByCreatedAtDesc(TypeOperation.OUT, pageable);
        // Use map to transform each OperationEntity to OperationDto
        List<OperationDto> dtos = page.getContent().stream()
                .map(operationMapper::modelToDto)
                .peek(dto -> {
                    dto.setAmountTemp(dto.getAmount());
                    getAllArticle(getElementById(page.getContent(), dto.getId()), dto);
                })
                .toList();
        return new PageImpl<>(dtos, pageable, page.getTotalElements());

    }

    @NotNull
    private List<OperationDto> getOperationDtos(List<OperationEntity> operations) {
        List<OperationDto> dtos = new ArrayList<>();
        for (OperationEntity op : operations) {
            OperationDto dto = operationMapper.modelToDto(op);
            dto.setAmountTemp(dto.getAmount());
            getAllArticle(op, dto);
            dtos.add(dto);
        }
        return dtos;
    }

    private int idGen() {
        if (operationRepository.nbre() == 0) return 1;
        else return operationRepository.max() + 1;
    }

    @Scheduled(cron = "0 0 0 * * *")
    protected void closeOperation() {
        var operations = operationRepository.findAllByStatusAndDebtor(false, true);
        operations.forEach(operation -> {
            operation.setStatus(true);
            operationRepository.save(operation);
        });
    }

    OperationEntity getElementById(List<OperationEntity> liste, UUID searchId) {
        for (OperationEntity objet : liste) {
            if (objet.getId() == searchId) {
                return objet;
            }
        }
        return null;
    }

}
