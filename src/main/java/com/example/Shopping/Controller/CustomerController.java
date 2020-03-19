package com.example.Shopping.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.exception.ResourceNotFundException;
import com.example.Shopping.model.Customer;
import com.example.Shopping.model.Product;
import com.example.Shopping.repository.CustomerRepository;
import com.example.Shopping.repository.ProductRepository;



@RestController
public class CustomerController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customer/product/{pid}")
	public List<Customer> allCustomersByProductId(@PathVariable("pid") int pid){
		Product p = productRepository.getOne(pid);
		if(p ==null)
			throw new ResourceNotFundException(pid);
		
		List<Customer> list = customerRepository.findByProductId(pid);
		return list;
	}
	
	@PostMapping("/customer/product/{cid}/{pid}")
	public void customerBuysProduct(@PathVariable("cid") long cid,
			@PathVariable("pid") int pid){
		Customer c= customerRepository.getOne(cid);
				Product p=productRepository.getOne(pid);
				List<Product> list=c.getProduct();
				list.add(p);
				c.setProduct(list);
				customerRepository.save(c);
		customerRepository.saveInCustomerProduct(cid,pid);
	}
	
	@PostMapping("/customer")
	public Customer postCustomer(@RequestBody Customer c){
		return customerRepository.save(c);
	}
}

