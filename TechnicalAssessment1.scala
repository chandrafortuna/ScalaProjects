import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import java.io._

object TechnicalAssessment1 {
  def main(args: Array[String]) {
      var filename = "age.txt"
      var array_age = ArrayBuffer[Int]()
      
      array_age = readFromFile(filename)
      array_age = sortAge(array_age)
      writeToFile(array_age.mkString("\n"))
   }
  
  def readFromFile(filename:String) : ArrayBuffer[Int] = {
    var file_data = ArrayBuffer[Int]()
    // load age from file into List
      for (line <- Source.fromFile(filename).getLines) {
          file_data += line.toInt
      }
    return file_data 
  }
  
  def sortAge(sorted_age:ArrayBuffer[Int]) : ArrayBuffer[Int] = {
    var _size = sorted_age.length - 1
      for ( i <- 0 to _size - 1){
        for ( j <- 0 to _size - 1 ){
          if (sorted_age(j) > sorted_age(j+1)){
            var _temp = sorted_age(j)
            sorted_age(j) = sorted_age(j+1)
            sorted_age(j+1) = _temp
          }
        }
      }
    return sorted_age
  }
  
  def writeToFile(text:String){
      val fw = new FileWriter("Part1/sorted_age.txt", true)
      fw.write(text)
      fw.close()
  }
}