package com.example.indatacoreback.CsvMapping;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMappingCsv {
    @CsvBindByName(column = "userName")
    private String userName;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "firstName")
    private String firstName;
    @CsvBindByName(column = "lastName")
    private String lastName;
}
