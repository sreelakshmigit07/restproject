package com.ust.pms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ust.pms.model.Product;
import com.ust.pms.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	public List<Product> getProducts(){ // get all products
		return (List<Product>) productRepository.findAll();
	}
	

	public Product getProduct(Integer productId) {  // get product by productId
		Optional<Product> product = productRepository.findById(productId);
		return product.get();
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public void deleteProduct(Integer productId) {
		productRepository.deleteById(productId);
		
	}

	public void updateProduct(Product product) {  // update or save Product
		productRepository.save(product);
	}
}
