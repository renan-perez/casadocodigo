package org.casadocodigo.loja.controllers;

import org.casadocodigo.loja.daos.ProductDAO;
import org.casadocodigo.loja.enums.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

}
