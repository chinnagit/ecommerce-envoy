package com.ecommerce.products;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.ResourceBundle;

/**
 * @Author: bala_chinna
 * @Date: 7/19/19, Fri
 **/
@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.ecommerce.products.repository")
//@ComponentScan(basePackages = { "com.ecommerce.products" })
public class Config {

//    @Value("${elasticsearch.home:/usr/local/Cellar/elasticsearch/5.6.0}")
//    private String elasticsearchHome;

//    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;

    ResourceBundle bundle = ResourceBundle.getBundle("config");

    private static final String HOST = "elastic_search";
    private static final int PORT_ONE = 9200;
    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "http";

    private static RestHighLevelClient restHighLevelClient;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static final String INDEX = "ecommerce";
    private static final String TYPE = "catalog";




    @Bean
    public RestHighLevelClient makeConnection() throws Exception {

        if(restHighLevelClient == null) {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(HOST, PORT_ONE, SCHEME)
//                            ,new HttpHost(HOST, PORT_TWO, SCHEME)
                    ));
        }


        System.out.println("Established connection to elastic search");
        return restHighLevelClient;
    }


}
