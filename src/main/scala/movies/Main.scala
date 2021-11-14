package movies
import framework.config.ConfigReader
import framework.container.IoCContainer
import movies.movieLister.MovieLister

object Main {
  def main(args: Array[String]): Unit = {

    val configPath = "src/main/resources/movieFinder.json"
    val config = ConfigReader.readConfig(configPath)

    val ioCContainer = IoCContainer()
    config.foreach(ioCContainer.registerComponentImplementation)

    val movieLister: MovieLister =
      ioCContainer.getComponentImplementation(classOf[MovieLister])

    movieLister.listAll()
  }
}
