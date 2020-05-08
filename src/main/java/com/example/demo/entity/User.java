package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value={"password","ssn"})
@JsonFilter("UserInfo")
public class User {
    private Integer id;

    @NotNull
    @Size(min=2, message="Name은 2글자 이상 입력해주세요.")
    private String name;

    @Past
    private Date joinDate;

    //@JsonIgnore
    @NotNull
    @Size(min=6, max=16)
    private String password;
    //@JsonIgnore
    private String ssn;
}
