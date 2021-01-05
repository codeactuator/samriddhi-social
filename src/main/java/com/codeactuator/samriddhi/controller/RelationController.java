package com.codeactuator.samriddhi.controller;


import com.codeactuator.samriddhi.dto.PersonDTO;
import com.codeactuator.samriddhi.dto.RelationDTO;
import com.codeactuator.samriddhi.services.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/relations")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @GetMapping("/ping")
    public String ping(){
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return "OK: " + format.format(date);
    }


    @GetMapping
    public List<RelationDTO> findAll(){
        return relationService.findAll().get();
    }


    @GetMapping("/{id}")
    public RelationDTO findById(@PathVariable(value = "id") Long id){
        return relationService.findById(id).get();
    }


    @PostMapping
    public RelationDTO create(@RequestBody RelationDTO relationDTO){
        return relationService.save(relationDTO).get();
    }

}
