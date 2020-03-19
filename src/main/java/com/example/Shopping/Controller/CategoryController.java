package com.example.Shopping.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.exception.ResourceNotFundException;
import com.example.Shopping.model.Category;
import com.example.Shopping.repository.CategoryRepository;
import com.example.Shopping.repository.ProductRepository;



@RestController
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	ProductRepository productRepository;
	@GetMapping("/category")
	public List<Category> getCategory(){
		List<Category> list = categoryRepository.findAll();
		ArrayList<Category> list_with_count=new ArrayList<>();
		for(Category c: list )
		{
		int count=productRepository.findByCategoryId(c.getId()).size();
		c.setCount(count);
			list_with_count.add(c);
		}
		return list_with_count;
	}
	
	@PostMapping("/category/{name}")
	public Category postCategory(@PathVariable("name") String name){
		Category category = new Category();
		category.setName(name);
		Category c = categoryRepository.save(category);
		category = null;
		return c;
	}
	
	@PutMapping("/category/{id}/{name}")
	public Category putCategory(@PathVariable("id") long id,
			@PathVariable("name") String name){
		Category c = categoryRepository.getOne(id);
		if(c==null){
			throw new ResourceNotFundException(id);
		}
		c.setName(name);
		return categoryRepository.save(c);
		
	}
	@DeleteMapping("/category/{id}")
	public void deleteCategory(@PathVariable("id") long id){
		try{
		categoryRepository.deleteById(id);
		}
		catch(Exception e){
			throw new ResourceNotFundException(id);
		}
	}
}

