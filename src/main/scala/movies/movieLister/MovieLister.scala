package movies.movieLister

import movies.movieFinder.MovieFinder

class MovieLister(movieFinder: MovieFinder) {
  def listAll(): Unit = {
    println(movieFinder.findAny())
  }
}
