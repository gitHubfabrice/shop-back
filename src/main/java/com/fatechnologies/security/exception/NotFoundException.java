package com.fatechnologies.security.exception;

/**
 * @author Christian Amani
 */
public class NotFoundException extends RuntimeException{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public NotFoundException(String message) {
    super(message);
  }
}
