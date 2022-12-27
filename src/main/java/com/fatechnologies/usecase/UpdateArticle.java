package com.fatechnologies.usecase;

import com.fatechnologies.command.ArticleCommand;
import com.fatechnologies.domaine.dto.FileDto;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.security.utils.Constants;
import com.fatechnologies.service.ArticleService;
import com.fatechnologies.service.FileService;

import java.io.IOException;

public class UpdateArticle implements UseCase<ArticleCommand> {
    private final ArticleService articleService;
    private final FileService fileService;
    private final ArticleMapper articleMapper;

    public UpdateArticle(ArticleService articleService, FileService fileService) {
        this.articleService = articleService;
        this.fileService = fileService;
        this.articleMapper = ArticleMapper.INSTANCE;
    }

    @Override
    public void perform(ArticleCommand command) {
        var dto = articleMapper.commandUpToDto(command);
        dto.setLabel(Constants.toUpperCase(dto.getLabel()));
        for (FileDto file : dto.getFiles()) {
            if (file.getId()!= null) {
                var fileEntity = fileService.getById(file.getId());
                try {
                    if (fileEntity.getFilename() != null)
                        fileService.delete(fileEntity.getId(), fileEntity.getUrl(), fileEntity.getFilename());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fileService.saveImage(file, dto.getReference());
        }
        articleService.save(dto);
    }

}
