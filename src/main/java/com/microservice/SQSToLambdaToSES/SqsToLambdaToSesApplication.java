package com.microservice.SQSToLambdaToSES;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SqsToLambdaToSesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsToLambdaToSesApplication.class, args);
	}

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailServiceClient() {
		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(new ProfileCredentialsProvider("pratikpoc"))
				.withRegion(Regions.US_EAST_1)
				.build();
	}
}
