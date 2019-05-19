/**
 * 
 */
package com.ecommerce.entity;

import java.io.Serializable;

/**
 * @author chinnb
 *
 */

public class User implements Serializable{
	
		private static final long serialVersionUID = 1L;

//		Integer id;
//
//		public Integer getId() {
//			return this.id;
//		}
//
//		public void setId(Integer id) {
//			this.id = id;
//		}
//
//	    private String name;
//
//		private Set<Cart> cart = new HashSet<Cart>(
//				0);
//
//		public User() {
//
//		}
//
//		public User(String name) {
//			this.name = name;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public Set<Cart> getCart() {
//			return this.cart;
//		}
//
//		public void setCart(Set<Cart> cart) {
//			 this.cart = cart;
//		}

	String name;
	String password;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
