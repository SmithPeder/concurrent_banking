import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)

    def withdraw(amount: Double): Either[Unit, String] = this.synchronized {
      if (amount <= 0) {
        Right("Amount can't be under/or 0")
      }
      else if (getBalanceAmount >= amount) {
        // New balance is equal to old balance minus the amount we withdraw
        balance.amount = getBalanceAmount - amount
        Left(null)
      }
      else {
        Right("To little funds")
      }
    }

    def deposit(amount: Double): Either[Unit, String] = this.synchronized {
      // New balance is equal to old balance pluss the amount we deposit
      if (amount <= 0 ) {
        Right("Amount can't be under/or 0")
      } else {
        balance.amount = getBalanceAmount + amount
        Left(null)
      }
    }
    def getBalanceAmount: Double = balance.amount

    def transferTo(account: Account, amount: Double) = {
        bank addTransactionToQueue (this, account, amount)
    }


}
