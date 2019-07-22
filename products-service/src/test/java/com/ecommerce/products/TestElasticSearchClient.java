package com.ecommerce.products;

import com.ecommerce.products.utils.ElasticSearchClient;


/**
 * @Author: bala_chinna
 * @Date: 7/20/19, Sat
 **/
public class TestElasticSearchClient {
    public static void main(String[] args) throws Exception{
        try {
            ElasticSearchClient.makeConnection();
//            getEcommerceDoc();
            ElasticSearchClient.writeToIndex();

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            ElasticSearchClient.closeConnection();
        }
    }


//    public static void connectToMongoDb() throws Exception{
//
//        try {
//
//            /**** Connect to MongoDB ****/
//            // Since 2.10.0, uses MongoClient
//            MongoClient mongo = new MongoClient("localhost", 27017);
//
//            /**** Get database ****/
//            // if database doesn't exists, MongoDB will create it for you
//            DB db = mongo.getDB("testdb");
//
//            /**** Done ****/
//            System.out.println("Done");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}
