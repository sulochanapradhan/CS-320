package com.app.backend;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class ContactService {
	/**
	 * Singleton instance of ContactService
	 */
	private static ContactService INSTANCE;

	protected Map<String, Contact> repository;

	private ContactService() {
		repository = new ConcurrentHashMap<>();
	}

	public static void resetInstance() {
		INSTANCE = null;
	}
	
	public static synchronized ContactService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ContactService();
		}
		return INSTANCE;
	}
	
	public Map<String, Contact> getRepository() {
		return repository;
	}

	/**
	 * Adds new contact.
	 * 
	 * @param contact
	 * @throws IllegalArgumentException if contact id already exists
	 */
	public Contact add(final Contact contact) {
		Objects.requireNonNull(contact);
		return EntityValidator.validateAndDoOrThrow(contact, t -> {
			if (repository.containsKey(contact.id())) {
				throw new IllegalArgumentException(String.format("A contact with the ID [%s] already exists"));
			}
			return repository.put(contact.id(), contact);
		});
	}

	/**
	 * Deletes existing contact of given id
	 * 
	 * @param id
	 * @throws IllegalArgumentException if contact id doesn't exist
	 */
	public Contact delete(final String id) {
		Objects.requireNonNull(id);
		if (!repository.containsKey(id)) {
			throw new IllegalArgumentException(String.format("A contact with the ID [%s] does not exist"));
		}
		return repository.remove(id);
	}

	/**
	 * Updates existing contact if contact of given id exists.
	 * 
	 * @param contact
	 * @throws IllegalArgumentException if contact of given id doesn't exist
	 */
	public Contact update(final Contact contact) {
		Objects.requireNonNull(contact);
		return EntityValidator.validateAndDoOrThrow(contact, t -> {
			if (!repository.containsKey(contact.id())) {
				throw new IllegalArgumentException(String.format("A contact with the ID [%s] doesn't exist"));
			}
			return repository.replace(contact.id(), contact);
		});
	}
}