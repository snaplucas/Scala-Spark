import org.apache.spark.{SparkConf, SparkContext}

object CalculatePi {

  val NUM_SAMPLES = 100

  def main(args: Array[String]): Unit = {
    val config = new SparkConf()
      .setMaster("local[2]")
      .setAppName("pi")

    val sc = new SparkContext(config)

    val count = sc.parallelize(1 to NUM_SAMPLES).filter { _ =>
      val x = math.random
      val y = math.random
      x * x + y * y < 1
    }.count()
    println(s"Pi is roughly ${4.0 * count / NUM_SAMPLES}")

  }
}
