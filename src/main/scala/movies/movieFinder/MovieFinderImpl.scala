package movies.movieFinder

import framework.utils.FileReader

class MovieFinderImpl(val fileName: String) extends MovieFinder {
  override def findAny(): String = {
    FileReader.readAll("src/main/resources/movies.txt")
  }
}
