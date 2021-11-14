package framework.config

case class ConfigEntry(
    interface: String,
    implementation: String,
    params: Option[List[String]]
)
