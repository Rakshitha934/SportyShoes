package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAO {
	
	//autowire - automatically trigger the object creation (no need to write ProductRepository repo=new ProductRepository())
	@Autowired
	ProductRepository repo;
	
//insert
	public Product insert(Product product) {
		return repo.save(product);
	}

	
//retrieve	
	public List<Product> getall(){
		return repo.findAll();
	}

}
