package com.leaderboard.api.exception;

import java.io.Serial;

public class InvalidScoreException  extends RuntimeException{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	
	public InvalidScoreException() {
        super("Invalid Score!");
    }

    public InvalidScoreException(final String message) {
        super(message);
    } 

}