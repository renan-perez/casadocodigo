package org.casadocodigo.loja.validation;

import org.casadocodigo.loja.enums.ValidationsTypes;
import org.casadocodigo.loja.models.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				ValidationsTypes.FIELD_REQUIRED.getTipoValidacao());

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				ValidationsTypes.FIELD_REQUIRED.getTipoValidacao());

		if (product.getPages() == 0) {
			errors.rejectValue("pages",
					ValidationsTypes.FIELD_REQUIRED.getTipoValidacao());
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

}
