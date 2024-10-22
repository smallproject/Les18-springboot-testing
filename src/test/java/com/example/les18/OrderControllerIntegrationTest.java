package com.example.les18;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;

// starts spring application context
// starts the entire springboot application
// necessary when y ou want to test the interaction of components with each other, such as controllers, services and repositories
//@SpringBootTest is used for integration testing by loading the full Spring application context.
//It works well with both REST controllers and repositories, allowing you to test the interaction of components.
//Use it with JUnit for integration testing across multiple layers of your application.
@SpringBootTest


//@AutoConfigureMockMvc configures a MockMvc instance to be injected into your test classes. MockMvc allows you to simulate HTTP requests and assert responses without actually starting the embedded Tomcat server. This makes the tests much faster and focused on the controller layer.
//It creates and configures a MockMvc object automatically for you, so you don’t need to configure it manually.
//You can use mockMvc to send GET, POST, PUT, DELETE requests to your endpoints and assert results.
//When you add addFilters = false to @AutoConfigureMockMvc, it disables Spring Security filters or any other configured filters in the application for that particular test. This is useful when you want to test the controller logic without authentication, authorization, or other filters getting in the way.
//Normally, when you use MockMvc to test an application with Spring Security enabled, the security filters would run, so you'd need to authenticate your requests. By setting addFilters = false, the security layer is bypassed, and you can focus on testing the logic without dealing with authentication.

//Explanation:
//@SpringBootTest loads the full application context.
//@AutoConfigureMockMvc(addFilters = false) ensures that MockMvc is auto-configured, but the security filters (e.g., Spring Security) are disabled. This allows the MockMvc instance to bypass authentication/authorization mechanisms, making it easier to test controller functionality.
//        mockMvc.perform(get(...)) sends a GET request to the controller and asserts the status and content.
//Use Case for addFilters = false:
//Testing API endpoints without security: When you want to test the functionality of your controller endpoints without dealing with authentication and authorization.
//Simplifying tests: If security is irrelevant to the test scenario, disabling it can make tests simpler, faster, and more focused on business logic.
@AutoConfigureMockMvc(addFilters = false)

//@ActiveProfiles("test") is a Spring annotation used to specify which profile to activate when running a particular test. This is useful when you want to load different configurations or properties for your test environment, separate from production or development configurations.
//Usage of @ActiveProfiles("test") in Tests
//@ActiveProfiles("test") is typically used in unit or integration tests where you want to use different settings (like an in-memory database, mocked services, etc.).
//Explanation:
//@SpringBootTest: This annotation loads the full application context for integration testing.
//@ActiveProfiles("test"): This activates the test profile, meaning the configurations in application-test.properties will be loaded. This is particularly useful if you want to use different database configurations, or mock certain external services, during testing.
//BookService: In this case, the BookService bean is injected, and the test ensures that the service is loaded correctly in the test environment.
//        3. When to Use @ActiveProfiles("test")
//Environment-Specific Configuration: When you want your test to load specific configurations (like a test database, mock service endpoints, etc.) that differ from the production or development environments.
//Testing with Different Settings: You can simulate different environments by switching profiles in your tests, such as using @ActiveProfiles("prod") for testing a production-like environment.
//Summary:
//@ActiveProfiles("test") is used in Spring Boot tests to activate the test profile.
//It allows you to load specific configurations, such as test databases, mock services, or other environment-specific settings.
//The annotation is useful for both unit tests and integration tests, allowing you to create a controlled, isolated test environment.
@ActiveProfiles("test")

class OrderControllerIntegrationTest {

//    @Autowired is a Spring Framework annotation used for dependency injection. It allows Spring to automatically inject the required beans (or dependencies) into a component (such as a class, service, or controller) at runtime, eliminating the need to manually instantiate objects.
//Summary of Best Practices:
//    Constructor Injection is the recommended approach because:
//
//    It promotes immutability.
//    It makes dependencies explicit.
//    It simplifies unit testing (especially for mocking dependencies).
//    Field Injection is simple but less preferred due to poor testability and tight coupling.
//
//    Setter Injection is useful when the dependency is optional or needs to be injected after object creation.
    @Autowired
    MockMvc mockMvc;

//    @Test is a key annotation from the JUnit testing framework, used to mark a method as a test case in a test class. When a method is annotated with @Test, the JUnit framework will recognize it as a unit test and execute it as part of the test suite.
//
//    Usage of @Test Annotation
//    The @Test annotation is used to indicate that a particular method is intended to be run as a test method. Typically, methods annotated with @Test contain assertions that check whether the code behaves as expected. If the assertions pass, the test is considered successful; if they fail, the test fails.
//    Summary:
//    @Test marks methods as test cases for JUnit.
//    Tests are used to verify expected behavior through assertions.
//    In JUnit 5, @Test provides flexibility with timeout, exception handling, and parameterized tests.
    @Test
    void shouldCreateCorrectOrder() throws Exception {

//        this creates a json file for creating ORDER
        String requestJson = """
                {
                    "productname" : "Gibson gitaar",
                    "unitprice" : 2399.00,
                    "quantity" :  5
                }
                """;

//        this runs the test via endpoints by throwing in a json file
        this.mockMvc

//        The method .perform(MockMvcRequestBuilders.post("/orders")) is used in Spring Boot testing with MockMvc to simulate an HTTP POST request to the /orders endpoint of a web application.
//
//        Usage in a Test Case
//        When testing web controllers in Spring Boot, MockMvc allows you to perform HTTP requests (like GET, POST, PUT, etc.) without having to actually start the server. Instead, it mocks the behavior of the MVC layer, making it easier and faster to test.
//        Common Expectations in Tests:
//        You can check for various things in the response, such as:
//        Status Codes: Use status().isOk(), status().isCreated(), status().isBadRequest(), etc.
//                Response Content: You can use jsonPath() to check for fields in the JSON response.
//                Headers: You can also check if specific headers are returned, using .andExpect(header().string("Location", ...)).
//        Summary:
//.perform(MockMvcRequestBuilders.post("/orders")) simulates a POST request to the /orders endpoint in a Spring Boot test.
//                It allows you to test the behavior of your controllers, ensuring that the HTTP request and response cycle works as expected, all without starting the actual server.
//                With additional methods, you can assert status codes, response bodies, headers, and more.
                .perform(MockMvcRequestBuilders.post("/orders")

//                        The .contentType(APPLICATION_JSON) method is used in Spring Boot MockMvc tests to specify that the content type of the HTTP request being sent is application/json. This is crucial when you're testing endpoints that expect to receive JSON data in the request body, such as a POST, PUT, or PATCH request where you send data to be processed (like creating or updating an entity).
//                        Explanation:
//        contentType(MediaType.APPLICATION_JSON):
//
//        This tells the MockMvc framework that the request body being sent should be interpreted as JSON. The server will expect the request body in JSON format, and Spring will attempt to convert the JSON payload into the appropriate object (for example, using @RequestBody in the controller).
//        The MediaType.APPLICATION_JSON constant is imported from org.springframework.http.MediaType, which represents the MIME type application/json.
//                content(orderJson):
//
//        This is the actual content (the JSON string) that you are sending in the request body. It contains the data you want the controller to process.
//        Why Content Type is Important:
//
//        The Content-Type header informs the server about the format of the data being sent in the request body.
//        In Spring Boot applications, if the server expects JSON data, setting the correct content type ensures that the server can deserialize the JSON into the correct Java object.
//                Without the correct Content-Type, the server may reject the request or fail to parse the input correctly.
//                        Summary:
//.contentType(MediaType.APPLICATION_JSON): Ensures the server knows the request body is in JSON format.
//                It's crucial for endpoints that accept JSON data and perform operations like creating or updating resources.
//        Correct content type is needed for proper deserialization of the request body into Java objects using annotations like @RequestBody.
                .contentType(APPLICATION_JSON)

//                        In Spring Boot MockMvc tests, the method .content(requestJson) is used to set the request body of an HTTP request. When testing API endpoints (such as POST or PUT requests), you often need to send a JSON payload as the request body. The .content() method allows you to provide that payload as a string.
//
//                                Here's a breakdown of how .content(requestJson) works in the context of MockMvc testing.
//                        Purpose of .content(requestJson)
//                        requestJson: This is a string that represents the JSON payload you want to send in the body of the HTTP request. For example, when you're creating or updating a resource, you send the data in JSON format, and .content() allows you to include that data in your test request.

//                        What Happens Behind the Scenes:
//        @RequestBody in the Controller: The JSON data sent using .content() in the test will be deserialized into a Java object (like OrderRequest) by Spring's @RequestBody mechanism.
//        Assertions in the Test: The assertions in the test ensure that the correct status code and response data are returned after processing the request.
//        Things to Note:
//        Correct JSON Formatting: Ensure that the string passed to .content() is a properly formatted JSON string. If the JSON structure is incorrect, the test may fail due to deserialization errors.
//                Matching Java Object: The structure of the JSON in the request should match the fields of the object (like OrderRequest) that the controller is expecting. Otherwise, Spring will throw an exception during request body deserialization.
//                Summary:
//        The .content(requestJson) method is used in MockMvc tests to set the request body of an HTTP request.
//                You can use it to send JSON payloads, especially in POST, PUT, or PATCH requests, when you need to provide data to the server.
//        The requestJson should be a valid JSON string, and you should specify the content type as application/json using .contentType(MediaType.APPLICATION_JSON).
//                        This method allows you to test how your Spring controllers handle incoming JSON data without actually starting a server.
                .content(requestJson))

//        The .andDo(MockMvcResultHandlers.print()) method in Spring Boot MockMvc tests is used to print detailed information about the request and response when the test is run. This is particularly useful for debugging, as it provides you with insight into the exact request sent to the server and the response received, including the HTTP status, headers, and body.
//
//                Purpose of .andDo(MockMvcResultHandlers.print()):
//        Print Debug Information: When you chain .andDo(print()) after a MockMvc request, it prints out the entire request-response cycle to the console. This includes details like:
//        The URL path.
//                The HTTP method used (e.g., GET, POST).
//                Request headers.
//                Request body (if applicable).
//        The status code of the response.
//        Response headers and response body.
//        This is useful when you want to quickly see what is happening during a test or if a test is failing, as it gives you more context about the error.

//        Why Use .andDo(print()):
//        Debugging: If a test fails, the output from .andDo(print()) helps you understand exactly what was sent in the request and what was returned in the response, making it easier to pinpoint issues.
//
//                Verbose Output: It provides a full, verbose log of both the request and response, making it ideal for identifying problems such as incorrect HTTP status codes, missing or incorrect fields in the response body, or header issues.
//
//        Non-intrusive: It doesn't change the behavior of the test. It simply adds more visibility, so it's safe to add .andDo(print()) to tests for additional logging without affecting test results.
//
//        Summary:
//.andDo(MockMvcResultHandlers.print()) is used in Spring Boot MockMvc tests to print detailed request and response information to the console.
//                It's commonly used for debugging to inspect the full HTTP request and response details.
//        It provides useful information, especially when tests fail, to help you understand what went wrong during the request-response cycle.
//                Using .andDo(print()) in combination with your other assertions gives you better insights into your MockMvc tests, making it easier to debug your API endpoints during unit and integration testing.
                .andDo(MockMvcResultHandlers.print())


//        The .andExpect(MockMvcResultMatchers.status().isCreated()) method is used in Spring Boot MockMvc tests to assert that the HTTP status code of the response is 201 Created. This status code is typically returned by a server when a resource has been successfully created in response to a POST request (or sometimes PUT).
//
//        Explanation:
//        MockMvcResultMatchers.status(): This part is used to set up the expectation for the HTTP status code in the response.
//.isCreated(): This checks specifically for HTTP status 201, which means the server successfully created the resource.
//                Usage Example:
//        In the context of a RESTful API, if you are testing the creation of an entity like an "Order," and your API returns 201 Created upon successful creation, you would use this assertion to ensure that your endpoint works as expected.
//        What Happens in the Test:
//        The test sends a POST request to the /orders endpoint with some JSON data.
//        The controller (as shown above) processes the request, creates the order, and returns a 201 Created status with the order details.
//        The test checks that the status code is indeed 201 Created, meaning the request was successfully handled, and the resource was created.
//        Summary:
//.andExpect(MockMvcResultMatchers.status().isCreated()) checks that the response status is 201 Created.
//                It is used to verify that a resource creation operation (typically a POST request) has been successfully completed.
//        This assertion is commonly used in tests for endpoints that create new resources in a RESTful API.
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
