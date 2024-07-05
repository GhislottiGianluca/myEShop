# myEShop

Web application developed using Spring Boot and Hibernate for backend services, ensuring efficient management of e-commerce operations like inventory and order processing. Spring Security was integrated to enhance authentication and secure user data, featuring CSRF protection and secure session management. The frontend was designed with HTML and CSS, providing a user-friendly interface.

## Technology used
- **Spring Security**: used to manage the security of the registration, authentication, login phase. *CSRF* protection and secure *session* management.
- **Hibernate**: ORM - Object Relational Mapping
- **PostgreSQL**
- **Java Mail Sender** connected with an **SMTP server** hosted by Google
- **H2 In-Memory Database**: isolation of the database during test execution.
- **Mockito** for testing.
- **Javadoc** for code documentation.


## Features Implemented
- *Registration* with *email confirmation*.
- Display of *products*, loaded with different sorting (e.g. by Price, by Sold) and dynamically generated html based on the content of the response.
- Modification of products quantity in the Cart page, with instant update of contents in case of removal and subtotal calculation.
- Creating and displaying *orders*, possible modification of user's personal data on the Account page.
