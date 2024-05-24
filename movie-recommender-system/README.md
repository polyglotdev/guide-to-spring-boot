# Guide to Spring 5 and Spring Boot 2.0

[Course Link](https://www.educative.io/module/O7rwGNTE1LJD4RVVx/10370001/5666917543313408)

## Dependency Injection

**Dependency Injection (DI)** is a design pattern used to implement IoC (Inversion of Control), allowing the creation of dependent objects outside a class and providing those objects to a class in various ways. This helps in making the code more modular, testable, and maintainable.

### `@Autowired` Annotation

The `@Autowired` annotation in Spring is used to automatically inject dependencies into a class. When Spring sees this annotation, it looks for a suitable bean (an object managed by the Spring container) to inject into the class.

### Explanation in Context

1. **RecommenderImplementation Class**:
    ```java
    @Component
    public class RecommenderImplementation {
        private final Filter filter;

        @Autowired
        public RecommenderImplementation(Filter filter) {
            this.filter = filter;
        }

        // Other methods...
    }
    ```
2. **Filter Interface**:
    ```java
    public interface Filter {
      String[] getRecommendations(String movie);
    }
    ```
3. **ContentBasedFilter Class**:
    ```java
    @Component
    public class ContentBasedFilter implements Filter {
        public String[] getRecommendations(String movie) {
            // Logic of content-based filter
            return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
        }
    }
    ```

### How It Works

1. **Defining the Dependency**:
    - The `RecommenderImplementation` class has a dependency on the `Filter` interface. This is indicated by the `private final Filter filter;` field and the constructor that takes a `Filter` object as a parameter.
2. **Using @Autowired**:
    - The `@Autowired` annotation on the constructor tells Spring that it needs to inject a `Filter` implementation when creating an instance of `RecommenderImplementation`.
3. **Component Scanning**:
    - The `@Component` annotation on both `RecommenderImplementation` and `ContentBasedFilter` classes tells Spring to manage these classes as beans. When the Spring application context is initialized, Spring will create instances of these classes.
4. **Injecting the Dependency**:
    - When Spring creates an instance of `RecommenderImplementation`, it will look for a bean that implements the `Filter` interface. In this case, it finds the `ContentBasedFilter` bean.
    - Spring then injects the `ContentBasedFilter` instance into the `RecommenderImplementation` constructor.

### Summary

- **Filter is a Dependency**: The `Filter` interface is a dependency of the `RecommenderImplementation` class because `RecommenderImplementation` needs an object that implements `Filter` to function.
- **@Autowired**: The `@Autowired` annotation tells Spring to automatically inject the appropriate `Filter` implementation (in this case, `ContentBasedFilter`) into the `RecommenderImplementation` class.
- **Spring Container**: Spring manages the creation and injection of these objects, making the code loosely coupled and easier to manage.

---

## Bean Scopes

The Spring container manages beans. The term bean scope refers to the lifecycle and the visibility of beans. It tells how long the bean lives, how many instances of the bean are created, and how the bean is shared.

## Types of bean scopes[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/4548361560784896#Types-of-bean-scopes)

There are six types of scopes: singleton, prototype, request, session, application, and websocket.

![Bean Scopes](./src/main/resources/images/bean-scope.svg)

The singleton and prototype scopes can be used in any application while the last four scopes are only available for a web application. In this lesson, we will focus on singleton and prototype bean scopes only.

### Singleton scope[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/4548361560784896#Singleton-scope)

The **default scope** of a bean is **singleton**, in which **only one instance of the bean is created and cached in memory**. Multiple requests for the bean return a shared reference to the same bean. In contrast, **prototype** scope results in the **creation of new beans whenever a request for the bean is made to the application context**.

In our movie recommendation system example, we have two implementations of the `Filter` interface, namely `ContentBasedFilter` and `CollaborativeFilter`. We will use them to show the differences between singleton and prototype bean scope.

For the code example shown in this lesson, we have created a sub-package called `lesson8` inside the package `io.datajek.spring.basics.movierecommendersystem`. The package contains `MovieRecommenderSystemApplication.java`, `Filter.java`, `ContentBasedFilter.java`, and `CollaborativeFilter.java` files.

Application context manages the beans, and we can retrieve a bean using the 
`getBean()` method. If we request the application context for the `ContentBasedFilter` bean three times as shown, we get the same bean:
