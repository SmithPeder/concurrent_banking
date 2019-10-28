import exceptions._
import scala.collection.mutable.Queue

object TransactionStatus extends Enumeration {
  val SUCCESS, PENDING, FAILED = Value
}

class TransactionQueue {

    var transactions = Queue[Transaction]()

    // Remove and return the first element from the queue
    def pop: Transaction = this.synchronized {
      while (isEmpty) {
        wait()
      }
      transactions.dequeue()
    }

    // Return whether the queue is empty
    def isEmpty: Boolean = transactions.isEmpty

    // Add new element to the back of the queue
    def push(t: Transaction): Unit = this.synchronized {
      transactions.enqueue(t)
      notifyAll()
    }

    // Return the first element from the queue without removing it
    def peek: Transaction = transactions.head

    // Return an iterator to allow you to iterate over the queue
    def iterator: Iterator[Transaction] = transactions.iterator
}

class Transaction(val transactionsQueue: TransactionQueue,
                  val processedTransactions: TransactionQueue,
                  val from: Account,
                  val to: Account,
                  val amount: Double,
                  val allowedAttemps: Int) extends Runnable {

  var status: TransactionStatus.Value = TransactionStatus.PENDING
  var attempt = 0

  override def run: Unit = {
    def doTransaction() = {
      from.withdraw(amount) match {
        case Left(x) =>
          to.deposit(amount)
          status = TransactionStatus.SUCCESS;
        case Right(_) =>
          attempt += 1
      }
      if (attempt == allowedAttemps){
        status = TransactionStatus.FAILED
      }
    }

    this.synchronized {
      if (status == TransactionStatus.PENDING) {
          doTransaction
          Thread.sleep(50)
      }
    }
  }
}
