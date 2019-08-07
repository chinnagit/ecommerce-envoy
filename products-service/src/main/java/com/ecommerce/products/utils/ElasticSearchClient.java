package com.ecommerce.products.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: bala_chinna
 * @Date: 7/18/19, Thu
 **/
public class ElasticSearchClient {

    //The config parameters for the connection
    private static String HOST = "vpc-cc-load-es6-im-ktnei6x4qb2pfapqodh7sipoue.us-east-1.es.amazonaws.com";
    private static final int PORT_ONE = 443;
//    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "https";

    private static RestHighLevelClient restHighLevelClient;
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String INDEX = "kibana_sample_data_ecommerce";
    private static final String TYPE = "_doc";

    /**
     * Implemented Singleton pattern here
     * so that there is just one connection at a time.
     * @return RestHighLevelClient
     */
    public static synchronized RestHighLevelClient makeConnection() throws Exception {

        String host = System.getenv("HOST");
        if(host == null){
            host = HOST;
        }

        if(restHighLevelClient == null) {
            System.out.println("Establishing elasticsearch connection to: "+HOST);
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(host, PORT_ONE, SCHEME)
//                            ,new HttpHost(HOST, PORT_TWO, SCHEME)
                    ));
        }


//        client = new PreBuiltTransportClient(Settings.builder().put("client.transport.sniff", true)
//                .put("cluster.name","elasticsearch").build())
//                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        System.out.println("Established connection to elastic search");
        return restHighLevelClient;
    }

    public static synchronized void closeConnection() throws IOException {
        restHighLevelClient.close();
        restHighLevelClient = null;
    }

    public static void getEcommerceDoc() throws Exception{
//        SearchResponse response = client.prepareSearch().execute().actionGet();
//        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
//
//        for(SearchHit hit : searchHits) {
//            String hitDoc = hit.getSourceAsString();
//            System.out.println("hitDoc: "+hitDoc);
//            JsonNode jsonNode = objectMapper.readTree(hitDoc);
//        }


        SearchRequest searchRequest = new SearchRequest(INDEX);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(20);
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(searchRequest);

        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
//        System.out.println("searchHits size: "+searchHits.size());

        StringBuilder data = new StringBuilder();
        for(int i=0; i<searchHits.size(); i++) {
            SearchHit hit = searchHits.get(i);
            String hitDoc = hit.getSourceAsString();
//            System.out.println("hitDoc: "+hitDoc);
            JsonNode jsonNode = objectMapper.readTree(hitDoc);
            JsonNode prodJsonNode = jsonNode.get("products");

            for(JsonNode node : prodJsonNode){
                ObjectNode object = (ObjectNode)node;
                object.remove("_id");
//                System.out.println("Product: "+prettyPrintJsonString(node));
                data.append(prettyPrintJsonString(object));

                if(i < searchHits.size()) {
//                    data.append(",\n");
                }
            }

//            System.out.println(prettyPrintJsonString(prodJsonNode));
//            String manufacturer = prodJsonNode.get(0).get("manufacturer").asText();
//            System.out.println("manufacturer: "+manufacturer);
            data.append("\n");
        }
        int len = data.length();
//        data.deleteCharAt(len-1);
//        data.deleteCharAt(len-2);
//        data.append("]");
//        System.out.println("complete list: "+data);

        PrintWriter out = new PrintWriter("products.json");
        out.write(data.toString());
        out.close();

//        GetRequest getPersonRequest = new GetRequest(INDEX, TYPE);
//        GetResponse getResponse = null;
//        try {
//            getResponse = restHighLevelClient.get(getPersonRequest);
//        } catch (java.io.IOException e){
//            e.getLocalizedMessage();
//        }
//        System.out.println(getResponse);
//
//
//        SearchResponse response = client
//                .prepareSearch()
//                .execute()
//                .actionGet();

//        return getResponse != null ?
//                objectMapper.convertValue(getResponse.getSourceAsMap(), Person.class) : null;
    }

    public static String prettyPrintJsonString(JsonNode jsonNode) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(jsonNode.toString(), Object.class);

//            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            return mapper.writeValueAsString(json);
        } catch (Exception e) {
            return "Sorry, pretty print didn't work";
        }
    }

    public static void writeToIndex() throws Exception {

        String indexName = "ecommerce";
        Response response = restHighLevelClient.getLowLevelClient().performRequest("HEAD", "/" + indexName);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 404) {
            CreateIndexRequest cireq=new CreateIndexRequest(indexName);
            CreateIndexResponse ciresp=restHighLevelClient.indices().create(cireq);
            System.out.println("Created index");
        } else {
            System.out.println("Index exists");
        }

        BulkRequest request = new BulkRequest();
        int count = 0;
        int batch = 2;

        BufferedReader br = new BufferedReader(new FileReader("/Users/bala_chinna/tutorials/ecommerce-envoy/products-service/src/main/resources/products.json"));

        String line;

        while ((line = br.readLine()) != null) {
            count++;

            request.add(new IndexRequest(indexName,"catalog")
                    .source(line, XContentType.JSON));
            if(count%batch==0) {
                BulkResponse bulkresp = restHighLevelClient.bulk(request);
                if(bulkresp.hasFailures()) {
                    for (BulkItemResponse bulkItemResponse : bulkresp) {
                        if (bulkItemResponse.isFailed()) {
                            BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                            System.out.println("Error "+failure.toString());
                        }
                    }
                }
                System.out.println("Uploaded "+count+" so far");
                request=new BulkRequest();
            }
        }
    }



}
