package com.app.backend;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Contact(
		@NotBlank(message = "Contact id is a required field")
		@Size(max = 10, message = "Contact id cannot be longer than ${max} characters") 
		String id, 
		
		@NotBlank(message = "First name is a required field")
		@Size(max = 10, message = "First name cannot be longer than ${max} characters") 
		String firstName, 
		
		@NotBlank(message = "Last name is a required field")
		@Size(max = 10, message = "Last name cannot be longer than ${max} characters") 
		String lastName, 
		
		@NotNull(message = "Phone number is a required field")
		@Pattern(regexp =  "\\d{10}$" , message = "Phone number must be of $10 digits")
		String phoneNumber, 
		
		@NotBlank(message = "Address is a required field")
		@Size(max = 30, message = "Address cannot be longer than ${max} characters") 
		String address) {

	public static Contact create(String id, String firstName, String lastName, String phoneNumber, String address) {
		return new Contact(id, firstName, lastName, phoneNumber, address);
	}

}
