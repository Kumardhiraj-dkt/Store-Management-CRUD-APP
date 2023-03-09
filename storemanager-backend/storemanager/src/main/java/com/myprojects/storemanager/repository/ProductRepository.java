package com.myprojects.storemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myprojects.storemanager.products.product;

@Repository
public interface ProductRepository extends JpaRepository<product, Long>{
	
}
