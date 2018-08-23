val javaDeps = Seq(
  "net.dv8tion"       % "JDA"        % "3.7.1_386",
  "com.mitchtalmadge" % "ascii-data" % "1.2.0"
)

val scalaDeps = Seq(
  "io.suzaku"    %% "diode"            % "1.1.3",
  "org.tpolecat" %% "doobie-core"      % "0.5.3",
  "org.tpolecat" %% "doobie-postgres"  % "0.5.3"
)

resolvers += Resolver.jcenterRepo

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "one.beatsbot",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    scalacOptions ++= Seq("-language:implicitConversions"),
    name := "boi",
    mainClass in assembly := Some("one.beatsbot.Bot"),
    libraryDependencies ++= javaDeps ++ scalaDeps,
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs@_*) =>
        xs.map(_.toLowerCase) match {
          case ("manifest.mf" :: Nil) |
               ("index.list" :: Nil) |
               ("dependencies" :: Nil) |
               ("license" :: Nil) |
               ("notice" :: Nil) => MergeStrategy.discard
          case _ => MergeStrategy.first // was 'discard' previousely
        }
      case "reference.conf" => MergeStrategy.concat
      case _ => MergeStrategy.first
    }
  )
