package com.fatechnologies.interactor;

import com.fatechnologies.command.ArticleCommand;

public interface ArticleInteractor {
    void create(ArticleCommand command);
    void update(ArticleCommand command);
}
