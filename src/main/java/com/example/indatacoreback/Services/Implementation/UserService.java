package com.example.indatacoreback.Services.Implementation;

import com.example.indatacoreback.CsvMapping.UserMappingCsv;
import com.example.indatacoreback.DTO.UserDto;
import com.example.indatacoreback.Models.User;
import com.example.indatacoreback.Repository.IUserRepository;
import com.example.indatacoreback.Services.Interfaces.IUserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    public Integer saveUsers(String filePath) throws IOException {
        File file = new File(filePath);
        Set<User> students = parseCsv(file);
        userRepository.saveAll(students);
        return students.size();
    }

    public Set<User> parseCsv(File  file) throws IOException {
        try(Reader reader = new BufferedReader(new FileReader(file))) {
            HeaderColumnNameMappingStrategy<UserMappingCsv> strategy =
                    new HeaderColumnNameMappingStrategy<>();
            strategy.setType(UserMappingCsv.class);
            CsvToBean<UserMappingCsv> csvToBean =
                    new CsvToBeanBuilder<UserMappingCsv>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> User.builder()
                            .userName(csvLine.getUserName())
                            .email(csvLine.getEmail())
                            .firstName(csvLine.getFirstName())
                            .lastName(csvLine.getLastName())
                            .build()
                    )
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        if(!users.isEmpty()) {
            for (User user:users) {
                usersDto.add(UserDto.mapToUserDto(user));
            }
            return usersDto;
        }else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found");
    }

    public User addUser(UserDto userDto) {
        return userRepository.save(UserDto.mapToUserEntity(userDto));
    }

    @Override
    public User addGeneratedUser() {
        return userRepository.save(generateUser());
    }
    public User generateUser(){
        List<String> listePrenoms = new ArrayList<>(List.of("Alice", "Bob", "Charlie", "David", "Eva"));
        List<String>  boites = new ArrayList<>(List.of("yahoo","gmail","uca","outlook", "example"));
        Random random = new Random();
        String username = "user"+generateRandomString(7);
        String firstname = listePrenoms.get(random.nextInt(listePrenoms.size()))
                +generateRandomString(2);
        String lastname = generateRandomString(6);
        String boit = boites.get(random.nextInt(boites.size()));
        String email = username+random.nextInt(1000)+boit+".ma";

        return User.builder()
                .userName(username)
                .email(email)
                .firstName(firstname)
                .lastName(lastname)
                .build();
    }


    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
