package movieFinder
import framework.config.ConfigReader
import framework.container.IoCContainer

object Main {
  def main(args: Array[String]): Unit = {

    val configPath = "src/main/resources/movieFinder.json"
    val config = ConfigReader.readConfig(configPath)

    val ioCContainer = IoCContainer()
    config.foreach(ioCContainer.registerComponentImplementation)

    val movieFinder: MovieFinder =
      ioCContainer
        .getComponentImplementation(classOf[MovieFinder])

    movieFinder.findAny()
  }
}
