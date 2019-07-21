import java.io.{File, PrintWriter}

import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.io.Source
import scala.util.matching.Regex
object ProcessAllTests extends App {
  val reg = new Regex("""__.{1,50}-(.{1,50}).txt""","method")
  val fileList = new File("/Users/mas.kimura/topcoder/testcases2/formatted/solutions_cpp").listFiles

  val result = fileList.map {f =>
    val className  = reg.findFirstMatchIn(f.getName).map(m=>{m.group("method")}).getOrElse("")
//    println(className +" * "+methodName)
    CreateClassTemplateOnce.run(targetClassName=className)

  }
}

