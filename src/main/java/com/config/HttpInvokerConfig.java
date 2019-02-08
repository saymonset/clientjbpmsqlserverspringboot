package com.config;

import com.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import java.util.logging.Logger;

/**
 * Created by iuliana.cosmina on 10/6/16.
 */
@Configuration
/*@EnableJpaRepositories(basePackages = {"com.repos"})
@EntityScan("com.entities")*/
public class HttpInvokerConfig {
Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    public SingerService singerService;

    @Bean(name = "/httpInvoker/singerService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        logger.info("CHEQUNO SERVICIO ------------------------------singerService =" + (singerService==null));
        HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(singerService);
        invokerService.setServiceInterface(SingerService.class);
        return invokerService;
    }

}