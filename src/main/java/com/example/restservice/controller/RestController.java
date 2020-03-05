package com.example.restservice.controller;

import com.example.restservice.service.RestService;
import com.example.restservice.vo.RequestVo;
import com.example.restservice.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.example.restservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    RestService restService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "Retrieve Person details by postcode", notes = "Retrieve Person details by postcode")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid input supplied"),
            @ApiResponse(code = 404, message = "Postcode not found"),
            @ApiResponse(code = 200, message = "Successful response send"),
            @ApiResponse(code = 500, message = "Server error")})
    @RequestMapping(value = "/api/v1/people/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Person getDetails(@PathVariable("id") Integer id) {
        Person p = restService.getPostCode(id);
        return p;
    }

    @ApiOperation(value = "update Person details ", notes = "Update Person details ")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid input supplied"),
            @ApiResponse(code = 404, message = "Value not found"),
            @ApiResponse(code = 200, message = "Successful response send"),
            @ApiResponse(code = 500, message = "Server error")})
    @RequestMapping(value = "/api/v1/people/{id}", consumes = "application/json", method = RequestMethod.PUT)
    public Integer updatePerson(@RequestBody RequestVo requestVo, @PathVariable("id") Integer id) {
        ResponseVO resp = new ResponseVO();
        requestVo.setId(id);
        Person newPerson = restService.updatePerson(requestVo);
        resp.setPerson(newPerson);
        resp.setStatus("Success!");
        return resp.getPerson().getId();
    }
}
