package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.Command;

/**
 * <p>Exécuteur des cas d'utilisations qui retourne une réponse.</p>
 *
 * @param <T> La commande
 * @author Christian Amani 2022-02-03
 */
public class UseCaseQueryExecutor<T extends Command, R> {

  public R execute(final UseCaseQuery<T, R> useCase, final T commande) {
    return useCase.perform(commande);
  }
}
