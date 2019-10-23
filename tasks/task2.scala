object Task2 {
  def main(args: Array[String]) {
    val hello = get_thread(hehe)
    hello.start()


    val thread1 = get_thread(increaseCounter)
    val thread2 = get_thread(increaseCounter)
    val thread3 = get_thread(printCount)

    thread1.start()
    thread2.start()
    thread3.start()

    slowPrint()
  }

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

  def increaseCounter(): Unit = lock.synchronized {
    println("IC", counter)
    counter += 1
    println("IC2", counter)
  }

  def printCount() {
    println("T", counter)
  }

  def hehe() {
    println("hello world")
  }

  def slowPrint() {
    Thread.sleep(1000)
    println("SUM", counter)
  }

  /*  A deadlock is where a situation where a nondeterminitic program sets itself
   *  in a state where two or more threads depend on each other. The threads can
   *  grind to a holdt since they both need the other threads to finish first.
   *
   *  To prevent deadlock we can synchronized threads so parts of the program run
   *  in a certain determinisitic way each time. This will prevent threads from
   *  locking up when they are missing a value.
   */
}
