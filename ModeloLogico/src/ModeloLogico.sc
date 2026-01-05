val circeVersion = "0.14.5"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion
)

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._


case class CrewMember(id: Int, name: String, job: String, department: String)
case class MovieCollection(
                            id: Int,
                            name: String,
                            poster_path: String,
                            backdrop_path: Option[String],
                            crew: Option[List[CrewMember]] = None
                          )
val rawJson = """{
  "id": 96665,
  "name": "Dumb and Dumber Collection",
  "poster_path": "/fdB86tl7SKIBWZzm0CbrYjOdE1K.jpg",
  "backdrop_path": "/saUnj5K6buVafDfLfTozDH2eqdC.jpg"
}"""

val decodeResult = decode[MovieCollection](rawJson)

decodeResult match {
  case Right(collection) => println(s"Nombre: ${collection.name}")
  case Left(error) => println(s"Error: $error")
}

def parseCrew(crewString: String): List[CrewMember] = {
  decode[List[CrewMember]](crewString).getOrElse(List.empty)
}


val rawCrewData = """[{"id": 123, "name": "Peter Farrelly", "job": "Director", "department": "Directing"}]"""
val cleanedCrew = parseCrew(rawCrewData)

val directors = cleanedCrew.filter(_.job == "Director").map(_.name)

def cleanCollection(data: MovieCollection): MovieCollection = {
  data.copy(
    name = data.name.trim,
    poster_path = if (data.poster_path.startsWith("/")) data.poster_path else "unknown",
    crew = data.crew.map(_.filter(_.name.nonEmpty))
  )
}