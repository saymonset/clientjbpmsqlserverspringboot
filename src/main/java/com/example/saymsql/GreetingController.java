package com.example.saymsql;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import com.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by simon on 05/02/19.
 */
@RestController
public class GreetingController {
    Logger log = Logger.getLogger(this.getClass().getName());
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    public SingerService singerService;
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {


        log.info("Hola Mundillo");
       StringBuilder str = new StringBuilder();

        singerService.findAll().forEach((h) ->{
            log.info(h.getFirstName());
            str.append( ", ").append(h.getFirstName());
        });
        return new Greeting(counter.incrementAndGet(),
                String.format(template, str.toString()));
    }
}


/*
    Set<GeographicDistribution> gds = new HashSet<>();
        geos.forEach((geo) -> {
                gds.add(geographicDistributionService.findByName(geo));
                });*/
