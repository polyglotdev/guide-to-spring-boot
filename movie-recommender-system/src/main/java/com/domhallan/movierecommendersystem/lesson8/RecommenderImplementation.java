package com.domhallan.movierecommendersystem.lesson8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RecommenderImplementation {

  private Filter filter;

  //constructor injection
  @Autowired
  public RecommenderImplementation(@Qualifier("collaborativeFilter") Filter filter) {
    super();
    this.filter = filter;
    System.out.println("Constructor invoked...");
  }

  //use the injected filter to find recommendations
  public String [] recommendMovies (String movie) {

    //print the name of interface implementation being used
    System.out.println("\nName of the filter in use: " + filter + "\n");
    return filter.getRecommendations("Finding Dory");
  }

}
