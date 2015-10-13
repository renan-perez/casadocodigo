package org.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import org.casadocodigo.loja.daos.ProductDAO;
import org.casadocodigo.loja.enums.BookType;
import org.casadocodigo.loja.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

	@RequestMapping("/form")
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(Product product, RedirectAttributes redirectAttributes) {

		productDAO.save(product);
		redirectAttributes.addFlashAttribute("Sucesso",
				"Produto cadastrado com sucesso");

		return "redirect:produtos";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		
		List<Product> products = productDAO.list();
		for (Product p : products) {
			System.out.println(p.getTitle());
		}
		
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", products);
		
		return modelAndView;
	}

}
