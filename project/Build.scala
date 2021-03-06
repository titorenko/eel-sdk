import com.typesafe.sbt.pgp.PgpKeys
import sbt.Keys._
import sbt._

import scala.languageFeature.experimental
import scala.languageFeature.experimental.macros

object Build extends Build {

  val org = "io.eels"

  val AvroVersion = "1.8.2"
  val ConfigVersion = "1.3.1"
  val Elastic4sVersion = "5.5.3"
  val ExtsVersion = "1.54.0"
  val H2Version = "1.4.196"
  val HadoopVersion = "2.7.4"
  val HiveVersion = "1.2.2"
  val JacksonVersion = "2.9.1"
  val KafkaVersion = "0.11.0.1"
  val KuduVersion = "1.5.0"
  val Log4jVersion = "2.7"
  val MysqlVersion = "5.1.44"
  val OrcVersion = "1.4.0"
  val ParquetVersion = "1.8.1"
  val ScalatestVersion = "3.0.3"
  val Slf4jVersion = "1.7.25"
  val SparkVersion = "2.1.1"
  val UnivocityVersion = "2.5.7"
  val HBaseVersion = "1.4.0"
  // val HbaseTestVersion = "1.2.2"
  val HbaseTestVersion = HBaseVersion


  val hiveSettings = Seq(
    libraryDependencies ++= Seq(
      "org.apache.hadoop" % "hadoop-mapreduce-client-core" % HadoopVersion,
      "org.apache.hive" % "hive-common" % HiveVersion,
      "org.apache.hive" % "hive-exec" % HiveVersion exclude("org.pentaho", "pentaho-aggdesigner-algorithm") exclude("org.apache.calcite", "calcite-core") exclude("org.apache.calcite", "calcite-avatica") exclude("org.apache.logging.log4j", "log4j-slf4j-impl"),
      //      "com.github.sakserv" % "hadoop-mini-clusters" % "0.1.14"  % "test" exclude("org.apache.hadoop", "hadoop-client"),
      //      "com.github.sakserv" % "hadoop-mini-clusters-hdfs" % "0.1.14" % "test" exclude("org.apache.hadoop", "hadoop-client"),
      //      "com.github.sakserv" % "hadoop-mini-clusters-hivemetastore" % "0.1.13" % "test" exclude("org.apache.hadoop", "hadoop-client"),
      "org.apache.hive" % "hive-common" % HiveVersion,
      "org.apache.hadoop" % "hadoop-common" % HadoopVersion % "test" classifier "tests",
      "org.apache.hadoop" % "hadoop-common" % HadoopVersion % "test",
      "org.apache.hadoop" % "hadoop-hdfs" % HadoopVersion % "test" classifier "tests",
      "org.apache.hadoop" % "hadoop-hdfs" % HadoopVersion % "test",
      "org.apache.logging.log4j" % "log4j-api" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-core" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % Log4jVersion % "test",
      "org.mockito" % "mockito-core" % "2.10.0" % "test"
    )
  )

  val yarnSettings = Seq(
    libraryDependencies ++= Seq(
      "org.apache.hadoop" % "hadoop-yarn" % HadoopVersion,
      "org.apache.hadoop" % "hadoop-mapreduce" % HadoopVersion,
      "org.apache.hadoop" % "hadoop-mapreduce-client" % HadoopVersion,
      "org.apache.hadoop" % "hadoop-mapreduce-client-core" % HadoopVersion,
      "org.apache.hadoop" % "hadoop-yarn-client" % HadoopVersion,
      "org.apache.hadoop" % "hadoop-yarn-server-resourcemanager" % HadoopVersion,
      "org.apache.logging.log4j" % "log4j-api" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-core" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % Log4jVersion % "test"
    )
  )

  val coreSettings = Seq(
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % JacksonVersion,
      "com.univocity" % "univocity-parsers" % UnivocityVersion,
      "org.apache.hadoop" % "hadoop-common" % HadoopVersion exclude("org.slf4j", "slf4j-log4j12"),
      "org.apache.hadoop" % "hadoop-hdfs" % HadoopVersion,
      "com.h2database" % "h2" % H2Version,
      "org.apache.avro" % "avro" % AvroVersion,
      "org.apache.parquet" % "parquet-avro" % ParquetVersion
    )
  )

  val orcSettings = Seq(
    libraryDependencies ++= Seq(
      "org.apache.orc" % "orc-core" % OrcVersion
    )
  )

  //  val kafkaSettings = Seq(
  //    libraryDependencies ++= Seq(
  //      "org.apache.kafka"            %  "kafka-clients"                  % KafkaVersion,
  //      "net.manub"                   %% "scalatest-embedded-kafka"       % "0.15.0"     % "test"
  //    )
  //  )

  val clouderaSettings = Seq(
    resolvers += "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos",
    libraryDependencies ++= Seq(
      "org.scalaj" %% "scalaj-http" % "2.3.0",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % JacksonVersion
    )
  )

  val kuduSettings = Seq(
    libraryDependencies ++= Seq(
      "org.apache.kudu" % "kudu-client" % KuduVersion
    )
  )

  val esSettings = Seq(
    libraryDependencies ++= Seq(
      "com.sksamuel.elastic4s" %% "elastic4s-http" % Elastic4sVersion
    )
  )

  val sparkSettings = Seq(
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % SparkVersion,
      "org.apache.spark" %% "spark-sql" % SparkVersion
    )
  )

  val hbaseSettings = Seq(
    libraryDependencies ++= Seq(
      "org.apache.hbase" % "hbase-client" % HBaseVersion,
      "org.apache.hbase" % "hbase-common" % HBaseVersion,
      "org.apache.hadoop" % "hadoop-common" % HadoopVersion % "test" classifier "tests",
      "org.apache.hadoop" % "hadoop-hdfs" % HadoopVersion % "test" classifier "tests",
      "org.apache.hadoop" % "hadoop-hdfs" % HadoopVersion % "test",
      "org.apache.hadoop" % "hadoop-common" % HadoopVersion % "test" classifier "tests",
      "org.apache.hadoop" % "hadoop-common" % HadoopVersion % "test",
      "org.apache.hbase" % "hbase-common" % HbaseTestVersion % "test" classifier "tests",
      "org.apache.hbase" % "hbase-common" % HbaseTestVersion % "test",
      "org.apache.hbase" % "hbase-server" % HbaseTestVersion % "test" classifier "tests",
      "org.apache.hbase" % "hbase-server" % HbaseTestVersion % "test",
      "org.apache.hbase" % "hbase-hadoop-compat" % HbaseTestVersion % "test" classifier "tests",
      "org.apache.hbase" % "hbase-hadoop-compat" % HbaseTestVersion % "test",
      "org.apache.hbase" % "hbase-hadoop2-compat" % HbaseTestVersion % "test" classifier "tests",
      "org.apache.hbase" % "hbase-hadoop2-compat" % HbaseTestVersion % "test",
      "org.apache.hbase" % "hbase-metrics-api" % HbaseTestVersion % "test",
      "org.apache.hbase" % "hbase-metrics-api" % HbaseTestVersion % "test" classifier "tests",
      "org.apache.hbase" % "hbase-metrics" % HbaseTestVersion % "test",
      "org.apache.hbase" % "hbase-metrics" % HbaseTestVersion % "test" classifier "tests",
      "org.apache.logging.log4j" % "log4j-api" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-core" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % Log4jVersion % "test",
      "commons-dbcp" % "commons-dbcp" % "1.4" % "test",
      "org.mockito" % "mockito-core" % "2.10.0" % "test",
      "org.apache.hive" % "hive-common" % HiveVersion,
      "org.apache.hive" % "hive-exec" % HiveVersion exclude("org.pentaho", "pentaho-aggdesigner-algorithm") exclude("org.apache.calcite", "calcite-core") exclude("org.apache.calcite", "calcite-avatica") exclude("org.apache.logging.log4j", "log4j-slf4j-impl")

    )
  )

  val rootSettings = Seq(
    organization := org,
    scalaVersion := "2.11.12",
    publishMavenStyle := true,
    resolvers += Resolver.mavenLocal,
    publishArtifact in Test := false,
    parallelExecution in Test := false,
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
    javacOptions := Seq("-source", "1.8", "-target", "1.8"),
    fork in test := false,
    concurrentRestrictions in Global += Tags.limit(Tags.Test, 1),
    javaOptions in test ++= Seq("-Xms256M", "-Xmx2G", "-XX:MaxPermSize=1024M", "-XX:+UseConcMarkSweepGC"),
    sbtrelease.ReleasePlugin.autoImport.releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    sbtrelease.ReleasePlugin.autoImport.releaseCrossBuild := true,
    testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-l", "kudu"),
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "com.typesafe" % "config" % ConfigVersion,
      "com.sksamuel.exts" %% "exts" % ExtsVersion,
      "org.slf4j" % "slf4j-api" % Slf4jVersion,
      "commons-lang" % "commons-lang" % "2.6",
      "org.apache.logging.log4j" % "log4j-api" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-core" % Log4jVersion % "test",
      "org.apache.logging.log4j" % "log4j-slf4j-impl" % Log4jVersion % "test",
      "mysql" % "mysql-connector-java" % MysqlVersion % "test",
      "org.scalatest" %% "scalatest" % ScalatestVersion % "test"
    ),
    excludeDependencies += "org.slf4j" % "slf4j-log4j12",
    publishTo <<= version {
      (v: String) =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    pomExtra := {
      <url>https://github.com/51zero/eel-sdk</url>
        <licenses>
          <license>
            <name>MIT</name>
            <url>https://opensource.org/licenses/Apache2</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:51zero/eel-sdk.git</url>
          <connection>scm:git@github.com:51zero/eel-sdk.git</connection>
        </scm>
        <developers>
          <developer>
            <id>garyfrost</id>
            <name>Gary Frost</name>
            <url>https://github.com/51zero</url>
          </developer>
          <developer>
            <id>hannesmiller</id>
            <name>Hannes Miller</name>
            <url>https://github.com/51zero</url>
          </developer>
        </developers>
    }
  )

  lazy val root = Project("eel", file("."))
    .settings(rootSettings: _*)
    .settings(name := "eel")
    .aggregate(
      core,
      schema,
      orc,
      hbase,
      hive,
      yarn,
      spark,
      kudu,
      elasticsearch,
      cloudera
      //      kafka,
    )

  lazy val schema = Project("eel-schema", file("eel-schema"))
    .settings(rootSettings: _*)
    .settings(name := "eel-schema")

  lazy val core = Project("eel-core", file("eel-core"))
    .settings(rootSettings: _*)
    .settings(coreSettings: _*)
    .settings(name := "eel-core")
    .dependsOn(schema)

  lazy val orc = Project("eel-orc", file("eel-orc"))
    .settings(rootSettings: _*)
    .settings(orcSettings: _*)
    .settings(name := "eel-orc")
    .dependsOn(core)

  lazy val hive = Project("eel-hive", file("eel-hive"))
    .settings(rootSettings: _*)
    .settings(hiveSettings: _*)
    .settings(name := "eel-hive")
    .dependsOn(core, orc)

  lazy val yarn = Project("eel-yarn", file("eel-yarn"))
    .settings(rootSettings: _*)
    .settings(yarnSettings: _*)
    .settings(name := "eel-yarn")
    .dependsOn(core)

  lazy val spark = Project("eel-spark", file("eel-spark"))
    .settings(rootSettings: _*)
    .settings(sparkSettings: _*)
    .settings(name := "eel-spark")
    .dependsOn(core)

  lazy val cloudera = Project("eel-cloudera", file("eel-cloudera"))
    .settings(rootSettings: _*)
    .settings(clouderaSettings: _*)
    .settings(name := "eel-cloudera")
    .dependsOn(hive)

  //  lazy val kafka = Project("eel-kafka", file("eel-kafka"))
  //    .settings(rootSettings: _*)
  //    .settings(kafkaSettings: _*)
  //    .settings(name := "eel-kafka")
  //    .dependsOn(core)

  lazy val kudu = Project("eel-kudu", file("eel-kudu"))
    .settings(rootSettings: _*)
    .settings(kuduSettings: _*)
    .settings(name := "eel-kudu")
    .dependsOn(core)

  lazy val elasticsearch = Project("eel-elasticsearch", file("eel-elasticsearch"))
    .settings(rootSettings: _*)
    .settings(esSettings: _*)
    .settings(name := "eel-elasticsearch")
    .dependsOn(core)

  lazy val hbase = Project("eel-hbase", file("eel-hbase"))
    .settings(rootSettings: _*)
    // .settings(hiveSettings: _*)
    .settings(hbaseSettings: _*)
    .settings(name := "eel-hbase")
    .dependsOn(core)

}
