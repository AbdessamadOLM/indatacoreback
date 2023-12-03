package com.example.indatacoreback.DTO;

import com.example.indatacoreback.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private String userName;
    private String email;
    private String firstName;
    private String lastName;

    public static User mapToUserEntity(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }


}


