package com.domhallan.movierecommendersystem.lesson1;

public class RecommenderImplementation {
  public String[] recommendMovies(String movie) {
    var name = "Niecy";
    System.out.println(name);
    ContentBasedFilter filter = new ContentBasedFilter();
    return filter.getRecommendations(movie);
  }
}
