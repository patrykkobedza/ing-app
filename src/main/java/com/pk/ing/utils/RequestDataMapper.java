package com.pk.ing.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pk.ing.model.transaction.AccountDto;
import spark.Request;
import spark.ResponseTransformer;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RequestDataMapper implements ResponseTransformer {

    private final Gson gson;

    public RequestDataMapper() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(AccountDto.class, new AccountSerializer())
                .create();
    }

    public static Type arrayType(Class<?> typeArgument) {
        return TypeToken.getParameterized(ArrayList.class, typeArgument).getType();
    }

    public <T> T fromJson(Request request, Type type) {
        return gson.fromJson(request.body(), type);
    }

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
