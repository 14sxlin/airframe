/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wvlet.airframe.tablet.text

import wvlet.airframe.AirframeSpec
import wvlet.airframe.tablet.Tablet._

import scala.io.Source

object TextTabletWriterTest {
  case class Person(id: Int, name: String, phone: Seq[String], address: String)
}

import wvlet.airframe.tablet.text.TextTabletWriterTest._

/**
  *
  */
class TextTabletWriterTest extends AirframeSpec {

  "TextTabletWriter" should {

    val seq = Seq(
      Person(1,
             "leo",
             Seq("xxx-xxxx"),
             """123 Apple Street
               |Cupertino, CA""".stripMargin),
      Person(2,
             "yui",
             Seq("yyy-yyyy", "zzz-zzzz"),
             """456 Orange Avenue
               |Santa Clara, CA""".stripMargin)
    )

    "output object in JSON array format" in {
      val w = seq.toJson
      info(w.mkString("\n"))
    }

    "output object in CSV format" in {
      val csv = seq.toCSV.mkString("\n")

      info(csv)
      val json = CSVTabletReader(Source.fromString(csv)).toJson

      info(json.mkString("\n"))
    }

    "output object in TSV format" in {
      val tsv = seq.toTSV.mkString("\n")
      info(tsv)
      val json = TSVTabletReader(Source.fromString(tsv)).toJson
      info(json.mkString("\n"))
      val csv = TSVTabletReader(Source.fromString(tsv)).toCSV
      info(csv.mkString("\n"))
    }

  }
}
