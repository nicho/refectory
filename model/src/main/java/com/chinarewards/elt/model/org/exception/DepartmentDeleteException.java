package com.chinarewards.elt.model.org.exception;

/**
 * Error appear when delete a Department.
 * 
 * @author yanxin
 * @since 1.0
 */
public class DepartmentDeleteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8597913068971921543L;

	public DepartmentDeleteException() {

	}

	public DepartmentDeleteException(String msg) {
		super(msg);
	}
}
