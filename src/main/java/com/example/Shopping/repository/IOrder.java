package com.example.Shopping.repository;

import java.util.Collection;

import com.example.Shopping.exception.ProductNotFoundException;
import com.example.Shopping.model.Product;

public interface IOrder {
	boolean addProduct(Product p);
	boolean removeProduct(String pid) throws ProductNotFoundException;
	Collection getCartDetails();
	Product getProductFromCart(String pid) throws ProductNotFoundException;
	int productCount();
	  double getCartPrice();
}
