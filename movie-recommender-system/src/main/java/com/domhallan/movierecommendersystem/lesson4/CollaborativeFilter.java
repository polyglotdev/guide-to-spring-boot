package com.domhallan.movierecommendersystem.lesson4;

public class CollaborativeFilter implements Filter {
  public String[] getRecommendations(String movie) {
    //logic of content based filter
    return new String[] {"Happy Feet", "Ice Age", "Shark Tale"};
  }
}
