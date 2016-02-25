import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import java.io._
import scala.sys.process._

object TechnicalAssessment2 {
  def main(args: Array[String]) {
      var filename = "age.txt"
      var array_age = ArrayBuffer[Int]()
      val end_of_life = 9999
      val per_batch_records = 8
      
      var tempFileName = java.time.LocalDateTime.now.toString
//      var num_of_tapes = 3
      var idx_line = 0
      var idx_file = 0
      var file_data = ArrayBuffer[Int]()
      for (line <- Source.fromFile(filename).getLines) {
          file_data += line.toInt 
          if (idx_line % per_batch_records == 0){
            file_data = sortAge(file_data)
            writeToFile(file_data.mkString("\n"), "temp/" +tempFileName+ "_" +idx_file+".txt")
            idx_file += 1
            file_data.foreach(println)
            println("-------------")
            file_data.clear
          }
          idx_line += 1
      }
      
            file_data = sortAge(file_data)
            writeToFile(file_data.mkString("\n"), "temp/" +tempFileName+ "_" +idx_file+".txt")
            file_data.foreach(println)
            println("-------------")
            file_data.clear
            idx_file += 1
      
      var Tape = new Array[Int](idx_file)
      for (i <- 0 to idx_file - 1){
        var _current_file_name = "temp/" +tempFileName +"_"+ i + ".txt"
        println(_current_file_name)
        var _data = firstLine(_current_file_name)
        if ( _data != "")
          Tape(i) = _data.toInt
      }
      
      var array_has_value = true
      while(array_has_value){
        var _minValue = getMinIndex(Tape)
        var _minIndex = getIndexArrayByValue(Tape, _minValue)
        
                println(Tape(0))
        Tape.foreach(println)
        println("Min: "+_minValue)
        
        var _updatedFilename ="temp/" +tempFileName +"_"+ _minIndex + ".txt"
        removefirstLine(_updatedFilename)
        var _data = firstLine(_updatedFilename)
        if ( _data != "")
          Tape(_minIndex) = _data.toInt
        else
          Tape(_minIndex) = end_of_life
          
        if (_minValue == end_of_life)
            array_has_value = false
        else
           writeToFile(_minValue.toString, "Part2/sorted_age" +tempFileName+ ".txt")
      }
      
      removeTempFiles(tempFileName)
   }
  
  def firstLine(filename:String): String = {
     for (line <- Source.fromFile(filename).getLines) {
              return line 
     }
     return ""
  }
  
  def removefirstLine(filename:String){
    val cmd = "sed -i .bak 1d %s".format(filename)
    cmd.!
  }
  
  def removeTempFiles(prefix:String){
//    val cmd = "rm temp/"+prefix+"_*.txt"
//    cmd.!
    for {
        files <- Option(new File("temp/").listFiles)
        file <- files if file.getName.startsWith(prefix)
      } file.delete()
  }
  
  def getMinIndex (array:Array[Int]) : Int = {
		var currentValue = array(0); 
		for (j <- 0 to array.length - 1) {
			if (array(j) < currentValue) 
				currentValue = array(j);
		}
		return currentValue
	}
  
  def getIndexArrayByValue(array:Array[Int], value:Int) : Int = {
    for (j <- 0 to array.length - 1) {
			if (array(j) == value) 
				return j
		}
    return 0
  }
  
//  def readFromFile(filename:String) : ArrayBuffer[Int] = {
//    var file_data = ArrayBuffer[Int]()
//    // load age from file into List
//      for (line <- Source.fromFile(filename).getLines) {
//          file_data += line.toInt 
//      }
//    return file_data
//  }
  
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
  
  def writeToFile(text:String, filename:String){
      val fw = new FileWriter(filename, true)
      fw.write(text)
      fw.write("\n")
      fw.close()
  }
}