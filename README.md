# Introduction: 
This project consists of RESTful services that implement a checkout counter for an online retail store that scans products and generates an itemized bill. 

The bill should also total the cost of all the products and the applicable sales tax for each product.
The total cost and total sales tax should be printed
Sales tax varies based on the type of products
-> category A products carry a levy of 10%
-> category B products carry a levy of 20%
-> category C products carry no levy

# REST endpoints
We can add/update/modify products and bills using the below REST endpoints:

# Products
*  GET /products - fetches list of all products
*  GET /products/{id} - fetches a specific product by id
*  POST /products - Creates a new product with incoming JSON request
*  PUT /products/{id} - Updates product details with incoming JSON request
*  DELETE /products/{id} - Delete an existing product if it is not added in the bill.

# Bills
*  GET /bills - fetches all bill details
*  GET /bills/{id} - fetches bill of particular id
*  POST /bills - creates a bill Id, we can add or remove products from this 
*  PUT /bills/{id} - Updates bill details, we can add or remove products from this with incoming JSON request.
*  DELETE /bills/{id} - Delete bill from the system.

# Steps to build and run locally:
* Open commandline
* Create a new directory called "onlineretailstore"
* Clone repository:- git clone https://github.com/vikrantdheer/OnlineRetailStore.git
* Build the executable jar:- mvn package
* Go to target folder:- cd target
* Run following command to start the server on port 8080:- java -jar "jar_name.jar"
* Swagger url:- http://localhost:8080/swagger-ui.html

Using H2 in-memory database and setting up the test data on application startup.

**Update with spring security username and password both are "vikrant"**

