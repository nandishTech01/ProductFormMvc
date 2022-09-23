package mvccrud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.view.RedirectView;

import mvccrud.Dao.ProductDao;
import mvccrud.model.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
	public String home(Model m) {
		List<Product> products = productDao.getProducts();
		m.addAttribute("product",products);
		return "index";
	}

	// show add product form
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title", "Add product");
		return "add_product_form";
	}

	// handle add product

	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request) {
		System.out.println(product);
		productDao.createProduct(product);
		RedirectView r = new RedirectView();
		r.setUrl(request.getContextPath() + "/");
		return r;
	}

	
	
	//delete handler
	
	@RequestMapping(value="/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int ProductId ,HttpServletRequest request) {
		productDao.deleteProduct(ProductId);
		RedirectView r = new RedirectView();
		System.out.println("deleted....");
		r.setUrl(request.getContextPath() + "/");
		return r;
	}
	
	@RequestMapping("/update/{productId}")
	public String updateform(@PathVariable("productId") int pid,Model m) {
		Product product = productDao.getProduct(pid);
		m.addAttribute("product", product);
		return "update_form";
	}
}
