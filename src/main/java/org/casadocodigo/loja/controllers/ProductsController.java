package org.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.casadocodigo.loja.daos.ProductDAO;
import org.casadocodigo.loja.enums.BookType;
import org.casadocodigo.loja.models.Product;
import org.casadocodigo.loja.validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}

	@RequestMapping("/form")
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid Product product, RedirectAttributes redirectAttributes) {

		productDAO.save(product);
		redirectAttributes.addFlashAttribute("Sucesso",
				"Produto cadastrado com sucesso");

		return "redirect:produtos";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		
		return modelAndView;
	}

}
