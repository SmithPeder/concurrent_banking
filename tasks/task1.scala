object Task1{

  // Main function calling rest of tasks
  //
  // param: An array of string arguments
  def main(args: Array[String]) {

    // Task a), array of 50 numbers
    val numbers = createArray(50)
    numbers.foreach(print)

    // Task b), sum the numbers array
    //
    // Iterative
    println("\n" + sumArrayIterative(numbers))
    // Recursive
    println("\n" + sumListRecursive(numbers.toList))
    println(fib(9))
  }


  /*
   * Funciton createArray
   *
   * Creates an array of length value
   *
   * :param: limit, Integer, end of array
   * :return: Array of integers
   */
  def createArray(limit: Int): Array[Int] = {
    var tempArray = Array.empty[Int]
    for (i <- 1 to limit) {
        tempArray :+= i
    }
    return tempArray
  }


  /*
   * Funciton sumArrayIterative
   *
   * Sums an array by iterating over the whole array
   *
   * :numbers: Array of number to sum
   * :return: Interger sum
   */
  def sumArrayIterative(numbers: Array[Int]): Int = {
    var sum = 0
    for (e <- numbers) {
      sum += e
    }
    return sum
  }


  /*
   * Funciton sumListRecursive
   *
   * Sums a list by using recurision with pattern matching
   *
   * :numbers: List of number to sum
   * :return: Interger sum
   */
  def sumListRecursive(numbers: List[Int]): Int = numbers match {
    case head :: tail => head + sumListRecursive(tail)
    case Nil => 0
  }


  /*
   * Funciton fib using BigInt
   *
   * Calculates the fibonacci
   *
   * BigInt uses 8 byte == 2^64 while Int uses 4 bytes
   * which gives it a range of 2^32, giving it 2.1 billion
   *
   * :value: The fib value you want to calculate
   * :return: Interger the value of a fibonacci sequence at the value
   */
  def fib(value: BigInt): BigInt = {
    // If under 2 we just return the value
    if (value < 2) {
      value
    }
    // Else we calculate the fib of the two lower
    else {
      return fib(value-1) + fib(value-2)
    }

  }
}
