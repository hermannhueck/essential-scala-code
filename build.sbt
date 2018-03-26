name := "essential-scala-code"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.5"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",   // source files are in UTF-8
  "-deprecation",         // warn about use of deprecated APIs
  "-unchecked",           // warn about unchecked type parameters
  "-feature",             // warn about misused language features
  "-Xlint",               // enable handy linter warnings
  // "-Xfatal-warnings",     // turn compiler warnings into errors
  // "-language:higherKinds",// allow higher kinded types without `import scala.language.higherKinds`
  // "-Ypartial-unification" // allow the compiler to unify type constructors of different arities
)
