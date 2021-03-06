name := "bar-crawl"

version := "1.0-SNAPSHOT"

resolvers += "OSGEO GeoTools repo" at "http://download.osgeo.org/webdav/geotools"

resolvers += "OpenGEO repo" at "http://repo.opengeo.org"

resolvers += "Hibernate Spatial repo" at "http://www.hibernatespatial.org/repository"

resolvers += "foursquare-api" at "http://foursquare-api-java.googlecode.com/svn/repository"

libraryDependencies ++= Seq(
  javaCore,
  cache,
  "com.h2database" % "h2" % "1.3.172",
  "org.opengeo" % "geodb" % "0.7",
  "org.springframework" % "spring-context" % "3.2.4.RELEASE",
  "org.springframework" % "spring-orm" % "3.2.4.RELEASE",
  "org.springframework" % "spring-jdbc" % "3.2.4.RELEASE",
  "org.springframework" % "spring-tx" % "3.2.4.RELEASE",
  "org.springframework" % "spring-test" % "3.2.4.RELEASE" % "test",
  "org.springframework.data" % "spring-data-jpa" % "1.4.1.RELEASE",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.6.Final",
  "org.hibernate" % "hibernate-spatial" % "4.0-M1" exclude("org.postgis", "postgis-jdbc"),
  "com.vividsolutions" % "jts" % "1.12",
  "org.geotools" % "gt-api" % "10.1",
  "org.geotools" % "gt-main" % "10.1",
  "org.geotools" % "gt-referencing" % "10.1",
  "org.geotools" % "gt-epsg-hsql" % "10.1",
  "org.geotools" % "gt-epsg-extension" % "10.1",
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "3.0.1",
  "org.webjars" % "jquery" % "2.0.3-1",
  "fi.foyt" %"foursquare-api" % "1.0.2" exclude ("com.google.appengine", "appengine-api-1.0-sdk")
)

play.Project.playJavaSettings

