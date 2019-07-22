package com.ecommerce.products;

import com.ecommerce.products.utils.ElasticSearchClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
//        loadProductCatalog();
    }

        private static void loadProductCatalog() throws Exception{
        try {
            ElasticSearchClient.makeConnection();
            ElasticSearchClient.writeToIndex();

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            ElasticSearchClient.closeConnection();
        }
    }
}

