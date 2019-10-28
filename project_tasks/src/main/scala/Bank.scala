class Bank(val allowedAttempts: Integer = 3) {

    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()

    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = {
        val t: Transaction = new Transaction(transactionsQueue, processedTransactions, from, to, amount, allowedAttempts)
        transactionsQueue.push(t)

        val process = get_thread(processTransactions)
        process.start()
    }

    private def processTransactions(): Unit = {
        if (transactionsQueue.isEmpty == false) {
            val t: Transaction = transactionsQueue.pop
            val thread = new Thread(t)
            thread.start()
            if (t.synchronized {t.status == TransactionStatus.PENDING}) {
                transactionsQueue.push(t)
                processTransactions
            } else {
                processedTransactions.push(t)
            }

        }
    }

    def addAccount(initialBalance: Double): Account = {
        new Account(this, initialBalance)
    }

    def getProcessedTransactionsAsList: List[Transaction] = {
        processedTransactions.iterator.toList
    }

    def get_thread(input: () => Any): Thread = {
        return new Thread(new Runnable {
            def run() {
                input()
            }
        })
    }
}
