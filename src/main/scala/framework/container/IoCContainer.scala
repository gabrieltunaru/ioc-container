package framework.container

import framework.config.ConfigEntry

import scala.collection.mutable

class IoCContainer {

  private val classMapper = mutable.Map.empty[Class[_], Class[_]]

  def registerComponentImplementation(configEntry: ConfigEntry): Unit = {
    val interface = Class.forName(configEntry.interface)
    val implementation = Class.forName(configEntry.implementation)
    classMapper.addOne((interface, implementation))
  }

  def getComponentImplementation[T](
      requestedClass: Class[T]
  ): T = {
    val impl = classMapper.getOrElse(requestedClass, throw new Error)
    impl.getConstructor().newInstance().asInstanceOf[T]
  }
}

object IoCContainer {
  def apply() = new IoCContainer()
}
