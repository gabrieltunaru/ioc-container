package dataPipe

import dataPipe.decoding.{ListDecoder, ColonListDecoder}
import framework.config.ConfigReader
import framework.container.IoCContainer
import framework.utils.FileReader

object Main {
  def main(args: Array[String]): Unit = {

    val configPath = "src/main/resources/datapipe.json"
    val config = ConfigReader.readConfig(configPath)

    val ioCContainer = IoCContainer()
    config.foreach(ioCContainer.registerComponentImplementation)

    val pipe = ioCContainer.getComponentImplementation(classOf[DataPipe])
    val res = pipe.pipe("this:is:a:pipe")
    println(res)
  }

}
