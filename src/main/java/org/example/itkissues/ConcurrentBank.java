package org.example.itkissues;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConcurrentBank {
    private final Map<Integer, BankAccount> accounts;
    private final Lock bankLock;

    public ConcurrentBank() {
        this.accounts = new HashMap<>();
        this.bankLock = new ReentrantLock();
    }

    public BankAccount createAccount(double initialBalance) {
        bankLock.lock();
        try {
            int accountNumber = accounts.size() + 1;
            BankAccount account = new BankAccount(accountNumber, initialBalance);
            accounts.put(accountNumber, account);
            return account;
        } finally {
            bankLock.unlock();
        }
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        BankAccount firstLock = fromAccount.getAccountNumber() < toAccount.getAccountNumber() ? fromAccount : toAccount;
        BankAccount secondLock = fromAccount.getAccountNumber() < toAccount.getAccountNumber() ? toAccount : fromAccount;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                } else {
                    throw new IllegalArgumentException("Insufficient funds for transfer");
                }
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double totalBalance = 0;
            for (BankAccount account : accounts.values()) {
                totalBalance += account.getBalance();
            }
            return totalBalance;
        } finally {
            bankLock.unlock();
        }
    }
}
