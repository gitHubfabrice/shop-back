package com.fatechnologies.usecase;

import com.fatechnologies.command.ArticleCommand;
import com.fatechnologies.domaine.dto.FileDto;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.security.utils.Constants;
import com.fatechnologies.service.ArticleService;
import com.fatechnologies.service.FileService;

import java.time.LocalDateTime;

public class CreateArticle implements UseCase<ArticleCommand> {

    private final ArticleService articleService;

    private final FileService fileService;

    private final ArticleMapper articleMapper;


    public CreateArticle(ArticleService articleService, FileService fileService) {
        this.articleService = articleService;
        this.fileService = fileService;
        this.articleMapper = ArticleMapper.INSTANCE;
    }

    @Override
    public void perform(ArticleCommand command) {
        var dto = articleMapper.commandAddToDto(command);
        dto.setReference("ART-ELED000" + articleService.idGen());
        dto.setLabel(Constants.toUpperCase(dto.getLabel()));
        dto.setCreatedAt(LocalDateTime.now());
        for (FileDto file : dto.getFiles()) {
           fileService.saveImage(file, dto.getReference());
        }
        articleService.save(dto);
    }
}
