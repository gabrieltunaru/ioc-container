package movieFinder

class MovieLister(movieFinder: MovieFinder) {
  def listAll(): Unit = {
    println(movieFinder.findAny())
  }
}
