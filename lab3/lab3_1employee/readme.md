
## Excercise

#### Identify a couple of examples that use AssertJ expressive methods chaining.

Some exmaples of expressive method chaning in the example SpringBoot code found in this project are:
- ```java assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());```
- ```java assertThat(found).extracting(Employee::getName).containsOnly("bob");```
- ```java assertThat(found).extracting(Employee::getName).containsOnly("bob");```

#### Identify an example in which you mock the behavior of the repository (and avoid involving a database). 

WIP

#### What is the difference between standard @Mock and @MockBean?

WIP

#### What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

WIP

#### The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

WIP