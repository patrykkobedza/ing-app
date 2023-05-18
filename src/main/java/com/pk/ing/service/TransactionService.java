package com.pk.ing.service;

import com.pk.ing.model.transaction.AccountDto;
import com.pk.ing.model.transaction.TransactionDto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class TransactionService {
    public List<AccountDto> executeReport(final List<TransactionDto> transactions) {
        final HashMap<String, AccountDto> result = new HashMap<>();
        transactions.forEach(transaction -> {
            String creditAccount = transaction.creditAccount();
            String debitAccount = transaction.debitAccount();
            double amount = transaction.amount();
            if (result.containsKey(creditAccount)) {
                result.get(creditAccount).credit(amount);
            } else {
                addNewAccountToMapWithAction(creditAccount, result, (newAcc -> newAcc.credit(amount)));
            }
            if (result.containsKey(debitAccount)) {
                result.get(debitAccount).debit(amount);
            } else {
                addNewAccountToMapWithAction(debitAccount, result, (newAcc -> newAcc.debit(amount)));
            }
        });
        return result.values()
                .stream()
                .sorted(Comparator.naturalOrder())
                .toList();
    }

    private void addNewAccountToMapWithAction(String account, Map<String, AccountDto> resultMap, Consumer<AccountDto> accountDtoConsumer) {
        final AccountDto newAccount = new AccountDto(account);
        accountDtoConsumer.accept(newAccount);
        resultMap.put(account, newAccount);
    }
}
