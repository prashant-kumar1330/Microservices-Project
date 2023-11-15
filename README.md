# Ecommerce Backend



![image](https://github.com/prashant-kumar1330/Microservices-Project/assets/43280446/03873af3-00d8-4ff1-afe8-07f74db1c39b)

• Created five distinct Microservices in SpringBoot for managing Product,
Inventory, and Security of the system. <br>
• Implemented Service discovery pattern for services to locate each other on a
network using Eureka server. <br>
• Implemented a Circuit Breaker mechanism to improve system reliability. <br>
• Used Kafka for asynchronous communication between microservices. <br>
• Incorporated JWT for secure authentication and authorization. <br>
• Employed both MongoDB and SQL databases. . <br>
• Ensured observability and debugging ease by integrating Zipkin for
Distributed tracing. <br>
• Implemented an API gateway as the single entry point to the system, handling
routing and security <br>

PRODUCT SERVICE
   
  • Handles the information about the product.<br>
  • Rest endpoints <br>
    GET  "/api/product" <br>
    POST "/api/product" <br>
  • Database used -> MongoDB   <br>

  ORDER SERVICE <br> <br>
  • used to create order after synchronously calling inventory service to check the avalibilty of the product  . <br>
  • Produces events to the kafka server which will be consumed by notification service to send notification. <br>
  • Rest endpoints <br>
    POST "/api/order" <br>
  • Database used -> Mysql  <br>

  INVENTORY SERVICE <br> <br>
  • used to handle the quantity of products  . <br>
  • Rest endpoints <br>
    POST "/api/inventory" <br>
  • Database used -> Mysql  <br>

  AUTH SERVICE <br> <br>
  • used register user and create JWT token after authentication  . <br>
  • Rest endpoints <br>
    POST "/auth/register" <br>
    GET "/auth/getToken" <br>
  • Database used -> Mysql  <br>

  API GATEWAY <br> <br>
  • used create a single entry point to the application and authenticate the token of each request. <br>
  • it will discover all service from the service registry and route request to them.
  • Database used -> Mysql  <br>
  
   




