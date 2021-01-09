package com.codeactuator.samriddhi.controller;

import com.codeactuator.samriddhi.dto.RelativeDTO;
import com.codeactuator.samriddhi.services.RelativeCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("v1/calculate")
public class CalculationController {

    @Autowired
    private RelativeCalculationService calculationService;

    @GetMapping("/ping")
    public String ping(){
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return "OK: " + format.format(date);
    }


    @GetMapping("/{relativeOneId}/{relativeSomeoneId}/{relativeLinkedId}")
    public RelativeDTO findMyRelationWithSomeOne(
            @PathVariable(value = "relativeOneId") Long relativeOneId,
            @PathVariable(value = "relativeSomeoneId") Long relativeSomeoneId,
            @PathVariable(value = "relativeLinkedId") Long relativeLinkedId){

        RelativeDTO relativeDTO = calculationService.find(relativeOneId, relativeSomeoneId, relativeLinkedId);

        return relativeDTO;
    }
}
