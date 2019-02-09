package com.example.saymsql;

import com.services.SingerService;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.definition.ProcessDefinition;
import org.kie.server.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
/**
 * Created by simon on 05/02/19.
 */
@RestController
public class GreetingController {
    Logger log = Logger.getLogger(this.getClass().getName());
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    KieServicesClient kieServicesClient;

    @Autowired
    public SingerService singerService;
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {



        String containerId = "hello";
        String processId = "hello";



       // KieServicesClient kieServicesClient =  KieServicesFactory.newKieServicesClient(configuration);

        System.out.println("\nKieServicesClient created...");
        System.out.println("=================================================\n");

        boolean deployContainer = true;
        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();

        // looking for container "hello"
        if (containers != null) {
            for (KieContainerResource kieContainerResource : containers.getContainers()) {
                if (kieContainerResource.getContainerId().equals(containerId)) {

                    System.out.println("\nFound containerId = " + containerId);
                    System.out.println("=================================================\n");

                    deployContainer = false;
                    break;
                }
            }
        }

        // create container with id = "hello"
        if (deployContainer) {
            System.out.println("\nDeploying container containerId = " + containerId);
            System.out.println("=================================================\n");

            KieContainerResource resource = new KieContainerResource(containerId, new ReleaseId("zjc.poltman.kieserver", "hello-kie-server", "0.0.1"));
            kieServicesClient.createContainer(containerId, resource);
        }

        // list query definitions
        QueryServicesClient queryClient = kieServicesClient.getServicesClient(QueryServicesClient.class);
        List<ProcessDefinition> processes = queryClient.findProcesses(0, 10);
        System.out.println("\nAvailable processes: " + processes);
        System.out.println("=================================================\n");

        // start process instance
        System.out.println("\nStart process instance:");
        System.out.println("=================================================\n");
        ProcessServicesClient processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("processVar1", "zibi");

        Long processInstanceId = processClient.startProcess(containerId, processId, params);

        System.out.println("\nprocessInstanceId = " + processInstanceId + " started:");
        System.out.println("=================================================\n");



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
