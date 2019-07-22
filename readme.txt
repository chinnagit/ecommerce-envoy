1. git clone

2. docker-compose up

3. Unable to launch elastic-search container due to memory constraint in mac docker.

4. once all containers are up and running

a. open the postman and get the token -- for now authentication happening with just
random token, need to explore more on it.

GET  http://localhost:9080/token

GET /token HTTP/1.1
Host: localhost:9080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 6e4f5142-cd91-79b2-5c47-410c72cb7a79



b. copy the token and pass the Authorization header for other apis

for eg -- get users from user micro service

GET http://localhost:8080/user

GET /user HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: <token got from above api>


c. cart micro service accessing user micro service -- envoy egress sample

GET http://localhost:8080/cart/users/


GET /cart/users/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: <token>


4. To fill the data refer the ElasticSearchClient.java in products-service

elastic search, kibana docker compose helps to collect the sample ecommerece data.


