package com.fatechnologies.command;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.security.command.Command;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
public record OrderCommand (
        Long id,
        UUID userId,
        Long clientId,
        String reference,
        LocalDate date,
        List<ArticleDto> articles
) implements Command {}
