package concurrency.conditions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CalculCondition {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        Account account = new Account(bank);
        new Transaction(account, 1500, 1);
        new Transaction(account, -500, 2);
        new Transaction(account, -5000, 3);
        new Transaction(account, -200, 4);
        new Transaction(account, -1900, 9);
        new Transaction(account, +800, 11);
        Transaction t = new Transaction(account, +500, 13);


    }

}

class Bank {


    public void checkAccount(Account account) throws InterruptedException {

        if (account.value < 0) {
            System.out.println(account + " is blocked. Trying to unblock");

            this.unblockAccount(account);
            System.out.println(" account deblocked");

        }
    }

    public void saveTransaction(Transaction transaction) {
        System.out.println(transaction + " saved");
    }

    public void unblockAccount(final Account account) throws InterruptedException {

        //we are currently in the thread of the Transaction
        System.out.println("We unblock the founds after "+Thread.currentThread().getName());
        //All transactions should wait for this condition
        System.out.println("unblocking the account : calling Monaco");

        Thread.sleep(4500);
        System.out.println("Monaco is OK : account unblocked");
        account.value = 2000;
        //TODO : at least a thred is blocked. => debug mode
        account.negative.signalAll();
       // account.negative.await();


    }

}

class Account {

    int value = 2000;
    Bank bank;

    public ReentrantLock lock = new ReentrantLock();
    Condition negative = lock.newCondition();

    public Account(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {

        return "Account : " + value + " ; ";
    }

}

class Transaction extends Thread {

    Account account;
    int amount;
    int number;

    public Transaction(Account account, int amount, int number) {
        this.account = account;
        this.amount = amount;
        this.number = number;
        start();
        this.setName(this.toString());

    }
    
    public static synchronized void doIt(){

    }

    public void run() {
        System.out.println("Executing transaction, please wait ...");

        account.lock.lock();

        try {

            if (account.value <0){
                try {
                    System.out.println("Current transaction "+this+" is waiting for available money");
                    account.negative.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            account.value += amount;
            System.out.println(this + " complete.");
            long time = (long) (1 + Math.random()) * 450;
            sleep(time);
            account.bank.checkAccount(account);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            account.lock.unlock();
        }

        System.out.println("  >>> Account is now : " + account);
    }

    @Override
    public String toString() {

        return "Transaction : " + amount;
    }

}
