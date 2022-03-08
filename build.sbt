name := "json-schema-validator"

version := "1.0"

lazy val `json-schema-validator` = (project in file(".")).enablePlugins(PlayScala)


resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq( ehcache , ws , specs2 % Test , guice,
  "com.typesafe.play"    %% "play-slick"              % "5.0.0",
  "com.typesafe.play"    %% "play-slick-evolutions"   % "5.0.0",
  "org.xerial"            % "sqlite-jdbc"             % "3.36.0.2",
  "com.github.fge"        % "json-schema-validator"   % "2.2.6",
  "com.google.code.gson"  % "gson"                    % "2.9.0"
)
      