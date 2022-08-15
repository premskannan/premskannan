package com.microservice.SQSToLambdaToSES.Lambda;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.microservice.SQSToLambdaToSES.SES.SESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LambdaFunctionHandler implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent event, Context context)
    {
        for(SQSEvent.SQSMessage msg : event.getRecords()){
            System.out.println(new String(msg.getBody()));
            this.sendMessage(msg.getBody());
        }
        return null;
    }
    private void sendMessage(String message) {
        AmazonSimpleEmailService service = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAWZPNMDCGKFVMH6YF", "CO5T8Ot9WePSRU8rDnV8mh4KMbUc5+L14VeYsJX4")))
                .withRegion(Regions.US_EAST_1)
                .build();
        SendEmailRequest request = new SendEmailRequest();
        request.withMessage(new Message(new Content("TEst Email from microservice"), new Body(new Content(message)))).withSource("madragon16@gmail.com").withDestination(new Destination(Arrays.asList("prem.skannan@gmail.com")));
        SendEmailResult result = service.sendEmail(request);
    }
}
