package com.recky.calculator.controller;

import com.recky.calculator.service.impl.NumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by reckywangbowen_i on 2019/01/09
 */

@RestController
@RequestMapping(path = "/number")
public class NumCalculatorController {
    @Autowired
    private NumServiceImpl numService;


    @GetMapping(path = "/add")
    public String add(@RequestParam String s1, @RequestParam String s2) {
        return numService.getAddResult(s1, s2);

    }

    @GetMapping(path = "/minus")
    public String minus(@RequestParam String s1, @RequestParam String s2) {
        return numService.getMinusResult(s1, s2);
    }

    @GetMapping(path = "/multiply")
    public String multiply(@RequestParam String s1, @RequestParam String s2) {
        return numService.getMultiplyResult(s1, s2);
    }

    @GetMapping(path = "/divide")
    public String divide(@RequestParam String s1, @RequestParam String s2) {
        return numService.getDivideResult(s1, s2);
    }
}
