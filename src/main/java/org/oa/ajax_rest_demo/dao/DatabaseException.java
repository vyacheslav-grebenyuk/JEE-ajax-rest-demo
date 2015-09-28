package org.oa.ajax_rest_demo.dao;

public class DatabaseException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2662469265209647890L;

	public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

}
