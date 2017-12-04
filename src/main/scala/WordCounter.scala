import org.apache.spark.{SparkConf, SparkContext}

object WordCounter {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Word Counter")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("path")
    val tokenizedFileData = textFile.flatMap(x => x.split(""))
    val countPrep = tokenizedFileData.map(x => (x, 1))
    val counts = countPrep.reduceByKey((x, y) => x + y)
    val sortedCounts = counts.sortBy(pair => pair._2, ascending = false)
    sortedCounts.saveAsTextFile("another_path")
  }

}
