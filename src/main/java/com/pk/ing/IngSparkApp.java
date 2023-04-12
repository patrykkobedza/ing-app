package com.pk.ing;

import com.pk.ing.controller.AppController;
import com.pk.ing.service.AtmService;
import com.pk.ing.service.OnlineGameService;
import com.pk.ing.service.TransactionService;
import com.pk.ing.utils.RequestDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import static com.pk.ing.utils.AppConsts.APP_PORT;

public class IngSparkApp {

    private static final Logger log = LoggerFactory.getLogger(IngSparkApp.class);
    private final RequestDataMapper mapper;
    private final AppController appController;

    public IngSparkApp() {
        mapper = new RequestDataMapper();
        appController = new AppController(
                new AtmService(),
                new OnlineGameService(),
                new TransactionService(),
                mapper);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        IngSparkApp app = new IngSparkApp();
        app.init();
        long end = System.currentTimeMillis();
        log.info("App started in {}ms", end - start);
    }

    public void init() {
        Spark.defaultResponseTransformer(mapper);
        Spark.port(APP_PORT);
        appController.initEndpoints();
        Spark.awaitInitialization();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

}
