package com.ust.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ust.pms.model.Product;
import com.ust.pms.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	/**
	 * Method to receive Product from web and save it.
	 */
	//@RequestMapping(method = RequestMethod.PUT, value = "product")
	@PostMapping
	public String saveProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return "Saved product..."+product;
	}

	//@RequestMapping(method = RequestMethod.DELETE, value= "product")
	@DeleteMapping(path = "/{productId}")
	public String deleteProduct(@PathVariable("productId") Integer productId) {
		productService.deleteProduct(productId);
		return "Deleted product...";
	}

	//@RequestMapping("/updateProduct")
	@PutMapping
	public String updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		return "Updated product..."+product;
	}
	
	/**
	 * Method to return all the products
	 */
	@GetMapping
	public List<Product> getProduct() { 

		return productService.getProducts();

	}
	
	/**
	 * Method to receive productId from web and return the product with that productId
	 */
	@GetMapping(path = "/{productId}")
	public Product getProducts(@PathVariable("productId")Integer productId) { 
		System.out.println("Getting Details of Product : "+ productId);
		return productService.getProduct(productId);

	}


}
