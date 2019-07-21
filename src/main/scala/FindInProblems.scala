import java.io.File

import org.jsoup.Jsoup
import org.jsoup.select.Elements

import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex
object FindInProblems extends App {
  val reg = new Regex("""(?:Parameters: )(.*?)(?:Returns: )""", "a")

  val fileList = new File("/Users/mas.kimura/topcoder/problems2").listFiles
  val result = fileList.map { f =>
    val html = Source.fromFile(f.getAbsolutePath).mkString
    val doc = Jsoup.parse(html)
    val paramters = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2)").text.split(',').map(_.trim)
    val method = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)").text
    val className = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)").text
    if (className == "Saleswoman") {
      println("class name:  " + className)
      println("method name: " + method)
      println("parameters:  " + paramters.mkString(" "))
      println("file path:   " + f.getAbsolutePath)

    }
  }
}
