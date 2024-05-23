package com.domhallan.movierecommendersystem.lesson4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecommenderImplementation {

  @Autowired
  private final Filter filter;

  public RecommenderImplementation(Filter filter) {
    super();
    this.filter = filter;
  }

  public String[] recommendMovies(String movie) {
    System.out.println("Name of the filter in use: " + filter + "\n");
    return filter.getRecommendations("Finding Dory");
  }
}
