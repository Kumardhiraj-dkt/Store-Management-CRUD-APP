package com.myprojects.storemanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.storemanager.products.product;
import com.myprojects.storemanager.repository.ProductRepository;
import com.myprojects.storemanager.exception.ResoruceNotFoundException;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

	@GetMapping("/products")
	public List<product> getAllProducts(){
		return this.productRepository.findAll();
	}
	
	@PostMapping("/products")
	public product createProduct(@RequestBody product prod)
	{
		return productRepository.save(prod);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<product> getProductById(@PathVariable Long id) throws ResoruceNotFoundException
	{
		product prod=productRepository.findById(id).orElseThrow(()->new ResoruceNotFoundException("Product doesnt exist with id :" +id));
		return ResponseEntity.ok(prod);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<product> updateProduct(@PathVariable Long id,@RequestBody product productDetails) throws ResoruceNotFoundException
	{
		product prod=productRepository.findById(id).orElseThrow(()->new ResoruceNotFoundException("Product doesnt exist with id :" +id));
		prod.setName(productDetails.getName());
		prod.setCompany(productDetails.getCompany());
		prod.setPrice(productDetails.getPrice());
		
		product uProduct = productRepository.save(prod);
		return ResponseEntity.ok(uProduct);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id) throws ResoruceNotFoundException
	{
		product prod=productRepository.findById(id).orElseThrow(()->new ResoruceNotFoundException("Product doesnt exist with id :" +id));
		productRepository.delete(prod);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
}
