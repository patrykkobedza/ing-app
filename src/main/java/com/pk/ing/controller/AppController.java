package com.pk.ing.controller;

import com.pk.ing.model.atm.RequestDto;
import com.pk.ing.model.game.PlayersDto;
import com.pk.ing.model.transaction.TransactionDto;
import com.pk.ing.service.AtmService;
import com.pk.ing.service.OnlineGameService;
import com.pk.ing.service.TransactionService;
import com.pk.ing.utils.RequestDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.List;

import static com.pk.ing.utils.AppConst.ATMS_CALCULATE_ORDER_URL;
import static com.pk.ing.utils.AppConst.ONLINEGAME_CALCULATE_URL;
import static com.pk.ing.utils.AppConst.PAGE_NOT_FOUND_MESSAGE;
import static com.pk.ing.utils.AppConst.TRANSACTIONS_REPORT_URL;
import static com.pk.ing.utils.RequestDataMapper.arrayType;

public class AppController {
    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    private final AtmService atmService;
    private final OnlineGameService onlineGameService;
    private final TransactionService transactionService;
    private final RequestDataMapper mapper;

    public AppController(AtmService atmService, OnlineGameService onlineGameService, TransactionService transactionService, RequestDataMapper mapper) {
        this.atmService = atmService;
        this.onlineGameService = onlineGameService;
        this.transactionService = transactionService;
        this.mapper = mapper;
    }

    public void initEndpoints() {
        initAtmEndpoint();
        initOnlineGameEndpoint();
        initTransactionsEndpoint();
        initExceptionHandling();
        Spark.notFound(PAGE_NOT_FOUND_MESSAGE);
    }

    private void initTransactionsEndpoint() {
        Spark.post(TRANSACTIONS_REPORT_URL, (request, response) -> {
            List<TransactionDto> list = mapper.fromJson(request, arrayType(TransactionDto.class));
            return transactionService.executeReport(list);
        });
    }

    private void initOnlineGameEndpoint() {
        Spark.post(ONLINEGAME_CALCULATE_URL, (request, response) -> {
            PlayersDto players = mapper.fromJson(request, PlayersDto.class);
            return onlineGameService.calculatePlayersOrder(players);
        });
    }

    private void initAtmEndpoint() {
        Spark.post(ATMS_CALCULATE_ORDER_URL, (request, response) -> {
            List<RequestDto> list = mapper.fromJson(request, arrayType(RequestDto.class));
            return atmService.calculateAtmOrder(list);
        });
    }
    private static void initExceptionHandling() {
        Spark.exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(exception.getMessage());
            log.error(exception.getMessage());
        });
    }

}
