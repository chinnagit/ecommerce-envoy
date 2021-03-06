/**
 * 
 */
package com.ecommerce.products.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.ecommerce.products.repository.ProductRepository;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.products.model.Product;

//import javax.ws.rs.GET;
//import javax.ws.rs.Path;

/**
 * @author chinnb
 *
 */

@RestController
public class ProductsApi {

//    // @Autowired
//    // private UserRepository repository;
//    @Autowired
//    ProductRepository productRepo;
//
//    @Autowired
//    CompanyRepository companyRepo;

    private List<Product> products;

    protected Logger logger = Logger.getLogger(ProductsApi.class.getName());

    Boolean isElastic = false;
    @Autowired
    ProductRepository productRepository ;

    public ProductsApi() {
        ResourceBundle bundle = ResourceBundle.getBundle("config"); // creates bundle from file
                                                                    // `config.properties`
        // List<String> keys = (List<String>) bundle.getKeys(); //gets the list of keys found in the
        // bundle (file)
        isElastic = new Boolean(bundle.getString("isElastic")); // gets the value for the key
                                                                // `mykey`

        products = new ArrayList<>();
        products.add(new Product("apple", 10, "iphone 3", "phones", "old model phone from Apple", new BigDecimal(299.99)));
        products.add(new Product("apple",20, "iphone 8", "phones", "new model phone from Apple", new BigDecimal(599.99)));
        products.add(new Product("Nintendo", 30, "switch", "gaming console", "Gaming console from Nintndo", new BigDecimal(399.99)));
        products.add(new Product("Microsoft",40, "Xbox", "gaming console", "Gaming console from microsoft", new BigDecimal(499.99)));
    }

//    /**
//     * Using SpEL for conditional caching - only cache method executions when
//     * the name is equal to 'Joshua'
//     */
//
//    @RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
//    @Cacheable(value = "productByName", condition = "'Nikejacket'.equals(#name)")
//    @ApiOperation(value = "Find pet by ID", notes = "Returns a pet when ID < 10.  ID > 10 or nonintegers will simulate API error conditions", response = Product.class
//    // , authorizations = @Authorization(value = "api_key"
//    )
//    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid name supplied"), @ApiResponse(code = 404, message = "Product not found") })
//    public List<Product> findByName(@PathVariable("name") String name) {
//        logger.info(String.format("Product.findByName(%s)", name));
////        if (isElastic) {
////            return productRepo.findByName(name);
////        } else {
//            return products.stream().filter(it -> it.getName().equals(name)).collect(Collectors.toList());
////        }
//    }
//
//    /**
//     * Using SpEL for conditional caching - only cache method executions when
//     * the category is equal to 'men'
//     */
//    @RequestMapping(value = "/products/category/{category}", method = RequestMethod.GET)
//    @Cacheable(value = "productByCategory", condition = "'men'.equals(#category)")
//    public List<Product> findByCatefory(@PathVariable("category") String category) {
//        logger.info(String.format("Product.findByCategory(%s)", category));
////        if (isElastic) {
////            return productRepo.findByCategory(category);
////        } else {
//            return products.stream().filter(it -> it.getCategory().equals(category)).collect(Collectors.toList());
////        }
//    }




    @RequestMapping(value = "/products", method = RequestMethod.GET)
    // @Cacheable(value = "product", key = "#name", unless = "#result == null")
    // @Cacheable("allproducts")
    public List<Product> findAll() {
        System.out.println("############################ Product.findAll() ###################################");
        logger.info("############################ Product.findAll() ################################### " + isElastic);
        if (isElastic)
            try {
                products = productRepository.getAllProducts();
            }catch (Exception exp){
                exp.printStackTrace();
            }
        else{
            return products;
        }

        return products;
    }

    /**
     * Using SpEL for conditional caching - only cache method executions when
     * the name is equal to 'Joshua'
     */

    @RequestMapping(value = "/products/{name}", method = RequestMethod.GET)
    public List<Product> findByName(@PathVariable("name") String name) {
        logger.info(String.format("Product.findByName(%s)", name));
//        if (isElastic) {
//            return productRepo.findByName(name);
//        } else {
//            return products.stream().filter(it -> it.getName().equals(name)).collect(Collectors.toList());
//        }
        try {
            products = productRepository.getProductByName(name);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return products;
    }


//
//    @RequestMapping(value = "/products/", method = RequestMethod.PUT)
//    public void updateProduct(@RequestBody Product product) {
//        System.out.println("updating product");
////        if (isElastic) {
////            List<Product> productOrgnl = productRepo.findByName(product.getName());
////            productOrgnl.get(0).setPrice(product.getPrice());
////            productRepo.save(product);
////        }
//
//    }
//
//    // @RequestMapping(value = "/products/", method = RequestMethod.POST)
//    // public void addProduct(@RequestBody Product product) {
//    // System.out.println("adding product");
//    // if (isElastic) {
//    // productRepo.save(product);
//    // }
//    //
//    // }
//
//    @RequestMapping(value = "/products/", method = RequestMethod.POST)
//    public void addProducts(@RequestBody List<Product> products) {
//        System.out.println("adding product");
////        if (isElastic) {
////            productRepo.save(products);
////        }
//
//    }
//
//    @RequestMapping(value = "/products/{productName}", method = RequestMethod.DELETE)
//    public void deleteProduct(@PathVariable("productName") String productName) throws Exception {
//        System.out.println("deleting product");
////        if (isElastic) {
////            List<Product> products = null;
////            try {
////                products = productRepo.findByName(productName);
////            } catch (Exception exxp) {
////                throw new Exception("Product with name: " + productName + " not found");
////            }
////            if (products != null) {
////                for (Product product : products) {
////                    productRepo.delete(product);
////                }
////
////            } else {
////                System.out.println(" product with name: " + productName + " not found");
////            }
////        }
//
//    }


}
