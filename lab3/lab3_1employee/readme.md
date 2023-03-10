
## Excercise

#### Identify a couple of examples that use AssertJ expressive methods chaining.

Some exmaples of expressive method chaning in the example SpringBoot code found in this project are:
- ```assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());```
- ```assertThat(found).extracting(Employee::getName).containsOnly("bob");```
- ```assertThat(found).extracting(Employee::getName).containsOnly("bob");```

#### Identify an example in which you mock the behavior of the repository (and avoid involving a database). 

Mocking the behaviour of the database is most easily done with the use of Mockito library. The user should firstly create a mock a mocked variable and inject the mocks just like in the code snippet below.
```java     
@Mock( lenient = true)
private EmployeeRepository employeeRepository;

@InjectMocks
private EmployeeServiceImpl employeeService; 
```
Then the user can use the mocked variable in the tests and mock the responses with the help of ``` Mockito.when() ``` method.
```java
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
```

#### What is the difference between standard @Mock and @MockBean?

``` @Mock ``` is an annotation provided by the Mockito library and is simply a shorthand for ``` Mockito.mock() ``` method.
``` @MockBean ``` is an annotation provided by SpringBoot. It not only initializes the mock object but also adds them to the Spring application context. The mock will replace any existing bean of the same type in the app context.

#### What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

"application-integrationtest.properties" holds the configuration data for setting up the integration tests. For instance, in this project the "application-integrationtest.properties" is containing data about the database that is necesarry to perform the service and database integration test (like username, password, database address and method of testing the database).

#### The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

Strategy C - Does not involve the database, is strictly for the verification of the boundary components behaviour.

Strategy D - Verifies the boundary with the full Spring Boot application loaded. This is an integration test strategy in which many components participate (REST endpoint, service implementation, repository and database).

Strategy E - Similar to the Strategy D but tests the REST API with an explicit HTTP client.