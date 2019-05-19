/**
 * 
 */
package com.ecommerce.user.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chinnb
 *
 */
public class User {
	String name;
	String password;

	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("password") String password) {
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
