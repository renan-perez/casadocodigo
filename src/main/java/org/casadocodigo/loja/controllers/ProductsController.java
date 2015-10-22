package org.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.casadocodigo.loja.daos.ProductDAO;
import org.casadocodigo.loja.enums.BookType;
import org.casadocodigo.loja.models.Product;
import org.com.casadocodigo.loja.infra.FileSarver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileSarver fileSaver;
	
	public static String FILE_UPLOADED_FOLDER = "uploaded-file";

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * binder.setValidator(new ProductValidator()); }
	 */

	@RequestMapping("/form")
	public ModelAndView form(Product product) {

		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return form(product);
		}
		
		String webPath = fileSaver.writeAmazonS3(FILE_UPLOADED_FOLDER, summary);
		product.setSummaryPath(webPath);
		
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("Sucesso", "Produto cadastrado com sucesso");

		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());

		return modelAndView;
	}

}
