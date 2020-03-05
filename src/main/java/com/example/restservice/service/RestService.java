package com.example.restservice.service;

import com.example.restservice.model.Person;
import com.example.restservice.vo.RequestVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestService {

    Person updatePerson(RequestVo requestVo);


    /*Person findByPersonId(int id);*/

    Person getPostCode(Integer id);
}

