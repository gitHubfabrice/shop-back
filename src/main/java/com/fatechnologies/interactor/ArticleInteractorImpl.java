package com.fatechnologies.interactor;

import com.fatechnologies.command.ArticleCommand;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.security.usecase.UseCaseExecutor;
import com.fatechnologies.service.ArticleService;
import com.fatechnologies.service.FileService;
import com.fatechnologies.usecase.CreateArticle;
import com.fatechnologies.usecase.UpdateArticle;
import org.springframework.stereotype.Service;

@Service
public class ArticleInteractorImpl implements  ArticleInteractor{
    private final UseCase<ArticleCommand> create;
    private final UseCase<ArticleCommand> update;

    public ArticleInteractorImpl(ArticleService articleService, FileService fileService) {
        this.create = new CreateArticle(articleService, fileService);
        this.update = new UpdateArticle(articleService, fileService);
    }

    @Override
    public void create(ArticleCommand command) {
        new UseCaseExecutor<ArticleCommand>().execute(create, command);
    }

    @Override
    public void update(ArticleCommand command) {
        new UseCaseExecutor<ArticleCommand>().execute(update, command);
    }
}
