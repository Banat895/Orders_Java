## Order Application


1. A system for maintaining the orders.
2. I have used H2 as in memory database.
3. For API specifications I have used swagger for ease of testing and validating the response.
4. I have used default health check endpoint of actuator for this application. It can be accessed via `http://localhost:8080/actuator/health` this url.
5. You can access the swagger via `http://localhost:8080/swagger-ui.html`.


There are APIs present for:
1. Creating a new order
2. Updating an order
3. Deleting an order
4. Fetching an order
5. Get all the orders

### How to start the application?
```
For starting the application please import the application in any of the preferred IDE (Intellij, Eclipse, STS, etc.)

Application can be started by executing the main method present in class OrdersApplication.
```
