import sbt._

class Project(info: ProjectInfo) extends PluginProject(info) with AkkaProject {
  val actor = akkaActor
}