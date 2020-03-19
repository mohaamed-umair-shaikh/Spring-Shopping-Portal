package com.example.Shopping.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.exception.ProductNotFoundException;
import com.example.Shopping.exception.ResourceNotFundException;
import com.example.Shopping.model.Category;
import com.example.Shopping.model.Customer;
import com.example.Shopping.model.Product;
import com.example.Shopping.repository.CategoryRepository;
import com.example.Shopping.repository.CustomerRepository;
import com.example.Shopping.repository.ProductRepository;



@RestController
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private  CustomerRepository customerReository; 
	
	@GetMapping("/product/{cid}")
	public List<Product> getproduct(@PathVariable("cid") int cid){
		
		List<Product> list = productRepository.findByCategoryId(cid);
		return list;
	}
	
	@PostMapping("/product/{cid}/{desc}/{price}/{name}")
	public Product postproduct(@PathVariable("cid") long cid,
			@PathVariable("desc") String desc,
			@PathVariable("name") String name,
			@PathVariable("price") int price){
		Category c = categoryRepository.getOne(cid);
		
		Product product = new Product();
		product.setPrice(price);
		product.setDescription(desc);
		product.setCategory(c);
		product.setName(name);
		
		return productRepository.save(product);
	}
	@PostMapping("/product/{cid}")
	public Product addProduct(@PathVariable("cid") long cid,
			@RequestBody Product product){
		Category category = categoryRepository.getOne(cid);
		if(category ==null)
			throw new ResourceNotFundException(cid);
		product.setCategory(category);
		return productRepository.save(product);
		
	}
	
	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable("id") int id){
		Product product = productRepository.getOne(id);
		if(product == null)
			throw new ResourceNotFundException(id);
		
		productRepository.deleteById(id);
	}
	
	@GetMapping("/product/search/{title}")
	public List<Product> searchProduct(@PathVariable("title") String title) throws ProductNotFoundException{
		List<Product> product;
		try {
		 product = productRepository.searchByNameJpql(title);
		}
		catch(Exception e) {
			throw new ProductNotFoundException(title);
		}
		return product;
	}
	@PostMapping("/product/custproduc/{cusid}")
	public void getAllProductsByCustomerId(@PathVariable("cusid")long cusid){
		List<Customer> c=customerReository.findAll();
	
			
			customerReository.findById(cusid);
		}
	}


