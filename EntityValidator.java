package com.app.backend;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

//Credit: https://www.youtube.com/watch?v=YZqXP6yT3To, Clinton Bush
public class EntityValidator {
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	public static <T> T validateAndDoOrThrow(final T entity, final Function<T, T> action) {
		final List<String> violations = validate(entity);
		if(violations.isEmpty()) {
			return action.apply(entity);
		}
		final String errorMessage =  String.format(
				"Validation failed for entity of type %s:\n%s", 
				entity.getClass().getSimpleName(),
				String.join(System.lineSeparator(), violations)
		);
		throw new ValidationException(errorMessage);
	}
	
	public static <T> List<String> validate(final T entity) {
		final Set<ConstraintViolation<T>> errors = validator.validate(entity);
		return errors.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toUnmodifiableList());
	}
}
