package com.domhallan.movierecommendersystem.lesson9;

import com.domhallan.movierecommendersystem.lesson8.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ContentBasedFilter implements Filter {

  private static int instances = 0;

  @Autowired
  private Movie movie;

  public ContentBasedFilter() {
    instances++;
    System.out.println("content-based filter constructor called");
  }

  public Movie getMovie() {
    return movie;
  }

  public static int getInstances() {
    return ContentBasedFilter.instances;
  }

  //getRecommendations takes a movie as input and returns a list of similar movies
  public String[] getRecommendations(String movie) {
    //implement logic of content based filter
    //return movie recommendations
    return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
  }
}
