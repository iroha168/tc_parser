import java.io.File

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.io.Source
import scala.util.matching.Regex

object FindInSingleFile extends App {
  val reg = new Regex("""View(.{1,50})Problem Statement""", "problem")

  val fileList = new File("/Users/mas.kimura/topcoder/testcases2/testcases").listFiles

  val html = Source.fromFile("/Users/mas.kimura/topcoder/testcases2/notdefences/1563200031.7297668.html").mkString
//  val problemHtml = "/Users/mas.kimura/topcoder/problem_with_title/problem__bloggoSequenceSearch_findPassages.html"
  val doc = Jsoup.parse(html)

//  val returnValues = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)")
  val returnValue  = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)")
  println(returnValue)
  //  println(solution__)
  val sourceCodeElem = doc.select(".problemText")
  val jsoupDoc = Jsoup.parse(sourceCodeElem.html)
  //    jsoupDoc.select("p").before("\\n");
  val replaceStr = jsoupDoc.html.replaceAll("\\\\n", "aaa")
  jsoupDoc.select("br").after("\\n");
  val solutionHtmlStr = replaceStr.replaceAll("\\\\n", "\\\n")
  val solution = Jsoup.parse(solutionHtmlStr
    .replaceAll("aaa", "\\\\n"))
    .wholeText().replaceAll(" "," ")
  println(solution)
}


