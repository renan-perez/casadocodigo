package org.casadocodigo.loja.validation;

import org.casadocodigo.loja.models.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				"field.required.product.title");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"field.required.product.description");

		if (product.getPages() == 0) {
			errors.rejectValue("pages", "typeMismatch.product.pages");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

}
