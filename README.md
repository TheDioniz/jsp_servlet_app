# JSP & Servlet application
#### Application for creating, reading and deleting users from external datasource
#### with presentation layer implemented in JSP (JSTL used)

Application uses one ControllerServlet application for logic and routing between views
Data is saved into MySQL Database which is configured via JNDI - jdbc/webshop
Data validation implemented, not allowed:
 - empty email
 - email does not matching w+@w+\.w+ regexp
 - empty password
 - password less than 8 chars
 - spaces in password