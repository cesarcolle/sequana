name := "sequana"

version := "0.1"
scalaVersion := "2.12.6"

lazy val buildSettings = Seq(
  organization        := "com.github.cesarcolle.sequana",
  version             := "0.1",
)


enablePlugins(Antlr4Plugin)

antlr4GenListener in Antlr4 := true

antlr4GenVisitor in Antlr4 := true

antlr4Dependency in Antlr4 := "org.antlr" % "antlr4" % "4.5"
