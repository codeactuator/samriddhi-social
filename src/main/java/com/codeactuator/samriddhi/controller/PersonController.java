package com.codeactuator.samriddhi.controller;

import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/ping")
    public String ping(){
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return "OK: " + format.format(date);
    }


    @GetMapping
    public List<PersonDTO> findAll(){
        return personService.findAll().get();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable(value = "id") Long id){
        return personService.findById(id).get();
    }

    @PostMapping
    public PersonDTO create(@RequestBody PersonDTO personDTO){
        return personService.save(personDTO).get();
    }
}
