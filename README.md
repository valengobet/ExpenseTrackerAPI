# ExpenseTrackerAPI
A RESTful API for managing expenses, built with Spring Boot. This API allows users to create, read, update, and delete expense records, categorize expenses, track payments by method or recipient, and calculate total expenses by category, date, month, or year. Fully documented with Swagger for easy integration.

### Features
RESTful CRUD operations for expense management.
- Categorization of expenses and support for custom payment methods and recipients.
- Filter and query expenses by date, category, payment method, or recipient.
- Modular architecture with Controller, Service, and Repository layers.
- API documentation using Swagger UI.

### Steps to Setup
1. Clone the repository
   ```
   git clone https://github.com/yourusername/ExpenseTrackerAPI.git  
   cd ExpenseTrackerAPI  
   ```

2. Create the MySQL database
Log in to MySQL and create the database:
   ```
   CREATE DATABASE expense_tracker;  
   ```

3. Update database credentials
   1. Open the file src/main/resources/application.properties.
   2. Modify the following properties with your MySQL username and password:
      ```
      spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker  
      spring.datasource.username=your_mysql_username  
      spring.datasource.password=your_mysql_password  
      ```

4. Run the application
You can run the app using Maven:
   ```
   mvn spring-boot:run
   ```
   The app will be available at:
   http://localhost:8080

### API Documentation
Swagger UI documentation is available at:
http://localhost:8080/swagger-ui/index.html
![image](https://github.com/user-attachments/assets/6fd6a422-49ef-40fb-96cf-59e46255fc75)
![image](https://github.com/user-attachments/assets/9832955c-437c-42bb-a74e-e17fbaeb14f6)

### API Endpoints  
Expense CRUD Endpoints  

| Method | URL              | Description           | Request Body |  
|--------|------------------|-----------------------|--------------|  
| POST   | /gastos          | Add a new expense     | JSON         |  
| GET    | /gastos          | Get all expenses      | N/A          |  
| GET    | /gastos/{id}     | Get expense by ID     | N/A          |  
| PUT    | /gastos/{id}     | Update expense by ID  | JSON         |  
| DELETE | /gastos/{id}     | Delete expense by ID  | N/A          |  

### Additional Endpoints for Filtering and Summarizing  

| Method | URL                                         | Description                       |  
|--------|--------------------------------------------|-----------------------------------|  
| GET    | /gastos/categoria/{categoria}              | Get expenses by category          |  
| GET    | /gastos/fecha/{fecha}                      | Get expenses by date              |  
| GET    | /gastos/mes/{mes}                          | Get expenses by month             |  
| GET    | /gastos/anio/{anio}                        | Get expenses by year              |  
| GET    | /gastos/categoria/gasto_total/{categoria}  | Get total expense by category     |  
| GET    | /gastos/metodo_pago/gasto_total/{metodo_pago} | Get total expense by payment method |  
| GET    | /gastos/destinatario/gasto_total/{destinatario} | Get total expense by recipient  |  

## Author
**Valentin Gobet**


