import org.apache.spark.{SparkConf, SparkContext}

object WordCounter {

  def main(args: Array[String]): Unit = {
    val config = new SparkConf()
      .setMaster("local[2]")
      .setAppName("CountingSheep")

    val sc = new SparkContext(config)
    sc.setLogLevel("ERROR")

    val textFile = sc.textFile("file:///usr/local/Cellar/apache-spark/2.2.0/README.md")

    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .sortBy(pair => pair._2, ascending = false)

    counts.saveAsTextFile("file:////Users/lucasrodrigues/Documents/WordCounter")
  }

}
