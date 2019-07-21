import java.io.{File, PrintWriter}

import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.io.Source
import scala.util.matching.Regex
object FindInSolutions extends App {
  val reg = new Regex("""View(.{1,50})Problem Statement""", "problem")
  val fileList = new File("/Users/mas.kimura/topcoder/testcases2/notdefences").listFiles
  val inputDir = "/Users/mas.kimura/topcoder/testcases2/formatted/input"
  val outputDir = "/Users/mas.kimura/topcoder/testcases2/formatted/output"
  val solutionDir = "/Users/mas.kimura/topcoder/testcases2/formatted/solutions"

  FileUtils.cleanDirectory(new File(inputDir))
  FileUtils.cleanDirectory(new File(outputDir))
  FileUtils.cleanDirectory(new File(solutionDir))
  val result = fileList.map { f =>
    println(f.getAbsolutePath)
    val html = Source.fromFile(f.getAbsolutePath).mkString
    val doc = Jsoup.parse(html)
    val sourceCodeElem = doc.select(".problemText")
    val jsoupDoc = Jsoup.parse(sourceCodeElem.html);
    val replaceStr = jsoupDoc.html.replaceAll("\\\\n", "this_must_be_unique_as_placdeholder_for_newline")
    jsoupDoc.select("br").after("\\n");
    val solutionHtmlStr = replaceStr.replaceAll("\\\\n", "\n")
    val solution = Jsoup.parse(solutionHtmlStr
      .replaceAll("this_must_be_unique_as_placdeholder_for_newline", "\\\\n"))
      .wholeText().replaceAll(" "," ")
    val problem = reg.findFirstMatchIn(doc.text()).map { m => m.group("problem") }.getOrElse("").replaceAll(" ", "").replaceAll(" ", "")
    val inputs = doc.select("tr.alignTop:nth-child(n) > td:nth-child(2)")
      .toArray.map(_.asInstanceOf[Element].text).mkString("\n").replace("Test Arguments\n", "")
    val outputs = doc.select("tr.alignTop:nth-child(n) > td:nth-child(4)")
      .toArray.map(_.asInstanceOf[Element].text).mkString("\n")
    val round = doc.select(".paddingTable > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > a:nth-child(1)")
      .text.replaceAll(">", "").replaceAll("/", "_").replaceAll("[ ]+", " ").replaceAll(" ", "_")
    val fileName = round + "-" + problem + ".txt"
    val outputFile = new PrintWriter(s"${outputDir}/output__${fileName}")
    outputFile.write(outputs)
    outputFile.close()
    val inputFile = new PrintWriter(s"${inputDir}/input__${fileName}")
    inputFile.write(inputs)
    inputFile.close()
    val solutionFile = new PrintWriter(s"${solutionDir}/solution__${fileName}")
//    println(solution)
    solutionFile.write(solution)
    solutionFile.close()
  }
}

