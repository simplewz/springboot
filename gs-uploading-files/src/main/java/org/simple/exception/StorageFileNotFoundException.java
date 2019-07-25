package org.simple.exception;

public class StorageFileNotFoundException extends RuntimeException {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 4879871019637720235L;

	public StorageFileNotFoundException(String message) {
	        super(message);
	    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
