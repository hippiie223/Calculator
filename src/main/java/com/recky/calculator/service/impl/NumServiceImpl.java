package com.recky.calculator.service.impl;

import com.recky.calculator.common.Constant;
import com.recky.calculator.common.MyUtils;
import com.recky.calculator.service.NumService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    @Override
    public Double prepareParam(String str) {
        if (null == str || "".equals(str)) {
            return null;
        }
        // 长度校验
        if (str.length() > MyUtils.FORMAT_MAX_LENGTH) {
            System.out.println("表达式过长！");
            return null;
        }
        // 预处理
        str = str.replaceAll(" ", "");// 去空格
        if ('-' == str.charAt(0)) {// 开头为负数，如-1，改为0-1
            str = 0 + str;
        }
        // 校验格式
        if (!MyUtils.checkFormat(str)) {
            System.out.println("表达式错误！");
            return null;
        }
        // 处理表达式，改为标准表达式
        str = MyUtils.change2StandardFormat(str);
        // 拆分字符和数字
        String[] nums = str.split("[^.0-9]");
        List<Double> numLst = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (!"".equals(nums[i])) {
                numLst.add(Double.parseDouble(nums[i]));
            }
        }
        String symStr = str.replaceAll("[.0-9]", "");
        return doCalculate(symStr, numLst);
    }

    @Override
    public Double doCalculate(String symStr, List<Double> numLst) {
        LinkedList<Character> symStack = new LinkedList<>();// 符号栈
        LinkedList<Double> numStack = new LinkedList<>();// 数字栈
        double result = 0;
        int i = 0;// numLst的标志位
        int j = 0;// symStr的标志位
        char sym;// 符号
        double num1, num2;// 符号前后两个数
        while (symStack.isEmpty() || !(symStack.getLast() == '=' && symStr.charAt(j) == '=')) {// 形如：
            // =8=
            // 则退出循环，结果为8
            if (symStack.isEmpty()) {
                symStack.add('=');
                numStack.add(numLst.get(i++));
            }
            if (MyUtils.symLvMap.get(symStr.charAt(j)) > MyUtils.symLvMap.get(symStack.getLast())) {// 比较符号优先级，若当前符号优先级大于前一个则压栈
                if (symStr.charAt(j) == '(') {
                    symStack.add(symStr.charAt(j++));
                    continue;
                }
                numStack.add(numLst.get(i++));
                symStack.add(symStr.charAt(j++));
            } else {// 当前符号优先级小于等于前一个 符号的优先级
                if (symStr.charAt(j) == ')' && symStack.getLast() == '(') {// 若（）之间没有符号，则“（”出栈
                    j++;
                    symStack.removeLast();
                    continue;
                }
                if (symStack.getLast() == '(') {// “（”直接压栈
                    numStack.add(numLst.get(i++));
                    symStack.add(symStr.charAt(j++));
                    continue;
                }
                num2 = numStack.removeLast();
                num1 = numStack.removeLast();
                sym = symStack.removeLast();
                switch (sym) {
                    case '+':
                        numStack.add(MyUtils.plus(num1, num2));
                        break;
                    case '-':
                        numStack.add(MyUtils.reduce(num1, num2));
                        break;
                    case '*':
                        numStack.add(MyUtils.multiply(num1, num2));
                        break;
                    case '/':
                        if (num2 == 0) {// 除数为0
                            System.out.println("存在除数为0");
                            return null;
                        }
                        numStack.add(MyUtils.divide(num1, num2));
                        break;
                }
            }
        }
        return numStack.removeLast();
    }
}

