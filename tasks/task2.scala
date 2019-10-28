object Task2 {
  // Main function calling all other methods in the task
  def main(args: Array[String]) {

    // Task 2 a), check if the get_thread function works
    val threadWrapper = get_thread(testFunctionToWrap)
    threadWrapper.start()

    // Task 2 b), cretate 2 threads with increaseCounter and 1 thread with printCount
    val thread1 = get_thread(increaseCounter)
    val thread2 = get_thread(increaseCounter)
    val thread3 = get_thread(printCount)

    // Task 2b) start all threads
    thread1.start()
    thread2.start()
    thread3.start()

    // Use slowPrint to check the real value of the counter
    slowPrint()
  }

  /* Task 2 a) Function that wraps a function in a thread
   *
   * :param: Input takes any function that can return anything
   * :return: The function wrapped in a Thread
   */
  def get_thread(input: () => Any): Thread = {
    return new Thread(new Runnable {
      def run() {
        input()
      }
    })
  }


  /*  The phenomenon is called "Non determinisitic thread" meaning
   *  the threads can run in any which order.
   *
   *
   *  It can be problematic if the threads put the program in an
   *  illegal state. It can also be problematic if you need to know
   *  the output at certain times.
   */
  private var counter: Int = 0

  private val lock = new Object

  // Threadsafe increaseCounter.
  // Prints both before and after the count increment
  // This is done to slow down the function down a bit,
  // so we can view the phenomenon a bit better.
  def increaseCounter(): Unit = lock.synchronized {
    println("IC", counter)
    counter += 1
    println("IC2", counter)
  }

  // Print the counter
  def printCount() {
    println("T", counter)
  }

  // Test
  def testFunctionToWrap() {
    println("Hello World")
  }

  // Test function that we run at the end to check what the counter value realy is
  def slowPrint() {
    Thread.sleep(1000)
    println("SUM", counter)
  }

  /*  A deadlock is a situation where a nondeterminitic program sets itself
   *  in a state where two or more threads depend on each other. The threads can
   *  grind to a holdt since they both need the other threads to finish first.
   *
   *  An example of this is a situation where two threads(T1, T2) both need the
   *  same two resources (A, B). A situation where T1 grabs A and then T2 grabs B.
   *  Then the threads will just wait forever (deadlock) since none of them will
   *  let go of the resources they have grabbed.
   *
   *  To prevent deadlock we can synchronized threads so parts of the program run
   *  in a certain determinisitic way each time. This will prevent threads from
   *  locking up when they are missing a value.
   */
}
