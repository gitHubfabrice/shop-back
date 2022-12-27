package com.fatechnologies.usecase;

/**
 * <p>Interface de base pour les cas d'utilisations.</p>
 *
 * @param <T> La commande
 * @author Christian Amani 2022-01-17
 */
public interface UseCase<T> {

  /**
   * <p>Effectue l'action du cas d'utilisation.</p>
   *
   * @param command Commande
   */
  void perform(T command);
}
