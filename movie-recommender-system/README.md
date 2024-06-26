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

For the code example shown in this lesson, we have created a sub-package called `lesson8` inside the package `com.domhallan.movierecommendersystem`. The package contains `MovieRecommenderSystemApplication.java`, `Filter.java`, `ContentBasedFilter.java`, and `CollaborativeFilter.java` files.

Application context manages the beans, and we can retrieve a bean using the 
`getBean()` method. If we request the application context for the `ContentBasedFilter` bean three times as shown, we get the same bean:

```java
package com.domhallan.movierecommendersystem.lesson8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MovieRecommenderSystemApplication {

	public static void main(String[] args) {
		
		//ApplicationContext manages the beans and dependencies
		ApplicationContext appContext = SpringApplication.run(MovieRecommenderSystemApplication.class, args);

		//Retrieve singleton bean from application context thrice
		ContentBasedFilter cbf1 = appContext.getBean(ContentBasedFilter.class);	
		ContentBasedFilter cbf2 = appContext.getBean(ContentBasedFilter.class);	
		ContentBasedFilter cbf3= appContext.getBean(ContentBasedFilter.class);	
					
		System.out.println(cbf1);
		System.out.println(cbf2);
		System.out.println(cbf3);
	}
}
```

Output:

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-24T08:03:53.781-04:00  INFO 6384 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Starting MovieRecommenderSystemApplication using Java 17.0.7 with PID 6384 (/Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system/target/classes started by domhallan in /Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system)
2024-05-24T08:03:53.782-04:00  INFO 6384 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : No active profile set, falling back to 1 default profile: "default"
Constructor invoked...
Setter method invoked..
2024-05-24T08:03:53.953-04:00  INFO 6384 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Started MovieRecommenderSystemApplication in 0.291 seconds (process running for 0.528)
com.domhallan.movierecommendersystem.lesson8.ContentBasedFilter@3b8ee898
com.domhallan.movierecommendersystem.lesson8.ContentBasedFilter@3b8ee898
com.domhallan.movierecommendersystem.lesson8.ContentBasedFilter@3b8ee898

Process finished with exit code 0
```
As can be verified from the output, all beans are the same. The application context did not create a new bean when we requested it the second and third time. Rather, it returned the reference to the bean already created. Pictorially, it can be shown as follows:

![Singleton Bean Scope](./src/main/resources/images/bean-application-context.svg)

Singleton bean scope is the default scope. It is used to minimize the number of objects created. Beans are created when the context is loaded and cached in memory. All requests for a bean are returned with the same memory address. This type of scope is best suited for cases where stateless beans are required. On the contrary, prototype bean scope is used when we need to maintain the state of the beans.

### Prototype scope[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/4548361560784896#Prototype-scope)

Now we will change the scope of the `CollaborativeFilter` bean from 
singleton to prototype. For this, we will use the `@Scope` annotation and 
import `org.springframework.context.annotation.Scope` and `org.
springframework.beans.factory.config.ConfigurableBeanFactory`. We can 
specify the scope in the two ways. `@Scope("prototype")` or `@Scope
(ConfigurableBeanFactory.SCOPE_PROTOTYPE)`. Here is the updated code:

```java
package com.domhallan.movierecommendersystem.lesson8;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Collaborative filtering implementation of the Filter interface
 */
@Component
@Scope("prototype")
public class CollaborativeFilter implements Filter {

   /**
    * Provides movie recommendations based on collaborative filtering.
    *
    * @param movie - the movie for which recommendations are needed.
    * @return array of recommended movie titles.
    */
   public String[] getRecommendations(String movie) {
      //logic of content based filter
      return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
   }
}
```

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-24T08:13:45.775-04:00  INFO 19928 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Starting MovieRecommenderSystemApplication using Java 17.0.7 with PID 19928 (/Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system/target/classes started by domhallan in /Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system)
2024-05-24T08:13:45.777-04:00  INFO 19928 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : No active profile set, falling back to 1 default profile: "default"
Constructor invoked...
Setter method invoked..
2024-05-24T08:13:45.954-04:00  INFO 19928 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Started MovieRecommenderSystemApplication in 0.295 seconds (process running for 0.478)
com.domhallan.movierecommendersystem.lesson8.ContentBasedFilter@4564e94b
com.domhallan.movierecommendersystem.lesson8.ContentBasedFilter@4564e94b
com.domhallan.movierecommendersystem.lesson8.ContentBasedFilter@4564e94b
com.domhallan.movierecommendersystem.lesson8.CollaborativeFilter@45673f68
com.domhallan.movierecommendersystem.lesson8.CollaborativeFilter@27abb83e
com.domhallan.movierecommendersystem.lesson8.CollaborativeFilter@69e308c6

Process finished with exit code 0
````

This time the application context will return three different objects. It will create a new object every time we invoke the getBean() method.

![Prototype Bean Scope](./src/main/resources/images/prototype-bean-creation.svg)

Spring creates a singleton bean even before we ask for it while a prototype bean is not created till we request Spring for the bean. In the code widget below, we will print a message in the `ContentBasedFilter` and `CollaborativeFilter` constructors and comment everything in the main method. When the application is run, the output shows that Spring has created a `ContentBasedFilter` bean but the CollaborativeFilter bean has not yet been created.

```java
package com.domhallan.movierecommendersystem.lesson8;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MovieRecommenderSystemApplication {

	public static void main(String[] args) {
		//ApplicationContext manages the beans and dependencies
		ApplicationContext appContext = SpringApplication.run(MovieRecommenderSystemApplication.class, args);
  }
}
```

### Spring vs. Gang of Four singleton[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/4548361560784896#Spring-vs-Gang-of-Four-singleton)

It is important to note that there is a difference between the Spring singleton and the Gang of Four (GoF) singleton design patterns. The singleton design pattern as specified by the GoF means one bean per JVM. However, in Spring it means one bean per application context. By the GoF definition, even if there were more than one application contexts running on the same JVM, there would still be only one instance of the singleton class.

---

### Issues from Bean Scope Lesson:

My code is printing both `content-based filter constructor called` and `collaborative filter constructor called` messages. However, the instructor's output only shows the `content-based filter constructor called` message. Why is this happening?

1. **Default Bean Scope**:
   - By default, Spring beans are singleton-scoped. This means that a single instance of the bean is created and shared throughout the application context.
2. **Prototype Scope**:
   - Beans with prototype scope are created anew each time they are requested from the application context.

### My Current Setup:

- **ContentBasedFilter**:
   - This bean is singleton-scoped by default because you did not specify a different scope.
   - The constructor is called when the application context is initialized.
- **CollaborativeFilter**:
   - This bean is prototype-scoped as specified by `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)`.
   - The constructor is called each time the bean is requested from the application context.

### Instructor's Output:

The instructor's output only shows the `content-based filter constructor called` message. This suggests that the `CollaborativeFilter` bean was not requested during the application startup, hence its constructor was not called.

### Your Output:

Your output shows both `content-based filter constructor called` and `collaborative filter constructor called`. This indicates that both beans were instantiated during the application startup.

### Explanation:

1. **Application Context Initialization**:
   - When the Spring application context is initialized, it creates instances of all singleton-scoped beans. This is why the `ContentBasedFilter` constructor is called.
2. **Prototype Bean Request**:
   - The `CollaborativeFilter` constructor is called because the bean is requested during the application startup. This could be due to some code in your application that explicitly requests the `CollaborativeFilter` bean.
### Verifying the Bean Requests:

To understand why both constructors are being called, you should check the `MovieRecommenderSystemApplication` class and any other configuration classes or components that might be requesting these beans.

### Steps to Align with Instructor's Output:

1. **Check Bean Requests**:
   - Ensure that the `CollaborativeFilter` bean is not being requested during the application startup. This includes checking any `@Autowired` fields, `@Bean` methods, or explicit `getBean` calls in your configuration.
2. **Modify `MovieRecommenderSystemApplication`**:
   - Comment out or remove any code that requests the `CollaborativeFilter` bean during startup.

Here is the modified `MovieRecommenderSystemApplication` class for reference:

```java
package com.domhallan.movierecommendersystem.lesson8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MovieRecommenderSystemApplication {

    public static void main(String[] args) {
        // ApplicationContext manages the beans and dependencies
        ApplicationContext appContext = SpringApplication.run(MovieRecommenderSystemApplication.class, args);

        // Retrieve singleton bean from application context thrice
         ContentBasedFilter cbf1 = appContext.getBean(ContentBasedFilter.class);
         ContentBasedFilter cbf2 = appContext.getBean(ContentBasedFilter.class);
         ContentBasedFilter cbf3= appContext.getBean(ContentBasedFilter.class);

        // Retrieve prototype bean from application context thrice
         CollaborativeFilter cf1 = appContext.getBean(CollaborativeFilter.class);
         CollaborativeFilter cf2 = appContext.getBean(CollaborativeFilter.class);
         CollaborativeFilter cf3 = appContext.getBean(CollaborativeFilter.class);

        // Print the beans to verify
         System.out.println(cf1);
         System.out.println(cf2);
         System.out.println(cf3);
    }
}
```

### Conclusion:

- **Singleton Beans**: Created during application context initialization.
- **Prototype Beans**: Created when explicitly requested.

By ensuring that the `CollaborativeFilter` bean is not requested during 
startup, you should see only the `content-based filter constructor called` 
message, aligning my output with the instructor's.

# Mixing Bean Scope

Learn what happens when the dependency of a singleton bean has prototype scope and how to get the correct output.

We'll cover the following:

- Singleton bean with prototype dependency
- Proxy
- `@Lookup` annotation

## Singleton bean with prototype dependency[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/4828164167827456#Singleton-bean-with-prototype-dependency)

In this lesson, we will discuss an interesting problem of mixing bean scopes. Sometimes, a bean has singleton scope but its dependency has prototype scope. An example is the content-based filter which recommends movies based on item-to-item similarity. Our basic implementation of the content-based filter compares different movies and assigns a similarity score. Hence, `Movie` is a dependency of the `ContentBasedFilter` class.

The `ContentBasedFilter` bean has singleton scope because we need only one instance of the filter. However, the `Movie` bean has prototype scope because we need more than one objects of this class.

![Singleton Bean with Prototype Dependency](./src/main/resources/images/singleton-with-proto-deps.svg)

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-24T09:26:38.055-04:00  INFO 24315 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Starting MovieRecommenderSystemApplication using Java 17.0.7 with PID 24315 (/Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system/target/classes started by domhallan in /Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system)
2024-05-24T09:26:38.057-04:00  INFO 24315 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : No active profile set, falling back to 1 default profile: "default"
content-based filter constructor called
Movie constructor called
2024-05-24T09:26:38.233-04:00  INFO 24315 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Started MovieRecommenderSystemApplication in 0.299 seconds (process running for 0.478)

ContentBasedFilter bean with singleton scope
com.domhallan.movierecommendersystem.lesson9.ContentBasedFilter@3b8ee898

Movie bean with prototype scope
com.domhallan.movierecommendersystem.lesson9.Movie@7d151a
com.domhallan.movierecommendersystem.lesson9.Movie@7d151a
com.domhallan.movierecommendersystem.lesson9.Movie@7d151a

ContentBasedFilter instances created: 1
Movie instances created: 1
```

The output of the above code shows that the same `Movie` bean is returned 
every time. Moreover, the number of instances of the prototype bean created 
is one instead of three. A singleton bean is created when the context is 
loaded. The `Movie` constructor was called by Spring when it was creating the `ContentBasedFilter` bean. The prototype bean injected into the singleton bean at the time of creation of the singleton bean when the container initializes it. This explains the following messages in the output:

```log
ContentBasedFilter constructor called
Movie constructor called
```

> 🧠 When a prototype bean is injected into a singleton bean, it loses its prototype behavior and acts as a singleton.

The same instance of the bean is returned by the application context every 
time it is requested using the `getMovie()` method.

![Spring container](./src/main/resources/images/spring-container.svg)

## Proxy[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/4828164167827456#Proxy)

Right now, Spring cannot inject the prototype bean into the singleton bean after it has been created. This problem can be solved in a number of ways. One of them is by using a **proxy**. We declare the bean with prototype scope as a proxy using the `proxyMode` element inside the `@Scope` annotation.

`@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode=ScopedProxyMode.TARGET_CLASS)`

In Spring, when a prototype bean is required in a singleton
bean, a proxy object is autowired instead of directly wiring the
prototype bean. This proxy serves as an intermediary,
ensuring that each time the prototype bean is requested, the
Spring container dynamically provides a fresh instance to the
singleton bean through the proxy, enabling the creation of a
new prototype bean instance upon each method call on the proxy.

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-05-24T09:39:58.309-04:00  INFO 43594 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Starting MovieRecommenderSystemApplication using Java 17.0.7 with PID 43594 (/Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system/target/classes started by domhallan in /Users/domhallan/learning/educative/guide-to-spring-boot/movie-recommender-system)
2024-05-24T09:39:58.311-04:00  INFO 43594 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : No active profile set, falling back to 1 default profile: "default"
content-based filter constructor called
2024-05-24T09:39:58.492-04:00  INFO 43594 --- [movie-recommender-system] [           main] .d.m.l.MovieRecommenderSystemApplication : Started MovieRecommenderSystemApplication in 0.299 seconds (process running for 0.495)

ContentBasedFilter bean with singleton scope
com.domhallan.movierecommendersystem.lesson9.ContentBasedFilter@6d64b553

Movie bean with prototype scope
Movie constructor called
com.domhallan.movierecommendersystem.lesson9.Movie@796d3c9f
Movie constructor called
com.domhallan.movierecommendersystem.lesson9.Movie@6bff19ff
Movie constructor called
com.domhallan.movierecommendersystem.lesson9.Movie@41e1455d

ContentBasedFilter instances created: 1
Movie instances created: 3

Process finished with exit code 0
```

As can be seen from the output, the singleton bean constructor is called when the `ContentBasedFilter` object is initialized, but the `Movie` constructor isn’t called at that time. The `Movie` constructor is called whenever the proxy object gets used (as in the `println` statements in lines **25–27**).

## @Lookup[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/4828164167827456#Lookup)

Another method is by using the `@Lookup` annotation on the `getMovie()` method. This annotation tells Spring to return an instance of `Movie` type. It is essentially the same as `beanFactory.getBean(Movie.class)`.

One thing to consider is that singleton scope minimizes the number of objects created so the scope should only be changed where necessary. If there are more objects, there will be an impact on the memory used as well as on garbage collection.

## `@ComponentScan` Annotation

Spring does a component scan to search for the beans that it manages. In a Spring application, the `@ComponentScan` annotation without any argument tells Spring to scan the current package as well as any sub-packages that might exist. Spring detects all classes marked with the `@Component`, `@Repository`, `@Service`, and `@Controller` annotations during component scan.

## `@SpringBootApplication`[#](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/5531348880326656#SpringBootApplication)

In a Spring application, `@ComponentScan` is used along with the `@Configuration` annotation. In a Spring Boot application, component scan happens implicitly. The `@SpringBootApplication` annotation is a combination of three annotations:

- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

`@SpringBootApplication` by default, searches the package where it is present, as well as all the sub-packages. If a bean is present in a package other than the base package or its sub-packages, it will not be found. If we want Spring to find beans defined in other packages, we need to use the `@ComponentScan` annotation and provide the path of the package where we want Spring to look for the beans.

> 💡The `MovieRecommenderSystemApplication` class has the
> `@SpringBootApplication` annotation which means that com.domhallan.
> movierecommendersystem.lesson9 and its subpackages will be scanned for
> beans. The `containsBean()` method which will return a `Boolean` value, is
> used to check if the bean is found during component scanning.

```java
package com.domhallan.movierecommendersystem.lesson9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MovieRecommenderSystemApplication {

	public static void main(String[] args) {

		//ApplicationContext manages the beans and dependencies
		ApplicationContext appContext = SpringApplication.run(MovieRecommenderSystemApplication.class, args);
		System.out.println("ContentBasedFilter bean found = " + appContext.containsBean("contentBasedFilter"));
		System.out.println("CollaborativeFilter bean found = " + appContext.containsBean("collaborativeFilter"));
	}
}
```

## `@ComponentScan` for specific package[](https://www.educative.io/module/page/O7rwGNTE1LJD4RVVx/10370001/5666917543313408/5531348880326656#ComponentScan-for-specific-package)

We need to guide Spring to search the `com.domhallan.movierecommendersystem.
lesson10` package and its sub-packages for beans. We can do this by using 
the `@ComponentScan` annotation and providing the base package as an argument.

```java

## What is Spring Boot?

Spring Boot is a framework that simplifies the development of Java applications. It provides a set of tools and conventions that allow developers to create production-ready applications quickly and with minimal configuration.

### Key Features of Spring Boot

1. **Auto-Configuration**: Spring Boot automatically configures the application based on the dependencies present in the classpath. This eliminates the need for manual configuration and reduces boilerplate code.
2. **Standalone**: Spring Boot applications can be run as standalone JAR files, making deployment and execution simple.
3. **Embedded Servers**: Spring Boot includes embedded servers like Tomcat, Jetty, and Undertow, allowing applications to be deployed without the need for an external server.
4. **Production-Ready**: Spring Boot provides features like health checks, metrics, and externalized configuration that make applications production-ready out of the box.
