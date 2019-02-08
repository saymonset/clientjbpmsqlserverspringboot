package com.config;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.definition.QueryDefinition;
import org.kie.server.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.util.Set;

/**
 * Created by simon on 2/8/2019.
 */
public class JbpmConfiguration   {

    private static final Logger log = LoggerFactory.getLogger(JbpmConfiguration.class);
   // private DecryptRelaxPropertyResolver propertyResolver;
    private KieServicesClient kieServicesClient;

    private static final String TARGET = "CUSTOM";
    private static final String SOURCE = "java:jboss/datasources/jbpmDS";



    @Bean(name = "processServicesClient")
    public ProcessServicesClient processServicesClient() {
        return kieServicesClient.getServicesClient(ProcessServicesClient.class);
    }

    @Bean(name = "queryServicesClient")
    public JbpmQueryServicesClientImpl queryServicesClient() {
        return kieServicesClient.getServicesClient(JbpmQueryServicesClientImpl.class);
    }

    @Bean(name = "userTaskServicesClient")
    public UserTaskServicesClient userTaskServicesClient() {
        return kieServicesClient.getServicesClient(UserTaskServicesClient.class);
    }

    @Bean(name = "uiServicesClient")
    public UIServicesClient uiServicesClient() {
        return kieServicesClient.getServicesClient(UIServicesClient.class);
    }

    /*@Bean(name = "jbpmEnvironment")
    public wissen.proyectoinversion.modelo.Environment getJBPMEnvironment() {
        wissen.proyectoinversion.modelo.Environment environment = new wissen.proyectoinversion.modelo.Environment();
        environment.setUrlBackendWissen(this.propertyResolver.getProperty("urlWissenBackend"));
        environment.setUrlFuse(this.propertyResolver.getProperty("urlFuse"));
        environment.setToken(this.propertyResolver.getProperty("token"));

        return environment;
    }*/


    private void conectarKieServer() throws MalformedURLException {
        String url = "http://127.0.0.1:8180/kie-server/services/rest/server";
        String user = "admin";
        String password = "admin";

        System.setProperty("org.kie.server.bypass.auth.user", "true");

        KieServicesConfiguration configuration = KieServicesFactory.newRestConfiguration(url, user, password);
        configuration.setMarshallingFormat(MarshallingFormat.JSON);

        long timeout = Long.parseLong("300000");
        configuration.setTimeout(timeout);

        Set<Class<?>> extraClass = new java.util.HashSet<>();
   /*     extraClass.add(wissen.proyectoinversion.modelo.Environment.class);
        extraClass.add(wissen.proyectoinversion.modelo.Assign.class);
        extraClass.add(PedidoInversion.class);
        extraClass.add(SOW.class);
        extraClass.add(CountEntity.class);
        extraClass.add(ProjectCountEntity.class);
*/
        configuration.addJaxbClasses(extraClass);
        kieServicesClient = KieServicesFactory.newKieServicesClient(configuration);

        QueryServicesClient queryService = kieServicesClient.getServicesClient(QueryServicesClient.class);

        // Registro las custom queries
        // tasks with custom variable information
        QueryDefinition queryTasks = QueryDefinition.builder().source(SOURCE)
                .name("getAllProcessInstances").target(TARGET)
                .expression("select * from processinstancelog")
                .build();

        queryService.replaceQuery(queryTasks);



    }
}
