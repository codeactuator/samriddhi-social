package com.codeactuator.samriddhi.controller;

import com.codeactuator.samriddhi.dto.RelativeDTO;
import com.codeactuator.samriddhi.services.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public RelativeDTO create(@RequestBody RelativeDTO relativeDTO){
        return relativeService.save(relativeDTO).get();
    }

}
