package com.fatechnologies.usecase;

/**
 * <p>Interface de base pour les cas d'utilisations retournant une réponse.</p>
 *
 * @param <T> La commande
 * @param <R> La réponse
 * @author Christian Amani
 */
public interface UseCaseQuery<T, R> {

  /**
   * <p>Effectue l'action du cas d'utilisation.</p>
   *
   * @param command Commande
   */
  R perform(T command);
}
