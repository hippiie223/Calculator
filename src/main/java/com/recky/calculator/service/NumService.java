package com.recky.calculator.service;

import java.util.List;

/**
 * Created by reckywangbowen_i on 2019/01/09
 */
public interface NumService {
    public String getAddResult(String s1, String s2);

    public String getMinusResult(String s1, String s2);

    public String getMultiplyResult(String s1, String s2);

    public String getDivideResult(String s1, String s2);

    public String getIncomeTax(String salary, String deduction);

    public Double prepareParam(String str);

    public Double doCalculate(String symStr, List<Double> numLst);
}
