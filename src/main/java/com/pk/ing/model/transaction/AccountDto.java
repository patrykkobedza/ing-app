package com.pk.ing.model.transaction;

import com.google.gson.annotations.Expose;

public class AccountDto implements Comparable<AccountDto> {

    @Expose
    private final String account;
    @Expose
    private int debitCount;
    @Expose
    private int creditCount;
    @Expose
    private long balance;

    public AccountDto(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void debit(double amount) {
        balance -= (long) (amount * 100);
        debitCount++;
    }

    public void credit(double amount) {
        balance += (long) (amount * 100);
        creditCount++;
    }

    public long getBalance() {
        return balance;
    }

    public int getDebitCount() {
        return debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    @Override
    public int compareTo(AccountDto o) {
        return account.compareTo(o.account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountDto that = (AccountDto) o;

        return account.equals(that.account);
    }

    @Override
    public int hashCode() {
        return account.hashCode();
    }
}
