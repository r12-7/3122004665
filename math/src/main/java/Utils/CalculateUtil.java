package Utils;


import Bean.Fraction;

import java.math.BigDecimal;
import java.util.Stack;


public class CalculateUtil {

    /**
     * @param :str是传入的中缀表达式
     * @return :返回后缀表达式
     * @desc :使用将中缀表达式转化为后缀表达式
     */
    public static String transform(String str) throws Exception {
        Stack<Character> stack_symbol = new Stack<>();//符号栈

        //存放后缀表达式
        StringBuilder tempStr = new StringBuilder();
        //将传入的中缀字符串存入数组
        char[] chars = str.toCharArray();
        char temp;

        //转化为后缀表达式
        int i;
        for (i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]) || chars[i] == '\'' || chars[i] == '/') {//若为数字则加入后缀表达式
                tempStr.append(chars[i]);
                if (i + 1 < chars.length && (chars[i + 1] == '+' || chars[i + 1] == '-' || chars[i + 1] == '÷' || chars[i + 1] == '*' || chars[i + 1] == ')' || chars[i + 1] == ' ')) {
                    tempStr.append(",");
                } else if (i + 1 == chars.length) {
                    tempStr.append(",");
                }
            } else if (chars[i] == ' ') {//若为空格‘ ’则直接跳过
                continue;
            } else if (chars[i] == '(') {//若为左括号(，将其入栈
                stack_symbol.push(chars[i]);
            } else if (chars[i] == '*' || chars[i] == '÷') {//若为乘或除
                while (!stack_symbol.isEmpty()) {
                    //取出栈顶元素
                    temp = stack_symbol.peek();
                    if (temp == '*' || temp == '÷') {
                        stack_symbol.pop();
                        tempStr.append(temp + ",");
                    } else {
                        break;
                    }
                }
                stack_symbol.push(chars[i]);
            } else if (chars[i] == '+' || chars[i] == '-') {//若为加号与减号
                while (!stack_symbol.isEmpty()) {
                    //取出栈顶元素
                    temp = stack_symbol.peek();
                    if (temp != '(') {
                        stack_symbol.pop();
                        tempStr.append(temp + ",");
                    } else {
                        break;
                    }
                }
                stack_symbol.push(chars[i]);
            } else if (chars[i] == ')') {
                //如果遇到右括号，则执行出栈操作，依次将出栈元素追加到后缀表达式，直到出栈元素是左括号为止
                while (!stack_symbol.isEmpty() && (temp = stack_symbol.pop()) != '(') {
                    tempStr.append(temp + ",");
                }
            }
        }
        //最后依次出栈，添加进后缀表达式
        while (!stack_symbol.isEmpty()) {
            tempStr.append(stack_symbol.pop() + ",");
        }
        String result = tempStr.toString();
        return result;
    }

    /**
     * @param :传入后缀表达式str
     * @return :返回计算结果
     * @desc :根据后缀表达式计算出结果
     */
    public static String calculate(String str) {
        // Stack<Character> stack_result  = new Stack<>();//临时栈，用于计算并存放后缀表达式的结果
        String[] strings = str.split(",");
        Stack<BigDecimal> stack = new Stack<>();
        Stack<Fraction> stack_f = new Stack<>();
        boolean flag = true;
        for (String string : strings) {//检查运算式中是否含有分数
            if (string.contains("/")) {
                flag = false;
                break;
            }
        }

        if (flag == true) {//运算式中不含分数
            //将字符串数组中的数字形式字符串进行格式转化，String->BigDecimal,推入栈中，并进行计算
            BigDecimal bigDecimal;
            for (String s : strings) {
                if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("÷")) {
                    //数字进栈
                    bigDecimal = new BigDecimal(s);
                    stack.push(bigDecimal);
                } else {
                    BigDecimal b1 = stack.pop();
                    BigDecimal b2 = stack.pop();
                    switch (s) {
                        case "+":
                            stack.push(b2.add(b1));
                            break;
                        case "-":
                            stack.push(b2.subtract(b1));
                            break;
                        case "*":
                            stack.push(b2.multiply(b1));
                            break;
                        case "÷":
                            stack.push(b2.divide(b1, BigDecimal.ROUND_CEILING));
                            break;
                    }
                }
            }
            return String.valueOf(stack.peek());
        } else {
            //运算式中含有分数
            for (String s : strings) {
                if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("÷")) {
                    //将每个数字都转化为分数后进栈
                    if (s.contains("'")) {
                        //如果是带分数，调用getResult()方法
                        stack_f.push(Fraction.getResult(s));
                    } else {
                        stack_f.push(Fraction.turnToFraction(s));
                    }

                } else {
                    Fraction p1 = stack_f.pop();
                    Fraction p2 = stack_f.pop();
                    switch (s) {
                        case "+":
                            stack_f.push(p1.plus(p2));
                            break;
                        case "-":
                            stack_f.push(p1.sub(p2));
                            break;
                        case "*":
                            stack_f.push(p1.multiply(p2));
                            break;
                        case "÷":
                            stack_f.push(p2.division(p1));
                            break;
                    }

                }
            }
            return stack_f.peek().simplify().turnToString();
        }

    }


}
