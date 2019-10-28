import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)

    /* Withdraw an amount of money from the current Account
     *
     * :param: Amount, the amount of money which will be withdrawed
     * :returns: Either returns nothing or the error string
     */
    def withdraw(amount: Double): Either[Unit, String] = this.synchronized {
      if (amount <= 0) {
        Right("Amount can't be under/or 0")
      }
      else if (getBalanceAmount >= amount) {
        // New balance is equal to old balance minus the amount we withdraw
        balance.amount = getBalanceAmount - amount
        Left(())
      }
      else {
        // Return an error if the funds
        Right("To little funds")
      }
    }

    /* Withdraw an amount of money from the current Account
     *
     * :param: Amount, the amount of money which will be withdrawed
     * :returns: Either returns nothing or the error string
     */
    def deposit(amount: Double): Either[Unit, String] = this.synchronized {
      // New balance is equal to old balance pluss the amount we deposit
      if (amount <= 0 ) {
        Right("Amount can't be under/or 0")
      } else {
        balance.amount = getBalanceAmount + amount
        Left(())
      }
    }

    /* Get the balance amount
     *
     * :returns: Gets the balance amount
     */
    def getBalanceAmount: Double = balance.amount

    /* Transfer to
     *
     * :param: Account, the account where you want to transfer money
     * :param: Amount, the amount you want transfer
     */
    def transferTo(account: Account, amount: Double) = {
        bank addTransactionToQueue (this, account, amount)
    }
}
