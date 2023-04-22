package com.fatechnologies.interactor;

import com.fatechnologies.command.ArticleCommand;

public interface ArticleInteract {
    void create(ArticleCommand command);
    void update(ArticleCommand command);
}
