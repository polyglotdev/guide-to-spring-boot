package com.domhallan.movierecommendersystem.lesson10;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Movie {
  private static int instances = 0;

  private int id;
  private String name;
  private String genre;
  private String producer;

  public Movie() {
    super();
    instances++;
    System.out.println("Movie constructor called");
  }

  public static int getInstances(){
    return Movie.instances;
  }

  public double movieSimilarity(int movie1, int movie2) {

    //if genres are same add 0.3 to similarity
    //if producers are same add 0.5 to similarity

    return 0.0;
  }
}
