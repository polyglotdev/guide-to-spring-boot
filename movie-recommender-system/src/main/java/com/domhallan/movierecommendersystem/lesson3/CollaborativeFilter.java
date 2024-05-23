package com.domhallan.movierecommendersystem.lesson3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Collaborative filtering implementation of the Filter interface
 */
@Component
@Primary
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
