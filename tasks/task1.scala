object Task1{
  def main(args: Array[String]) {

    val a = createArray(50)

    a.foreach(print)

    println("\n" + sumArrayIterative(a))
    println("\n" + sumListRecursive(a.toList))
    println(fib(9))
  }


  /*
   * Funciton createArray
   * Creates an array of length value
   */
  def createArray(value: Int): Array[Int] = {
    var array = Array.empty[Int]
    for (i <- 1 to value) {
        array :+= i
    }
    return array
  }


  /*
   * Funciton sumArrayIterative
   * Returns the sum of an array
   */
  def sumArrayIterative(list: Array[Int]): Int = {
    var sum = 0
    for (e <- list) {
      sum += e
    }
    return sum
  }


  /*
   * Funciton sumListRecursive
   * Returns the sum of a list using pattern mathcing
   */
  def sumListRecursive(list: List[Int]): Int = list match {
    case head :: tail => head + sumListRecursive(tail)
    case Nil => 0
  }

  /*
   * Funciton fib using BigInt
   * BigInt uses 8 byte == 2^64 while Int uses 4 bytes
   * which gives it a range of 2^32, giving it 2.1 billion
   */
  def fib(value: BigInt): BigInt = {
    if (value < 2) {
      value
    }
    else {
      return fib(value-1) + fib(value-2)
    }

  }
}
