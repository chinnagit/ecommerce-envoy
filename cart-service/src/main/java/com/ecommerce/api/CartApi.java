/**
 * 
 */
package com.ecommerce.api;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;


/**
 * @author chinnb
 *
 */

@RestController
public class CartApi {

	
	protected Logger logger = Logger.getLogger(CartApi.class.getName());
	Boolean isMongoDb = false;
	List<Cart> carts;
	List<User> users;

	
	public CartApi() {
//		ResourceBundle bundle = ResourceBundle.getBundle("config"); //creates bundle from file `config.properties`
		
		carts = new ArrayList<>();
		
		carts.add(new Cart(10, 100, new BigDecimal(299.99), 1));
		carts.add(new Cart(20, 200, new BigDecimal(599.99), 2));
		carts.add(new Cart(30, 300, new BigDecimal(399.99), 3));
		carts.add(new Cart(40, 400, new BigDecimal(499.99), 4));

		users = new ArrayList<>();
		users.add(new User("bala", "1234"));
		users.add(new User("kavitha", "abc123"));
		users.add(new User("kanish", "xyz123"));
	}

//	@PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
	@RequestMapping("/cart")
	public List<Cart> findAll() {
		logger.info("############################ Cart.findAll() ################################### ");

		return carts;

	}
	
	@RequestMapping("/cart/user/{user_name}")
	public User findByUserName(@PathVariable("user_name") String userName) {
		logger.info("############################ Cart.find by user id() ################################### ");
		userName = "".equals(userName) ? "bala" : userName;
		return users.get(0);
	}

	@RequestMapping("/cart/users/")
	public String findByUsers(HttpServletRequest request) {
		logger.info("############################ Cart get all users ################################### ");
//		userName = "".equals(userName) ? "bala" : userName;
		//simply invoke user service
		System.out.println("Header info: "+request);

		Map<String, Object> returnValue = new HashMap<>();

		Enumeration<String> hearderNames = request.getHeaderNames();
		while(hearderNames.hasMoreElements()) {
			String headerName = hearderNames.nextElement();
			System.out.println("header name "+headerName);
		}

//		return "not implemented";

		return OtherApiInvoker.getUser(request);

	}
	
}

