package com.tirmizee;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.tirmizee.jpa.entities.Category;
import com.tirmizee.jpa.entities.Product;
import com.tirmizee.jpa.repositories.CategoryRepository;
import com.tirmizee.jpa.repositories.ProductRepository;

@Configuration
public class Runner implements CommandLineRunner {

	@Autowired
	ApplicationContext context;
	
	@Override
	public void run(String... args) throws Exception {
		CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
		ProductRepository productRepository = context.getBean(ProductRepository.class);
		
		Category category = categoryRepository.getByCode("C0002");
		
		Product product = new Product();
		product.setCode("P0003");
		product.setName("กางเกง");
		product.setQuatity(10);
		product.setPrice(BigDecimal.valueOf(1200));
		product.setCreateDate(new Date());
		product.setCategory(category);
//		productRepository.save(product);
	}

}
