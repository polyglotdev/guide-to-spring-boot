package com.domhallan.movierecommendersystem.lesson9;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Movie {
  private static int instances = 0;

  private int id;
  private String name;
  private String genre;
  private String producer;

  public Movie() {
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
