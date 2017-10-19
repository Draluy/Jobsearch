package fr.raluy.jobsearchback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"fr.raluy.jobsearchback"})
public class JobsearchBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsearchBackApplication.class, args);
	}
}
