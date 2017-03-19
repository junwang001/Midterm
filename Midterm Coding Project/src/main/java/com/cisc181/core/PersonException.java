package com.cisc181.core;

public class PersonException extends Exception {

	private static final long serialVersionUID = 1L;

	private Person person;

	public PersonException(Person person) {
		super();
		this.person = person;
	}
	
}
