package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.Command;

/**
 * <p>Exécuteur des cas d'utilisations.</p>
 *
 * @param <T> La commande
 * @author Christian Amani 2022-01-17
 */
public class UseCaseExecutor<T extends Command> {

  /**
   * <p>Exécute la commande avec le cas d'utilisation.</p>
   *
   * @param useCase Cas d'utilisation
   * @param commande Commande a exécuté
   */
  public void execute(final UseCase<T> useCase, final T commande) {
    useCase.perform(commande);
  }
}
