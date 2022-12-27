package com.fatechnologies.command;

import com.fatechnologies.domaine.dto.FileDto;
import com.fatechnologies.security.command.Command;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleCommand(
        int id, int categoryId, String reference, String label,
        LocalDateTime createdAt, int quantity, double price, boolean published,
        String description, Set<FileDto> files) implements Command {}
