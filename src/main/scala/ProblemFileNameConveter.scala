import java.io.{File, PrintWriter}

import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup

import scala.io.Source

object ProblemFileNameConveter extends App{
  val originFileList = new File("/Users/mas.kimura/topcoder/problems2").listFiles
  FileUtils.cleanDirectory(new File("/Users/mas.kimura/topcoder/problem_with_title"))
  originFileList.map(f =>{
    val html = Source.fromFile(f.getAbsolutePath).mkString
    val doc = Jsoup.parse(html)
    val className = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)").text
    val methodName= doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)").text
    val writer = new PrintWriter(s"/Users/mas.kimura/topcoder/problem_with_title/problem__${className}-${methodName}.html")
    val text: String = doc.html
    writer.write(text)
    writer.close()
  })

}
