/**
 * 
 */
package com.ecommerce.entity;

import java.io.Serializable;


/**
 * @author chinnb
 *
 */

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	

	Integer id;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
    private String name;
	
    private String description;
	
	public Product() {
		
	}
	
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}  
}
