package org.saiteja.emailservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.saiteja.emailservice.dto.SendEmailDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailEventConsumer {

    private ObjectMapper objectMapper;

    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "send_Email",groupId = "emailService")
    public void handleSendEmailEvents(String message) throws JsonProcessingException {

      SendEmailDTO sendEmailDTO= objectMapper.readValue(message, SendEmailDTO.class);
        String to = sendEmailDTO.getTo();
        String from = sendEmailDTO.getFrom();
        String body = sendEmailDTO.getBody();
        String subject = sendEmailDTO.getSubject();

        System.out.println("Sending email process starts");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS


        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rapellisai315@gmail.com", "qjfiievwz");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, to,subject, body);

    }

}
