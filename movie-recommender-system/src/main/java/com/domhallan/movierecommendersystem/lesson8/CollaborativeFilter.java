package com.domhallan.movierecommendersystem.lesson8;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Collaborative filtering implementation of the Filter interface
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CollaborativeFilter implements Filter {

  public CollaborativeFilter() {
    super();
    System.out.println("collaborative filter constructor called");
  }

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
