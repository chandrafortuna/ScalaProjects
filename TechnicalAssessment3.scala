import scala.io.Source

object TechnicalAssessment3 {
  
  var filename = new String()
  
  def main(args: Array[String]) {
      initialize("blacklist.txt")
      println(check_blacklist("Ahmad", "1234567"))
  }
  
  def initialize(blacklist:String){
    filename = blacklist
  }
  
  def check_blacklist(name:String, phone_number:String) : Boolean = {
      for (line <- Source.fromFile(filename).getLines) {
          var data = line.split(" ")
          if(data(0) == name && data(1) == phone_number){
            return true
          }
      }
      return false
  }
  
}