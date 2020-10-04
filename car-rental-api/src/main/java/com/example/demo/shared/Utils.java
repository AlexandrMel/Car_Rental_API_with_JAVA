package com.example.demo.shared;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import com.example.demo.ui.model.request.UserDetailsRequestModel;
import org.springframework.stereotype.Component;


//Creating Utils class with methods that generate random string UserId
@Component
public class Utils {
 private final Random RANDOM = new SecureRandom();
 private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	//Methods that can be accessed
 public String generateUserId(int length) {
     return generateRandomString(length);
 }
 //Privat methods that holds the logic and cannot be accessed
 private String generateRandomString(int length) {
     StringBuilder returnValue = new StringBuilder(length);

     for (int i = 0; i < length; i++) {
         returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
     }

     return new String(returnValue);
 }
    public Boolean ageVerification(UserDetailsRequestModel userDetails) {
        LocalDate birthday = userDetails.getBirthday();
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthday, today);
        System.out.println(period.getYears());
        Integer age = period.getYears();
        Boolean verify = age < 18 ? false : true;
        return verify;
    }

	
}
