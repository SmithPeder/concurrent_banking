class Bank(val allowedAttempts: Integer = 3) {

    // Queues
    private val transactionsQueue: TransactionQueue = new TransactionQueue()
    private val processedTransactions: TransactionQueue = new TransactionQueue()

    /* AddTransactionToQueue
     *
     * Will create a new transaction and add it to the queue
     *
     * :param: from, the account where we want to withdraw money
     * :param: to, the account where we want to deposit money
     * :param: amout, the amout we want to transfer
     */
    def addTransactionToQueue(from: Account, to: Account, amount: Double): Unit = {
        val transaction: Transaction = new Transaction(transactionsQueue, processedTransactions, from, to, amount, allowedAttempts)
        transactionsQueue.push(transaction)

        val process = get_thread(processTransactions)
        process.start()
    }

    /* ProcessTransactions
     *
     * Will process all transactions in the queue
     */
    private def processTransactions(): Unit = {
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

    /* AddAccount
     *
     * Will add an account to the bank
     *
     * :param; initialBalance, will set the initialBalance for the account
     */
    def addAccount(initialBalance: Double): Account = {
        new Account(this, initialBalance)
    }

    /* GetProcessedTransactionsAsList
     *
     * Will get the list of transactions
     */
    def getProcessedTransactionsAsList: List[Transaction] = {
        processedTransactions.iterator.toList
    }

    /* Get_thread
     *
     * Function from Task 1, wraps a function in a thread
     */
    def get_thread(input: () => Any): Thread = {
        return new Thread(new Runnable {
            def run() {
                input()
            }
        })
    }
}
