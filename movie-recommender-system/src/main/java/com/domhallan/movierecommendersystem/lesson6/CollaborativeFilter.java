package com.domhallan.movierecommendersystem.lesson6;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Collaborative filtering implementation of the Filter interface
 */
@Component
@Qualifier("CF")
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
