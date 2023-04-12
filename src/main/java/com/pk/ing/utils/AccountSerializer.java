package com.pk.ing.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pk.ing.model.transaction.AccountDto;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class AccountSerializer implements JsonSerializer<AccountDto> {
    @Override
    public JsonElement serialize(AccountDto src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject root = new JsonObject();
        root.addProperty("account", src.getAccount());
        root.addProperty("debitCount", src.getDebitCount());
        root.addProperty("creditCount", src.getCreditCount());
        BigDecimal balanceValue = BigDecimal.valueOf(src.getBalance(), 2);
        root.add("balance", new JsonPrimitive(balanceValue));

        return root;
    }
}
