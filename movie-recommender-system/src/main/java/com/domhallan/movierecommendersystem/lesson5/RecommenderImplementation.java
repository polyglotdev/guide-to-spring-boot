package com.domhallan.movierecommendersystem.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommenderImplementation {

  @Autowired
  private Filter contentBasedFilter;

  public String [] recommendMovies (String movie) {
    System.out.println("\nName of the filter in use: " + contentBasedFilter + "\n");
    return contentBasedFilter.getRecommendations("Finding Dory");
  }
}
