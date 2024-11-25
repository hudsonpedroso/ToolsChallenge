package com.banco.api_pagamentos.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("==========================================================================================");
        log.info("============================== A aplicação foi iniciada! =================================");
        log.info("========= Acesse e teste a API no Swagger UI: http://localhost:8080/swagger-ui.html ======");
        log.info("==========================================================================================");
    }
}
