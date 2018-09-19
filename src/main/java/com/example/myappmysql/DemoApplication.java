package com.example.myappmysql;

import com.apress.spring.domain.Journal;
import com.com.apress.spring.repository.JournalRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resource;

@SpringBootApplication
@EnableJpaRepositories("com.*")
@EntityScan("com.apress.spring.domain")
public class DemoApplication {
	@Autowired
	private JournalRepository repo;
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	InitializingBean saveData(JournalRepository repo){
		return () -> {
			repo.save(new Journal("Get to know Spring Boot","Today I will learn Spring 					Boot","01/01/2016"));
			repo.save(new Journal("Simple Spring Boot Project","I will do my first Spring 							Boot Project","01/02/2016"));
			repo.save(new Journal("Spring Boot Reading","Read more about Spring 									Boot","02/01/2016"));
			repo.save(new Journal("Spring Boot in the Cloud","Spring Boot using Cloud 											Foundry","03/01/2016"));
		};
	}
}
