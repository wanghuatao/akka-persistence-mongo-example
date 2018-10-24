scalaVersion := "2.12.6"
lazy val akkaVersion = "2.5.12"

// Akka Actor
libraryDependencies += "com.typesafe.akka" % "akka-actor_2.12" % akkaVersion

// Akka sl4j (logging)
libraryDependencies += "com.typesafe.akka" % "akka-slf4j_2.12" % akkaVersion
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.22"

// Akka Persistence
libraryDependencies += "com.typesafe.akka" % "akka-persistence_2.12" % akkaVersion
// On file system
//libraryDependencies += "org.iq80.leveldb"            % "leveldb"          % "0.7"
//libraryDependencies += "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
// In MongoDB using the reactive mongo driver

libraryDependencies += "com.github.scullxbones" %% "akka-persistence-mongo-rxmongo" % "2.1.0"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.12"

libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.12.4"


