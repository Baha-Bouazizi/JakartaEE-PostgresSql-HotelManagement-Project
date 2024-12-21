# Jakarta EE Hotel Management System

## Project Overview

This Jakarta EE-based Hotel Management System is designed to efficiently manage hotels, room types, and reservations through three distinct user roles: Admin, Agent, and Normal User.

- **Admin**: Manages Agents.
- **Agent**: Adds hotels, room types, links hotels with room types (prices and quantities).
- **Normal User**: Views hotels and makes reservations (feature under development).

## Features

- Admin can manage agents.
- Agents can add hotels and room types, and link them with prices and quantities.
- Normal users can view hotels and make reservations (feature not fully implemented).

## Technologies Used

- **Backend**: Jakarta EE (formerly Java EE)
- **Database**: PostgreSQL
- **Development IDE**: IntelliJ IDEA (or any compatible Java IDE)
  
## Prerequisites

- JDK 11 or higher
- Jakarta EE-compatible application server (e.g., Payara, WildFly, or GlassFish)
- PostgreSQL database

## How to Run the Project

1. **Clone the Repository**:
   Clone this project to your local machine using the following command:
   ```bash
   git clone https://github.com/Chouikhi-abdallah/JakartaEE-PostgresSql-HotelManagement-Project.git
   ```
  
2. **Set up the Database**:

Install PostgreSQL on your machine if you don't have it already.
Create a new PostgreSQL database.
Update the database connection settings in the persistence.xml or application.properties file:
db.url: The URL to your PostgreSQL database
db.username: Your database username
db.password: Your database password

3. **Configure the Jakarta EE Server**:

Deploy the project to a Jakarta EE-compatible application server such as Payara, WildFly,Tomcat or GlassFish.
Make sure the server is running and accessible.
Build and Run:

If using Maven or Gradle, run the build command:
```bash
mvn clean install
```
Start your Jakarta EE server and deploy the application.
4. **Access the Application**:

Open a browser and go to the following URL:
```bash
http://localhost:8083/Proj_war_exploded/home
```
## Future Features
Complete the reservation functionality for normal users.
Add additional features like user authentication and hotel booking confirmation.
## Contributing
Feel free to fork the repository and submit issues or pull requests. Contributions are always welcome!

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.
