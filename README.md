This is a project to create a web application that can create Notes, that need to have a title and content. The only main requirements were to be able to create notes and display them.

The project is built with Maven, to start the application one can:

run the Application.java file in src/main/java/notemanager/Application.java
write the command 'mvn spring-boot:run' in the terminal
After the application is running, access http://localhost:8080/notes for the main page or http://localhost:8080/h2-console to access the DB JDBC URL: jdbc:h2:file:./data/notedb User Name: admin Password: ris

As for the architecture, I used MVC, where the application can be divided into:

Model (represents and maps the data to the db)
View (handles the presentation layer)
Controller (acts as a middle man and manages the interactions, handles HTTP requests) In addition, I used a Service Layer that interacts with the Controller and contains the logic of the application
As the web framework, I used Spring Boot and Spring Data JPA/H2 for persistence.

My first idea was to use ArrayLists to maintain the notes, and before exiting the application, create a file with the existing nodes. But after researching other options, Data JPA had methods that simplify CRUD operations by working with objects, making it a good match for the project. As H2 typically uses in-memory database, I created a db in notes/data that maintains it even when the application is closed.

To display to the user, I used Thymeleaf to render HTML and dynamically insert the data into the page.

After the choices are made and the project is done, it is intended to work as follows: The user interacts with the page and sends a request, which is caught by the Controller. Based on the request, it calls the Service layer that interacts with the Repository. After the data from the DB is obtained, the Controller updates the Model, and Thymeleaf updates the page for the user.