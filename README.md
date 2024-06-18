# myEShop

Web application developed using Spring Boot and Hibernate for backend services, ensuring efficient management of e-commerce operations like inventory and order processing. Spring Security was integrated to enhance authentication and secure user data, featuring CSRF protection and secure session management. The frontend was designed with HTML and CSS, providing a user-friendly interface.

## Technology used
- **Spring Security**: used to manage the security of the registration, authentication, login phase. *CSRF* protection and secure *session* management.
- **Hibernate**: ORM - Object Relational Mapping
- **PostgreSQL**
- **Java Mail Sender** connected with an **SMTP server** hosted by Google
- **H2 In-Memory Database** : isolation of the database during test execution.


## Features Implemented
- *Registration* with *email confirmation*.
- Display of *products*, loaded by a Rest API and dynamically generated html based on the content of the response.
- Modification of products in the *cart*, instant update of contents in case of removal and subtotal calculation.
- Creating and displaying *orders* on the user's Account page.
