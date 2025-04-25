package org.saiteja.userserviceauthentication.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.saiteja.userserviceauthentication.dtos.SendEmailDTO;
import org.saiteja.userserviceauthentication.models.Token;
import org.saiteja.userserviceauthentication.models.User;
import org.saiteja.userserviceauthentication.repositories.TokenRepository;
import org.saiteja.userserviceauthentication.repositories.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    private KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository,
                       TokenRepository tokenRepository,KafkaTemplate kafkaTemplate,ObjectMapper objectMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository=tokenRepository;
        this.kafkaTemplate=kafkaTemplate;
        this.objectMapper=objectMapper;
    }



    public User signUpService(String name, String email, String password) throws JsonProcessingException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        User user1=userRepository.save(user);
        /*
        after signup i want to publish this event to message queue()kafka.
         */
        SendEmailDTO sendEmailDTO=new SendEmailDTO();
        sendEmailDTO.setFrom("admin@scaler.com");
        sendEmailDTO.setTo("rapellisaiteja154@gmail.com");
        sendEmailDTO.setSubject("Application status");
        sendEmailDTO.setBody("We are looking into the application , we will get back to you ");
        kafkaTemplate.send("send_Email",
                objectMapper.writeValueAsString(sendEmailDTO)
        );
        //kafka queue - [{send_Email, {"to" : "" , from : "" , "body}]

        return user1;
    }

    public Token login(String email, String password){

        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            return null;
            //throw new UserNotFoundException("");
        }
        User user=optionalUser.get();

        //validate password.
        //bcrypt gives a new hash every time even for the same password .
        // so we match raw passwor dwith hashed password.
        if(!(bCryptPasswordEncoder.matches(password,user.getHashedPassword()))){
            // password is invalid , throw invalid exception.

        }

        //business logic of how much time you need a token for
        //today + 30 days

        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);

        Date thirtyDaysLaterdate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token=new Token();
        token.setUser(user);
        token.setExpiryAt(thirtyDaysLaterdate);

        //JWT -> A.B.C https://github.com/jwtk/jjwt

        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        Token savedToken = tokenRepository.save(token);
        return savedToken;

    }


    public void logout(String token) {

        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedEquals(token, false);

        if(optionalToken.isEmpty()){
            //return token doesn't exist exception
            return;
        }
        Token tkn = optionalToken.get();
        tkn.setDeleted(true); //soft deleted

        tokenRepository.save(tkn);
        return;
    }


    public User validateToken(String token){
        Optional<Token> optionalToken=tokenRepository.findByValueAndDeletedEqualsAndExpiryAtGreaterThan(token,false,new Date());
        if(optionalToken.isEmpty()){
            // token is invalid .
            return null;
        }
        return optionalToken.get().getUser();
    }

    public User getUserDetails(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new RuntimeException("User with userId + " + userId + " not present");
        }

        return optionalUser.get();
    }


}