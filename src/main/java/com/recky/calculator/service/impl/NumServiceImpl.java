package com.recky.calculator.service.impl;

import com.recky.calculator.service.NumService;
import org.springframework.stereotype.Service;

/**
 * Created by reckywangbowen_i on 2019/01/09
 */
@Service
public class NumServiceImpl implements NumService {
    @Override
    public String getMinusResult(String s1, String s2) {
        long number1 = Long.parseLong(s1);
        long number2 = Long.parseLong(s2);
        long result = number1 - number2;
        return String.valueOf(result);
    }

    @Override
    public String getMultiplyResult(String s1, String s2) {
        long number1 = Long.parseLong(s1);
        long number2 = Long.parseLong(s2);
        long result = number1 * number2;
        return String.valueOf(result);
    }

    @Override
    public String getDivideResult(String s1, String s2) {
        long number1 = Long.parseLong(s1);
        long number2 = Long.parseLong(s2);
        if (number2 != 0) {
            long result = number1 / number2;
            return String.valueOf(result);
        } else {
            return "除数不为0";
        }
    }

    @Override
    public String getAddResult(String s1, String s2) {
        long number1 = Long.parseLong(s1);
        long number2 = Long.parseLong(s2);
        long result = number1 + number2;
        return String.valueOf(result);
    }
}
