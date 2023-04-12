package com.pk.ing.model.transaction;

import com.google.gson.annotations.Expose;

public record TransactionDto(@Expose String debitAccount, @Expose String creditAccount, @Expose double amount) {
}
