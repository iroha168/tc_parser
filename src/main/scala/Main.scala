import java.io.File

import org.jsoup.Jsoup
import org.jsoup.select.Elements

import scala.io.{BufferedSource, Source}
import scala.util.matching.Regex

object Main extends App {
  //  val reg = new Regex("""(pm.*&)""", "a")
  //  val problemNo= for{
  //    line <-Source.fromFile("/Users/mas.kimura/topcoder/save.txt3").getLines
  //    result <- reg.findAllIn(line).matchData
  //  } yield {
  //    val aGroup =result.group("a")
  //    aGroup.slice(0,aGroup.size - 1)
  //  }
  //  val uniqueProblem = problemNo.toSet
  //  uniqueProblem.foreach{ problem =>
  //    val url = s"https://community.topcoder.com/stat?c=problem_statement&${problem}"
  //    println(url)
  //    Process(s"wget ${url} -P /Users/mas.kimura/topcoder/problems") run
  //  }
  //  var i = 0
  //  val fileList = new File("/Users/mas.kimura/topcoder/problems").listFiles
  //  fileList.foreach{ f =>
  //    new File(f.getAbsolutePath).renameTo(new File(f.getParent + s"/$i.html"))
  //    i += 1
  //  }


  val reg = new Regex("""(?:Parameters: )(.*?)(?:Returns: )""", "a")

  //  val html = Source.fromFile("/Users/mas.kimura/topcoder/testcases2/notdefences/1563214466.7800581.html").mkString
  //  val (fileName, testCases) = fun(html)
  val fileList = new File("/Users/mas.kimura/topcoder/problems2").listFiles
  val result = fileList.map { f =>
    val html = Source.fromFile(f.getAbsolutePath).mkString
    //    val (fileName, testCases) = fun(html)
    //      val file = new PrintWriter(s"/Users/mas.kimura/topcoder/testcases2/formatted/output/${fileName}")
    //      file.write(testCases)
    //      file.close()
    val doc = Jsoup.parse(html)

    val paramters = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2)").text.split(',')
    val method =  doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)").text
    val className = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)").text()
//    val problem = doc.select(".statTextBig").select("td:contains(Problem Statement for)").text().replaceAll("Problem Statement for", "").trim
    if(className== "Saleswoman") {
      println(className)
    }
//    println(className + " : " + f.getAbsolutePath)
    //    reg.findAllIn(doc.body.text).matchData.map(_.group("a"))
  }
  //  val sp= result.flatMap(_.map(_.toString.trim()))
  //  val r = sp.mkString(".").split(',')
  //  r.foreach(println)
  //  println(r.size)
  //  println(r.toSet.size)


  def fun(html: String): (String, String) = {
    var roundTitle = ""
    var problemTitle = ""
    val doc = Jsoup.parse(html)

    val round = doc.select("body > table > tbody > tr > td.bodyText > table.paddingTable > tbody > tr:nth-child(1) > td > table:nth-child(4) > tbody > tr:nth-child(3) > td > a:nth-child(1)")
    val test: Elements = doc.select(
      "body > table > tbody > tr > td.bodyText > table.paddingTable > tbody > tr:nth-child(1) > td > table:nth-child(5) > tbody > tr > td:nth-child(4)")
    //                "body > table > tbody > tr > td.bodyText > table.paddingTable > tbody > tr:nth-child(1) > td > table:nth-child(5) > tbody > tr:nth-child(30) > td:nth-child(2)#
    val sb = new StringBuilder();
    for (i <- 0 until test.size()) {
      if (sb.length() != 0)
        sb.append("\n");
      sb.append(test.get(i).text);
    }
//    val roundName = round.text().replaceAll(" > ", " ")
//      .replaceAll(" ", "_")
//      .replaceAll("/", "_")
//    val problemName = problem.text().replaceAll("   Problem Statement", "")
//      .replaceAll("View ", "")
//      .replaceAll(" ", "_")
//      .replaceAll("/", "_")
//    val fileName = roundName + "-" + problemName
    println(sb)
//    (fileName, sb.toString())
    ???
  }

  def getSource(filePath: String): Option[BufferedSource] = {
    try {
      Some(Source.fromFile(filePath))
    } catch {
      case e: Exception => None
    }
  }

  def next(bufferedSource: BufferedSource): Option[Iterator[String]] = {
    Some(bufferedSource.getLines)
  }

  def getResult(reg: Regex, line: String) = {
    reg.findAllIn(line).matchData
  }
}
