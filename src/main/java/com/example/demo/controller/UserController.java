package com.example.demo.controller;

import com.example.demo.dao.UserDaoService;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> getUserList(){
        List<User> list = service.getUserList();
        //list의 내용 출력
        for (User user : list) {
            //System.out.println(user);
            //error, warn, info, debug
            log.info(user.toString());
        }
        return list;
    }

    @GetMapping("/users/{id}")
    public MappingJacksonValue getUser(@PathVariable(value="id") Integer id){
        User user = service.getUser(id);

        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(provider);

        return mapping;
    }

    @GetMapping("/admin/users/{id}")
    public MappingJacksonValue getUserByAdmin(@PathVariable(value = "id") Integer id){
        User user = service.getUser(id);

        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider provider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(provider);

        return mapping;
    }
}
