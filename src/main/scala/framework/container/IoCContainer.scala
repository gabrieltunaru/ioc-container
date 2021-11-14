package framework.container

import framework.config.ConfigEntry

import scala.collection.mutable

class IoCContainer {
  case class Impl(implementation: Class[_], params: Option[List[String]])

  private val classMapper = mutable.Map.empty[Class[_], Impl]

  def registerComponentImplementation(configEntry: ConfigEntry): Unit = {
    val interface = Class.forName(configEntry.interface)
    val implementation = Class.forName(configEntry.implementation)
    classMapper.addOne((interface, Impl(implementation, configEntry.params)))
  }

  def getRegisteredParams(cls: Class[_]): Array[Class[_]] = {
    val constructor = cls.getConstructors
      .find(constructor =>
        constructor.getParameterTypes
          .forall(foundClass => classMapper.contains(foundClass))
      )
      .getOrElse(
        throw new Error(s"No suitable constructor found for ${cls.getName}")
      )
    constructor.getParameterTypes
  }

  def getComponentImplementation[T](
      requestedClass: Class[T]
  ): T = {
    val impl = classMapper.getOrElse(requestedClass, throw new Error)
    val implementation = impl.implementation

    val interfaceParams =
      impl.params match {
        case None    => getRegisteredParams(implementation).toList
        case Some(v) => v.map(_.getClass)
      }

    val implementationParams = impl.params.getOrElse(
      getRegisteredParams(implementation).toList
        .map(getComponentImplementation(_))
    )

    implementation
      .getConstructor(interfaceParams: _*)
      .newInstance(implementationParams: _*)
      .asInstanceOf[T]
  }
}

object IoCContainer {
  def apply() = new IoCContainer()
}
