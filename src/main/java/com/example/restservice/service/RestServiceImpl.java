package com.example.restservice.service;

import com.example.restservice.model.Address;
import com.example.restservice.model.Person;
import com.example.restservice.vo.AddressVo;
import com.example.restservice.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RestServiceImpl implements RestService {


    public static List<Person> personList;
    public static List<Address> addressList;

    //initialize static DB
    static {
        addressList = Arrays.asList(new Address("EC23CD", 1), new Address("EC11QR", 2));
        personList = Arrays.asList(new Person("Jack", 1, addressList));
    }

    /**
     * @apiNote Update Person details based on person id and name
     * */
    @Override
    public Person updatePerson(RequestVo requestVo) {
        for (Person p : personList) {
            if (p.getName().equals(requestVo.getName()) && requestVo.getId() == p.getId()) {
                List<Address> adList = new ArrayList<>();
                for (AddressVo addVo : requestVo.getAddressVO()) {
                    adList.add(new Address(addVo.getPostcode(), addVo.getId()));
                }
                addressList = adList;
                p.setAddress(addressList);
                return p;
            }
        }
        return null;
    }

    /**
     * @apiNote Get Address from 3rd party API based on the post code
     * */
    @Override
    public Person getPostCode(Integer id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            List<Address> adList = new ArrayList<>();
            List<Address> updatedList = addressList.stream().map(address -> {
                return restTemplate.getForObject("http://localhost:8081/api/v1/address/postcodes/" + address.getPostcode(), Address.class);
            }).collect(Collectors.toList());
            Person p = personList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
            return new Person(p.getName(), p.getId(), updatedList);
        }
        catch(Exception e){
            System.out.println(e.getMessage()+" The API might be down. Please contact customer care...");
        }
        return null;
    }
}
