# Sorting Algorithm RESTful API with HATEOAS in Spring MVC

## Table of Contents

- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Exception Handling](#exception-handling)
- [Acknowledgements](#acknowledgements)

## Project Description

This project is a Spring MVC application that provides a RESTful API for sorting an array of integers using various algorithms. The application adheres to HATEOAS principles and offers a web interface for user interaction.

## Technologies Used

- Java
- Spring MVC
- Jakarta EE
- HATEOAS
- JSP
- JSTL
- Apache Tomcat

## Setup Instructions

### Prerequisites

- JDK 11 or higher
- Apache Tomcat 9 or higher
- Maven

### Project Setup

1. **Clone the repository**

    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Configure Dependencies**

   Ensure your `pom.xml` includes the necessary dependencies:

    

3. **Build the project**

    ```bash
    mvn clean install
    ```

4. **Deploy to Tomcat**

    - Copy the generated WAR file from the `target` directory to the `webapps` directory of your Tomcat installation.
    - Start the Tomcat server.

### Configuration Classes

1. **Spring Configuration**

    ```java
    @Configuration
    @EnableWebMvc
    @ComponentScan(basePackages = "com.example.webapp")
    public class AppConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        }

        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/WEB-INF/views/");
            resolver.setSuffix(".jsp");
            return resolver;
        }
    }
    ```

2. **Servlet Initializer**

    ```java
    public class SpringServlet extends AbstractAnnotationConfigDispatcherServletInitializer {
        @Override
        protected Class<?>[] getRootConfigClasses() {
            return null;
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class[] { AppConfig.class };
        }

        @Override
        protected String[] getServletMappings() {
            return new String[] { "/" };
        }
    }
    ```

## Usage

### Access the Web Interface

Navigate to `http://localhost:8080/<context-path>/` to access the web interface for sorting arrays.

### API Endpoints

#### Sort Array Endpoint

- **URL:** `/api/sort/array`
- **Method:** `POST`
- **Parameters:**
    - `array`: Comma-separated list of integers (e.g., `1,5,3,2`)
    - `algorithm`: Sorting algorithm to use (`heap`, `quick`, `radix`, `bucket`, `merge`)

#### Example JSON Response

```json
{
  "sortedArray": [1, 2, 3, 5],
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/sort/array?array=1,5,3,2&algorithm=quick"
    }
  }
}
Exception Handling
Custom exception handling is implemented for invalid array formats and unsupported algorithms.

Acknowledgements
This project is based on the principles of Spring MVC and HATEOAS, and it leverages various sorting algorithms for educational purposes.