package com.ecommerce.products.repository;

import com.ecommerce.products.Config;
import com.ecommerce.products.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: bala_chinna
 * @Date: 7/19/19, Fri
 **/
@Component
public class ProductRepository {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ObjectMapper objectMapper;

    public List<Product> getAllProducts() throws Exception{
        List<Product> products ;
        SearchRequest searchRequest = new SearchRequest(Config.INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.size(2);
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest);
        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());

        return massageProducts(searchHits);
//        return new ArrayList<>();

    }

    public List<Product> getProductByName(String name) throws Exception {
//        SearchRequest searchRequest = new SearchRequest(Config.INDEX);
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchQuery("product_name", name));
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse response = restHighLevelClient.search(searchRequest);
//        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
//        return massageProducts(searchHits);
        return new ArrayList<>();
    }

    private List<Product> massageProducts(List<SearchHit> searchHits) throws Exception{
        List<Product> products = new ArrayList<>();
        for(int i=0; i<searchHits.size(); i++) {
            SearchHit hit = searchHits.get(i);
            String hitDoc = hit.getSourceAsString();
            System.out.println("hitDoc: "+hitDoc);
            Product product = objectMapper.readValue(hitDoc, Product.class);
            products.add(product);
            System.out.println("Product category: "+product.getCategory()
                    +" Manufacturer: "+product.getManufacturer()+" name: "+product.getProductName());
        }
        return products;
    }

    private String prettyPrintJsonString(JsonNode jsonNode) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(jsonNode.toString(), Object.class);

//            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            return mapper.writeValueAsString(json);
        } catch (Exception e) {
            return "Sorry, pretty print didn't work";
        }
    }
}
