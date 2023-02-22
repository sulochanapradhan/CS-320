package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.backend.Contact;
import com.app.backend.ContactService;


public class ContactServiceTest {
	private ContactService service;
	
	final Contact contact = Contact.create("PERSON001", "Sulochana", "Pradhan", "1234567890", "Alexandria, VA");
	final Contact updatedContact = Contact.create("PERSON001", "Sulo", "Pradhana", "9876543210", "Woodbridge, VA");

	@BeforeEach
	void init() {
		ContactService.resetInstance();
		service = ContactService.getInstance();
	}

	@Test
	void add_Success() {
		service.add(contact);
		assertTrue(service.getRepository().containsKey("PERSON001"));
	}

	@Test
	void add_ExistingId() {
		service.add(contact);
		assertThrows(IllegalArgumentException.class, () -> service.add(updatedContact));
	}

	@Test
	void delete_Success() {
		service.add(contact);
		service.delete("PERSON001");
		assertFalse(service.getRepository().containsKey("PERSON001"));
	}

	@Test
	void delete_NonExistingId() {
		assertThrows(IllegalArgumentException.class, () -> service.delete("PERSON001"));
	}
	
	@Test
	void update_Success() {
		service.add(contact);
		service.update(updatedContact);
		
		Contact tempContact  = service.getRepository().get("PERSON001");
		
		assertTrue(tempContact.id().equals("PERSON001"));
		assertTrue(tempContact.firstName().equals("Sulo"));
		assertTrue(tempContact.lastName().equals("Pradhana"));
		assertTrue(tempContact.phoneNumber().equals("9876543210"));
		assertTrue(tempContact.address().equals("Woodbridge, VA"));
	}

	@Test
	void update_NonExistingId() {
		assertThrows(IllegalArgumentException.class, () -> service.update(updatedContact));
	}
}
