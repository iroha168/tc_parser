import java.io.{File, PrintWriter}

import org.jsoup.Jsoup

import scala.concurrent.{Await, Future, duration}
import scala.io.Source
import scala.sys.process.Process

object CreateClassTemplate extends App {
  def run(targetClassName: String) = try{
    val templateCpp: String =
      """#include <algorithm>
        |#include <cmath>
        |#include <iostream>
        |#include <sstream>
        |#include <string>
        |#include <vector>
        |
        |//INSERT_SUBMIT_CODE
        |
        |std::vector<std::string> inputStringArray(std::string input) {
        |    std::vector<std::string> v;
        |    std::stringstream ss(input);
        |    std::string buffer;
        |    while (std::getline(ss, buffer, ',')) {
        |        v.push_back(buffer);
        |    }
        |    return v;
        |}
        |
        |std::string inputString(std::string input) {
        |    return input;
        |}
        |
        |std::vector<double> inputDoubleArray(std::string input) {
        |    std::vector<double> v;
        |    std::stringstream ss(input);
        |    std::string buffer;
        |    while (std::getline(ss, buffer, ',')) {
        |        v.push_back(stod(buffer));
        |    }
        |    return v;
        |}
        |
        |double inputDouble(std::string input) {
        |    return stod(input);
        |}
        |
        |std::vector<long long> inputLongLongArray(std::string input) {
        |    std::vector<long long> v;
        |    std::stringstream ss(input);
        |    std::string buffer;
        |    while (std::getline(ss, buffer, ',')) {
        |        v.push_back(stoll(buffer));
        |    }
        |    return v;
        |}
        |
        |long long inputLongLong(std::string input) {
        |    return stoll(input);
        |}
        |
        |std::vector<int> inputIntArray(std::string input) {
        |    std::vector<int> v;
        |    std::stringstream ss(input);
        |    std::string buffer;
        |    while (std::getline(ss, buffer, ',')) {
        |        v.push_back(stoi(buffer));
        |    }
        |    return v;
        |}
        |
        |int inputInt(std::string input) {
        |    return stoi(input);
        |}
        |
        |char InputChar(std::string input) {
        |    return input[0];
        |}
        |
        |std::vector<std::string> sliceInput(std::string input) {
        |    std::vector<std::string> v;
        |    std::string s = "";
        |    bool isInArray = false;
        |    input.erase(std::remove(input.begin(), input.end(), '"'), input.end());
        |    input.erase(std::remove(input.begin(), input.end(), ' '), input.end());
        |    input.erase(std::remove(input.begin(), input.end(), '\''), input.end());
        |
        |    input += ',';
        |    for (int i = 0; i < input.size(); i++) {
        |        if (input[i] == '{') {
        |            isInArray = true;
        |        } else if (input[i] == '}') {
        |            isInArray = false;
        |            v.push_back(s);
        |            s = "";
        |            i++;
        |        } else {
        |            if (input[i] == ',') {
        |                if (isInArray) {
        |                    s += input[i];
        |                } else {
        |                    v.push_back(s);
        |                    s = "";
        |                }
        |            } else {
        |                s += input[i];
        |            }
        |        }
        |    }
        |    return v;
        |}
        |
        |template <typename T>
        |void displayArray(std::vector<T> answer){
        |    int i = 0;
        |    for(auto &e : answer){
        |        if(i++)
        |            std::cout << " ";
        |        std::cout << e;
        |    }
        |    cout << endl;
        |
        |}
        |
        |template <typename T>
        |void displayAnswer(T answer){
        |    std::cout << answer << std::endl;
        |}
        |
        |/*
        | *argv
        | *[1] class name
        | * */
        |int main(int argc, char *argv[]) {
        |    std::string in;
        |    freopen(argv[1], "r", stdin);
        |
        |    //#INSERT_INPUTS
        |    return 0;
        |}
        |
        |
        |
        |""".stripMargin

    val inputDir = "/Users/mas.kimura/topcoder/testcases2/formatted/input"
    val outputDir = "/Users/mas.kimura/topcoder/testcases2/formatted/output"
    val solutionDir = "/Users/mas.kimura/topcoder/testcases2/formatted/solutions_cpp"
    val problemDir = "/Users/mas.kimura/topcoder/problem_with_title"
    val solutionFileList = new File(solutionDir).listFiles
    val targetHtml = solutionFileList.find(file => file.getName.contains(s"-${targetClassName}")).getOrElse("")
    val inputFileList = new File(inputDir).listFiles
    val testInput = inputFileList.find(file => file.getName.contains(s"-${targetClassName}")).getOrElse("")
    val problemFileList = new File(problemDir).listFiles
    val testProblem = problemFileList.find(file => file.getName.contains(s"__${targetClassName}-")).getOrElse("")
//    println(testProblem.toString)
//    println("class name: " + targetClassName)

    val html = Source.fromFile(testProblem.toString).mkString
    val doc = Jsoup.parse(html)
    val targetMethodName = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)").text
    val parameters = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2)").text.split(',').map(_.trim)
    val returnValues = doc.select(".problemText > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2)").text
    //    println("class name: " + targetClassName)
//    println("method name: " + targetMethodName)
    //    println("returns: " + returnValues)
    //    println("input file: " + testInput)
    val inputPairs = parameters.zipWithIndex.map {
      case ("int", in) => (s"vector<int> input_${in};", s"input_${in}.push_back(inputInt(result[${in}]));")
      case ("int[]", in) => (s"vector<vector<int> > input_${in};", s"input_${in}.push_back(inputIntArray(result[${in}]));")
      case ("String", in) => (s"vector<std::string> input_${in};", s"input_${in}.push_back(inputString(result[${in}]));")
      case ("String[]", in) => (s"vector<vector<std::string> > input_${in};", s"input_${in}.push_back(inputStringArray(result[${in}]));")
      case ("char", in) => (s"vector<std::string> input_${in};", s"input_${in}.push_back(inputChar(result[${in}]));")
      case ("double", in) => (s"vector<double> input_${in};", s"input_${in}.push_back(inputDouble(result[${in}]));")
      case ("double[]", in) => (s"vector<vector<double> > input_${in};", s"input_${in}.push_back(inputDoubleArray(result[${in}]));")
      case ("long", in) => (s"vector<long long> input_${in};", s"input_${in}.push_back(inputLongLong(result[${in}]));")
      case ("long[]", in) => (s"vector<vector<long> long> input_${in};", s"input_${in}.push_back(inputLongLongArray(result[${in}]));")
    }
    val inputDeclaration = inputPairs.map(_._1).mkString("\n")
    val inputParams = inputPairs.map(_._2).mkString("\n")
    val arguments = parameters.zipWithIndex.map { case (_, in) => s"input_${in}[i]" }.mkString(", ")

    //  println(inputParams.mkString("\n"))
//    println(targetHtml)
    val sampleAnswer = Source.fromFile(targetHtml.toString).mkString
    val displayMethod = if (returnValues.contains("[]")) "displayArray" else "displayAnswer"
    val inputs =
      s"""
         |    ${inputDeclaration}
         |    ${targetClassName} *instance_name= new ${targetClassName}();
         |    int cnt = 0;
         |    while (getline(cin, in)) {
         |        vector<std::string> result = sliceInput(in);
         |        ${inputParams}
         |        cnt++;
         |    }
         |    for(int i = 0; i < cnt; i++) {
         |        ${displayMethod}(instance_name->${targetMethodName}(${arguments}));
         |    }""".stripMargin
    val resultCpp = templateCpp.replace("//INSERT_SUBMIT_CODE", sampleAnswer).replace("//#INSERT_INPUTS", inputs)
    //    val resultCpp = templateCpp
    //  println(inputDeclaration)
    val cppFile =s"/Users/mas.kimura/topcoder/tmp/${targetClassName}.cpp"
    val writeCpp = new PrintWriter(s"${cppFile}")
    //  println(resultCpp)
    writeCpp.write(resultCpp)
    writeCpp.close()
    val tmpDir = "/Users/mas.kimura/topcoder/tmp"
    //  println(resultCpp)
    Process(s"g++ -std=c++14 ${cppFile} -o ${tmpDir}/a").!
    println(s"g++ -std=c++14 ${cppFile} -o ${tmpDir}/a")
    Process(s"chmod 777 ${tmpDir}/a").!
    println(s"${tmpDir}/a ${testInput}")
    Process(s"${tmpDir}/a ${testInput}").!!
  }catch {
    case e: Exception => println(e.getMessage)
  }
}

