package com.codeactuator.samriddhi.controller;

import com.codeactuator.samriddhi.dto.RelativeDTO;
import com.codeactuator.samriddhi.services.RelationService;
import com.codeactuator.samriddhi.services.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1/relatives")
public class RelativeController {


    @Autowired
    private RelativeService relativeService;

    @Autowired
    private RelationService relationService;

    @GetMapping("/ping")
    public String ping(){
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return "OK: " + format.format(date);
    }


    @GetMapping
    public List<RelativeDTO> findAll(){
        return relativeService.findAll().get();
    }


    @GetMapping("/{id}")
    public RelativeDTO findById(@PathVariable(value = "id") Long id){
        return relativeService.findById(id).get();
    }
}
