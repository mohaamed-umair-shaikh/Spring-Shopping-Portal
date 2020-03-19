package com.example.Shopping.Controller;

import java.util.ArrayList;

import java.util.List;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.exception.ResourceNotFundException;
import com.example.Shopping.model.Category;
import com.example.Shopping.model.Customer;
import com.example.Shopping.model.Product;
import com.example.Shopping.repository.CustomerRepository;
import com.example.Shopping.repository.ProductRepository;



@RestController
public class LoginController {

	 
    @Autowired
    private ProductRepository product;
	@Autowired
	 private CustomerRepository customerrepository;
	@Valid Customer customer = new Customer();
	HttpSession session;
	static ArrayList<Product>  cartList =new ArrayList<>();
	ArrayList<Category> list =new ArrayList<>();
	@PostMapping("/signup/{name}/{username}/{password}")
	public Customer signup(
			@PathVariable("name") String name,
			@PathVariable("username") String username,
			@PathVariable("password") String password,Model model){
		
		customer.setName(name);
		customer.setUsername(username);
		customer.setPassword(password);
		return customerrepository.save(customer);
	}

	@PostMapping("/login/{username}/{password}")
	public String signup(@PathVariable("username") String username,
			@PathVariable("password") String password,Model model ){

		List <Customer> c=customerrepository.findAll();
		
		for( Customer s :c ) {
			if(username.equalsIgnoreCase(s.getUsername())&& password.equalsIgnoreCase(s.getPassword())) {
				session.setAttribute("name", s.getUsername());
				return "true";
			}
		
		}
		return "fail";
		}
	@PostMapping("/login/cart/{pid}")
	public ArrayList<Product> addtocart(@PathVariable("pid") int pid) {
		List<Product> list=product.findAllById(pid);
		//session.setAttribute("list_product", list);
		for(Product p:list) {
			if(p.getId() == pid) {
				cartList.add(p);
				break;
				}
			//session.setAttribute("cartList", cartList);
		}
		return cartList;
	}
	@GetMapping("/login/cart")
	public ArrayList<Object> checkout(){
		ArrayList<Object> mo=new ArrayList<Object>();
		int i=0;
		for(Product p:cartList) {
			mo.add(p.getName());
			mo.add(p.getDescription());
			mo.add(p.getPrice());
			
			i=i+p.getPrice();
		}
		mo.add(i);
		
		return mo;
	
	}
	@DeleteMapping("/login/cart/{pid}")
	public ArrayList<Product> delete(@PathVariable("pid")int pid) {
		try {
		for(Product p:cartList) {
			if(pid==p.getId()) {
			cartList.remove(pid);
			break;
					
		}}}
		catch(Exception e) {
			throw new ResourceNotFundException(pid);
		}
		return cartList;
	}
}
	
	
	
