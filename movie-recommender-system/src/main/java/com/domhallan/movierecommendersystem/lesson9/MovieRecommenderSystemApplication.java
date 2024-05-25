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
