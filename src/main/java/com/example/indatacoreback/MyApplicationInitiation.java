package com.example.indatacoreback;

import com.example.indatacoreback.Services.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationInitiation implements ApplicationRunner {

    @Autowired
    private  UserService userService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String filePath = "C:\\Users\\Asus\\Desktop\\real-indatacore-test\\indatacoreback\\user-data.csv";
        int numberOfUsersSaved = userService.saveUsers(filePath);
        System.out.println("Number of users saved: " + numberOfUsersSaved);
    }
}
