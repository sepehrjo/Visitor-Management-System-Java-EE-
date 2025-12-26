# Visitor Management System
A Java EE application designed to manage visitor requests in a residential community. It supports three user roles: Residents, Security, and Staff.

## Why this project exists
Managing visitors in a large community can be chaotic. This system provides a structured way for residents to pre-approve guests, for security to track entries, and for staff to oversee the entire process.

## Quick Start
1. **Prerequisites**: You'll need NetBeans 8.2 and GlassFish 4.1.1.
2. **Database**: Set up a database and update the `persistence.xml` in the `ejb` module if needed.
3. **Open Project**: Open both the `ejb` and `war` folders as projects in NetBeans.
4. **Deploy**: Deploy the `ejb` module first, then the `war` module to GlassFish.
5. **Run**: Access the application through your browser (usually `http://localhost:8080/war`).

## Project Structure
- `ejb/`: Contains the backend logic, database models (JPA), and EJB facades.
- `war/`: Contains the web interface (JSP), servlets (Controllers), and CSS.
- `.gitignore`: Keeps the repository clean by ignoring build and IDE files.


## License
No license file found.
