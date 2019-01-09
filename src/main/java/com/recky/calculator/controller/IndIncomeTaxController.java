package com.recky.calculator.controller;

import com.recky.calculator.service.NumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by reckywangbowen_i on 2019/01/09
 */
@RestController
@RequestMapping(path = "/tax")
public class IndIncomeTaxController {

    @Autowired
    private NumService numService;

    @GetMapping(path = "/income")
    public String getIncomeTax(@RequestParam String salary, @RequestParam String deduction) {
        return numService.getIncomeTax(salary, deduction);
    }
}
