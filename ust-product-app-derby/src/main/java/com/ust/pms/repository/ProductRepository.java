package com.ust.pms.repository;

import org.springframework.data.repository.CrudRepository;

import com.ust.pms.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer> {

	// will do the CRUD operations on Product
}
