package com.microservice.SQSToLambdaToSES.SES;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SESService {
    @Autowired
    private AmazonSimpleEmailService service;

    public void sendMessage(String message) {
        SendEmailRequest request = new SendEmailRequest();
        request.withMessage(new Message(new Content("TEst Email from microservice"), new Body(new Content(message)))).withSource("madragon16@gmail.com").withDestination(new Destination(Arrays.asList("prem.skannan@gmail.com")));
        SendEmailResult result = service.sendEmail(request);
    }
}
