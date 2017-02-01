scalaVersion := "2.11.8"
lazy val akkaVersion = "2.4.16"

// Akka Actor
libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % akkaVersion

// Akka sl4j (logging)
libraryDependencies += "com.typesafe.akka" % "akka-slf4j_2.11" % akkaVersion
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.22"

// Akka Persistence
libraryDependencies += "com.typesafe.akka" % "akka-persistence_2.11" % akkaVersion
// On file system
//libraryDependencies += "org.iq80.leveldb"            % "leveldb"          % "0.7"
//libraryDependencies += "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
// In MongoDB using the reactive mongo driver
libraryDependencies += "com.github.scullxbones" % "akka-persistence-mongo-rxmongo_2.11" % "1.3.7"

// reactive mongo
libraryDependencies += "org.reactivemongo" % "play2-reactivemongo_2.11" % "0.11.14"
