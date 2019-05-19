package com.ecommerce.api;

import com.ecommerce.entity.Product;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: bala_chinna
 * @Date: 4/28/19, Sun
 **/
public class OtherApiInvoker {
    protected static Logger logger = Logger.getLogger(OtherApiInvoker.class.getName());
    private enum RequestTypesEnum {
        // GET, POST;
        GET("GET"),
        POST("POST"),
        DELETE("DELETE");

        String type;

        RequestTypesEnum(String type) {
            this.type = type;
        }

    }
//    private static final String REST_URI = "http://service_cart_envoy:9788/user/";

    private static final String REST_URI = "http://localhost:9788/user/";

    Product product = new Product();

    private static Client client = ClientBuilder.newClient();

    public static void addProduct(Product product) {
        try {

            URL url = new URL(REST_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(product);
            System.out.println(jsonInString);

            OutputStream os = conn.getOutputStream();
            os.write(jsonInString.getBytes());
            os.flush();

            System.out.println("conn.getResponseCode(): " + conn.getResponseCode());
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static String getUser(HttpServletRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        String output = "";

        for(int i=0; i<1; i++){
            output = genericInvocation(REST_URI, RequestTypesEnum.GET, null, request);
            try {

                // Pretty print
                String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(output);
                System.out.println(prettyStaff1);
                logger.info(prettyStaff1);

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return output;
    }

    public static void getProduct() {

        ObjectMapper mapper = new ObjectMapper();
        String output = genericInvocation(REST_URI, RequestTypesEnum.GET, null, null);
        try {
            // Convert JSON string to Object
            Product[] products = mapper.readValue(output, Product[].class);

            // Pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
            System.out.println(prettyStaff1);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(String productName) {

        String output = genericInvocation(REST_URI + productName, RequestTypesEnum.DELETE, null, null);
    }

    public static void addProducts(List<Product> products) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(products);
            System.out.println(jsonInString);

            String output = genericInvocation(REST_URI, RequestTypesEnum.POST, jsonInString, null);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private static String genericInvocation(String uri, RequestTypesEnum type, String payLoad, HttpServletRequest request) {
//        uri = "http://service_a_envoy:80/service/b";
//        uri = "http://service_cart_envoy:80/user";
        uri = "http://front-envoy:80/user";
        logger.info("URI: "+uri);
        System.out.println("URI: "+uri);
        // retry logic
        for (int i = 0; i < 1; i++) {
            try {
                URL url = new URL(uri);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod(type.toString());

                if (type.equals(RequestTypesEnum.DELETE) || type.equals(RequestTypesEnum.POST)) {
                    conn.setRequestProperty("Content-Type", "application/json");
                } else if (type.equals(RequestTypesEnum.GET)) {
                    conn.setRequestProperty("Accept", "application/json");
                }

                //add request header
                Enumeration<String> hearderNames = request.getHeaderNames();
                while(hearderNames.hasMoreElements()) {

                    String headerName = hearderNames.nextElement();
                    conn.setRequestProperty(headerName, request.getHeader(headerName));
                }
//                conn.setRequestProperty("x-request-id", request.getHeader("x-request-id"));
//                conn.setRequestProperty("x-b3-traceid", request.getHeader("x-b3-traceid"));
//                conn.setRequestProperty("x-b3-spanid", request.getHeader("x-b3-spanid"));
//                conn.setRequestProperty("x-b3-parentspanid", request.getHeader("x-b3-parentspanid"));
//                conn.setRequestProperty("x-b3-sampled", request.getHeader("x-b3-sampled"));
//                conn.setRequestProperty("x-b3-flags", request.getHeader("x-b3-flags"));
//                conn.setRequestProperty("x-ot-span-context", request.getHeader("x-ot-span-context"));


//                if (request != null) {
//                    OutputStream os = conn.getOutputStream();
//                    os.write(request.getBytes());
//                    os.flush();
//                }
//
//                if (conn.getResponseCode() != 200 && i == 2) {
//                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//                }

                if (type.equals(RequestTypesEnum.GET)) {
                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    String output;
                    StringBuilder sb = new StringBuilder();
                    // System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        sb.append(output);
                        // System.out.println(output);
                    }
                    return sb.toString();
                } else if (type.equals(RequestTypesEnum.DELETE) || type.equals(RequestTypesEnum.POST)) {

                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                    String output;

                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    System.out.println("Output from Server .... " + output);
                    return output;

                }
                conn.disconnect();

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
        return null;
    }

    public static Product getJsonProduct(String name) {
        return client.target(REST_URI).path(String.valueOf(name)).request(MediaType.APPLICATION_JSON).get(Product.class);
    }

    public static void main(String[] args) {

//        // Product prod = getJsonProduct("Nikejacket");
//        // System.out.println(prod);
//
//        Product product1 = new Product("Adidas", "shoes");
//        Product product2 = new Product("Nike", "shoes");
//        Product product3 = new Product("Rebok", "shoes");
//        Product product4 = new Product("Under Armour", "shoes");
//        Product product5 = new Product("Nike", "shoes");
//        Product product6 = new Product("Rebok", "shoes");
//
//        List<Product> products = new ArrayList<Product>();
//        products.add(product1);
//        products.add(product2);
//        products.add(product3);
//        products.add(product4);
//        products.add(product5);
//        products.add(product6);
//
//        addProducts(products);
//        // addProduct(product);
//
//        getProduct();
//        deleteProduct("Nike");
//        getProduct();

//        getUser();

    }
}
