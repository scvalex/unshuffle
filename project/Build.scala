import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "Unshuffle",
    version := "0.2",
    versionCode := 0,
    scalaVersion := "2.9.2",
    platformName in Android := "android-14",
    javacOptions ++= Seq("-source", "1.6", "-target", "1.6"),
    scalacOptions ++= Seq("-deprecation", "-unchecked")
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++ Seq (
      compileOrder := CompileOrder.JavaThenScala
    ) ++ Seq(
      proguardOption in Android := "-keep class scala.Function1 -keep class scala.Function4 -keep class scala.Tuple2 -keep class android.support.v4.view.ViewPager -keep class scala.collection.immutable.List"
    ) ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "scvalex"
    ) ++
    Github.settings ++ Seq (
      githubRepo in Android := "scvalex/unshuffle"
    )
}

object AndroidBuild extends Build {
  lazy val main = Project (
    "Unshuffle",
    file("."),
    settings = General.fullAndroidSettings
  )
}
