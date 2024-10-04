# Kitchensink Application

## Overview

The Kitchensink application is a Spring Boot application designed to manage members using a MongoDB database. This README outlines the steps required to build, run, and test the application.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK) 21**: Download and install JDK 21 from [Oracle](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html) or use an open-source alternative like [Adoptium](https://adoptium.net/).
- **Maven**: Ensure you have Maven installed. You can download it from [Apache Maven](https://maven.apache.org/download.cgi).
- **MongoDB**: Install and run MongoDB on your local machine. You can find installation instructions [here](https://docs.mongodb.com/manual/installation/). Alternatively, use cloud version.

## Getting Started

### Cloning the Repository

1. Clone the repository to your local machine using the following command:

   ```bash
   git clone https://github.com/Hjindal-dev/kitchensink.git

2. Navigate to the project directory:

    ```bash
    cd kitchensink

3. Build the Application
   To build the application, use Maven:

    ```bash
    mvn clean install

    This command will compile the source code, run the tests, and package the application into a JAR file.

4. Configure MongoDB

   4.1 Open the src/main/resources/application.properties file.

    4.2 Configure the MongoDB connection settings. The default settings may look like this:

        spring.data.mongodb.uri=mongodb://localhost:27017/kitchensink

    Ensure that the URI matches your MongoDB setup.

6. Running the Application
    Once the build is successful, you can run the application using the following command:
    ```bash
    mvn spring-boot:run

    Alternatively, you can run the packaged JAR file directly. After building, navigate to the target directory and run:
    java -jar kitchensink-0.0.1-SNAPSHOT.jar

7. Accessing the Application
    After starting the application, you can access it in your web browser at: http://localhost:8080

8. Running Tests
    To run the tests, use the following Maven command:
    ```bash
    mvn test
    ```
  Example Endpoints
- **List All Members**: `GET http://localhost:8080/api/members`
- **Register a Member**: `POST http://localhost:8080/members/register`
  
  Request Body Example:
  ```json
  {
      "name": "Jane Doe",
      "email": "jane@mailinator.com",
      "phoneNumber": "2125551234"
  }

8. Troubleshooting
    If you encounter issues, ensure that:

    MongoDB is running and accessible.
    The correct version of JDK is set up in your environment.
    All dependencies are correctly specified in the pom.xml.
    License
    This project is licensed under the MIT License. See the LICENSE file for details.

8. Acknowledgments
    Thanks to the Spring Boot and MongoDB communities for their valuable contributions to open-source software.

### Customization Notes
- **Versioning**: Update the JAR filename in the running section if your application version is different.
- **Example Endpoints**: Adjust or expand this section based on your API structure and available endpoints.

Feel free to modify any section further to fit your application's specific needs!
