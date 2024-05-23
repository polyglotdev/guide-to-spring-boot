package com.domhallan.movierecommendersystem.lesson6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RecommenderImplementation {

  @Autowired
  @Qualifier("CBF")
  private Filter contentBasedFilter;

  public String [] recommendMovies (String movie) {
    System.out.println("\nName of the filter in use: " + contentBasedFilter + "\n");
    return contentBasedFilter.getRecommendations("Finding Dory");
  }
}
