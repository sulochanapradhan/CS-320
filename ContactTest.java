package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.app.backend.Contact;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ContactTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	final String validId = "PERSON001";
	final String invalidId = "PERSON000001";

	final String validFirstName = "Sulochana";
	final String invalidFirstName = "Constantine";

	final String validLastName = "Pradhan";
	final String invalidLastName = "Fitzpatricks";

	final String validPhoneNumber = "1234567890";
	final String invalidPhoneNumberShort = "123";
	final String invalidPhoneNumberLong = "1234567890123";
	final String invalidPhoneNumberAlpha = "1234a56789";

	final String validAddress = "Alexandria, Virginia";
	final String invalidAddress = "P.O. Box 100000 8562 Fusce Rd. Frederick Nebraska 112233";

	@Test
	void testValidateId_Valid() {
		assertTrue(validator
				.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, validAddress))
				.isEmpty());
	}

	@Test
	void testValidateId_Null() {
		assertEquals("Contact id is a required field",
				validator.validate(Contact.create(null, validFirstName, validLastName, validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}
	
	@Test
	void testValidateId_Blank() {
		assertEquals("Contact id is a required field",
				validator.validate(Contact.create("", validFirstName, validLastName, validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidateId_TooLong() {
		assertEquals("Contact id cannot be longer than $10 characters", validator
				.validate(Contact.create(invalidId, validFirstName, validLastName, validPhoneNumber, validAddress))
				.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidateFirstName_Valid() {
		assertTrue(validator
				.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, validAddress))
				.isEmpty());
	}

	@Test
	void testValidateFirstName_Null() {
		assertEquals("First name is a required field",
				validator.validate(Contact.create(validId, null, validLastName, validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}
	
	@Test
	void testValidateFirstName_Blank() {
		assertEquals("First name is a required field",
				validator.validate(Contact.create(validId, "", validLastName, validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidateFirstName_TooLong() {
		assertEquals("First name cannot be longer than $10 characters",
				validator.validate(Contact.create(validId, invalidFirstName, validLastName, validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidateLastName_Valid() {
		assertTrue(validator
				.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, validAddress))
				.isEmpty());
	}

	@Test
	void testValidateLastName_Null() {
		assertEquals("Last name is a required field",
				validator.validate(Contact.create(validId, validFirstName, null, validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}
	
	@Test
	void testValidateLastName_Blank() {
		assertEquals("Last name is a required field",
				validator.validate(Contact.create(validId, validFirstName, "", validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidateLastName_TooLong() {
		assertEquals("Last name cannot be longer than $10 characters",
				validator.validate(Contact.create(validId, validFirstName, invalidLastName, validPhoneNumber, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidatePhoneNumber_Valid() {
		assertTrue(validator
				.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, validAddress))
				.isEmpty());
	}

	@Test
	void testValidatePhoneNumber_Null() {
		assertEquals("Phone number is a required field",
				validator.validate(Contact.create(validId, validFirstName, validLastName, null, validAddress))
						.stream().findFirst().get().getMessage());
	}
	
	@Test
	void testValidatePhoneNumber_Blank() {
		assertEquals("Phone number must be of $10 digits",
				validator.validate(Contact.create(validId, validFirstName, validLastName, "", validAddress))
						.stream().findFirst().get().getMessage());
	}
	

	@Test
	void testValidatePhoneNumber_TooLong() {
		assertEquals("Phone number must be of $10 digits",
				validator.validate(Contact.create(validId, validFirstName, validLastName, invalidPhoneNumberLong, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidatePhoneNumber_TooShort() {
		assertEquals("Phone number must be of $10 digits",
				validator.validate(Contact.create(validId, validFirstName, validLastName, invalidPhoneNumberShort, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidatePhoneNumber_InvalidCharacters() {
		assertEquals("Phone number must be of $10 digits",
				validator.validate(Contact.create(validId, validFirstName, validLastName, invalidPhoneNumberAlpha, validAddress))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidateAddress_Valid() {
		assertTrue(validator
				.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, validAddress))
				.isEmpty());
	}

	@Test
	void testValidateAddress_Null() {
		assertEquals("Address is a required field",
				validator.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, null))
						.stream().findFirst().get().getMessage());
	}
	
	@Test
	void testValidateAddress_Blank() {
		assertEquals("Address is a required field",
				validator.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, null))
						.stream().findFirst().get().getMessage());
	}

	@Test
	void testValidateAddress_TooLong() {
		assertEquals("Address cannot be longer than $30 characters",
				validator.validate(Contact.create(validId, validFirstName, validLastName, validPhoneNumber, invalidAddress))
						.stream().findFirst().get().getMessage());
	}

}