This is a project to create a web application that can create Notes, that need to have a title and content. The initial idea was to create a basic notes app to understand how such would be built and work, and the challenges I would face in it's development. As advised and discussed with Professor Julien, to make it not just simple CRUD operations, I thought of other functionalities that a "complete" Notes application have and which ones I use the most/help me in general. The search bar to match a title or specific content is a must for me, as I create many and don't organize it often. With that in mind, some notes are more important than others, which made me think of a way of organizing them based on urgency, that being how often I use it or if there is a deadline for it's purpose.


The project is built with Maven, to start the application one can:
- Run the Application.java file in src/main/java/notemanager/Application.java
- Write the command 'mvn spring-boot:run' in the terminal

After the application is running, access http://localhost:8080/notes for the main page, 
or to access the DB: http://localhost:8080/h2-console 

JDBC URL: jdbc:h2:file:./data/notedb

User Name: sa

Password:


As for the architecture, I used MVC, where the application can be divided into:
- Model (represents and maps the data to the db)
- View (handles the presentation layer)
- Controller (acts as a middle man and manages the interactions, handles HTTP requests)

In addition, I used a Service Layer that interacts with the Controller and contains the logic of the application

As the web framework, I used Spring Boot and Spring Data JPA/H2 for persistence.

My first idea was to use ArrayLists to maintain the notes, and before exiting the application, create a file with the existing nodes.
But after researching other options, Data JPA had methods that simplify CRUD operations by working with objects, making it a good match for the project.

As H2 typically uses in-memory database, I created a db in notes/data that maintains it even when the application is closed.

To display to the user, I used Thymeleaf to render HTML and dynamically insert the data into the page.

After the choices are made and the project is done, it is intended to work as follows:
The user interacts with the page and sends a request, which is caught by the Controller. Based on the request, it calls the Service layer that interacts with the Repository. After the data from the DB is obtained, the Controller updates the Model and return the new desired page. The new or updated page then gets returned to the user.


Extra remarks:
Initially I wanted to use more of a back-end approach for the search function, but finally I decided to use JavaScript due to it's easiness to use and implementation since it is a web application. 
I have also started to implement a "tag" component (which is more or less already correctly implemented) that for this project I decided to not further develop, exactly as Julien predicted that it would be a lot of work. It already successfully scrapes the content to help organize into other categories, such as #work, #recipes or #accounts for example (making it also easier to separate them into Folders in the future, or maybe other ideas such as better visual design), if it would be more broadly used.
