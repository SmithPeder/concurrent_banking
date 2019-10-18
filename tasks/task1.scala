object Task1{
  def main(args: Array[String]) {
    val a = createArray(50)
    a.foreach(print)

    println("\n" + sumArrayIterative(a))

    println(sumArrayRecursive(a.toList))
  }


  def createArray(value: Int): Array[Int] = {
    var array = Array.empty[Int]
    for (i <- 1 to value) {
        array :+= i
    }
    return array
  }


  def sumArrayIterative(list: Array[Int]): Int = {
    var sum = 0
    for (e <- list) {
      sum += e
    }
    return sum
  }


  def sumArrayRecursive(list: List[Int]): Int = list match {
      case head :: tail => head + sumArrayRecursive(tail)
      case Nil => 0
  }
}
