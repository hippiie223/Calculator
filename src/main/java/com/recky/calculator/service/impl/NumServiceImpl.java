package com.recky.calculator.service.impl;

import com.recky.calculator.common.Constant;
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

    @Override
    public String getIncomeTax(String salary, String deduction) {
        double salaryNum = Double.parseDouble(salary) - Constant.TAX_START;
        double deductionNum = Double.parseDouble(deduction);
        double ratal = salaryNum - deductionNum;
        if (salaryNum <= 0) {
            return "不需要交税";
        }
        double result = 0;
        if (ratal <= Constant.CLASS_1) {
            result = ratal * Constant.TAX_1 - Constant.SHORTCUN_NUM_1;
        } else if (ratal > Constant.CLASS_1 && ratal <= Constant.CLASS_2) {
            result = ratal * Constant.TAX_2 - Constant.SHORTCUN_NUM_2;
        } else if (ratal > Constant.CLASS_2 && ratal <= Constant.CLASS_3) {
            result = ratal * Constant.TAX_3 - Constant.SHORTCUN_NUM_3;
        } else if (ratal > Constant.CLASS_3 && ratal <= Constant.CLASS_4) {
            result = ratal * Constant.TAX_4 - Constant.SHORTCUN_NUM_4;
        } else if (ratal > Constant.CLASS_4 && ratal <= Constant.CLASS_5) {
            result = ratal * Constant.TAX_5 - Constant.SHORTCUN_NUM_5;
        } else if (ratal > Constant.CLASS_5 && ratal <= Constant.CLASS_6) {
            result = ratal * Constant.TAX_6 - Constant.SHORTCUN_NUM_6;
        } else {
            result = ratal * Constant.TAX_7 - Constant.SHORTCUN_NUM_7;
        }

        return String.valueOf(result);
    }
}
