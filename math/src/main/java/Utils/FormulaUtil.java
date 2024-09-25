package Utils;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class FormulaUtil {
    @Test
    /**
     * Description:  根据传入的参数随机生成计算式子，包含真分数
     * date: 2024/9/24 20:20
     * @param m
     * @param n
     * @param symbol
     * @param strArray
     * @author: wzr
     * @return :
     */
    public static String[] CreateFormula(int m, int n) {
        String[] symbol = {"+","-","*","÷"};
        String[] strArray=new String [n];
        Random rand = new Random();
        for (int j = 0; j < n; j++) {
            boolean flag = true;
            int kuohao = 0;
            String subject = "";
            int operator = rand.nextInt(3) + 1;//操作符个数

            for (int i = 1; i <= operator; i++) {
                int temp = rand.nextInt(3) + 1;
                if (temp == 1)//决定是否加入左括号

                {

                    subject += "(";

                    kuohao++;
                    if(Math.random()>0.5){
                        subject += (int) (Math.random() * m + 1);

                        subject += symbol[(int) (Math.random() * 4)];
                    }
                    else {
                        subject =subject+ (int) (Math.random() * m + 1)+'\'' +(int) (Math.random() * m + 1)+'/'+(int) (Math.random() * m + 1);

                        subject += symbol[(int) (Math.random() * 4)];
                    }

                } else if (temp == 2 && flag) {//决定是否加入左括号

                    flag = false;

                    subject = "(" + subject;

                    kuohao++;

                    if(Math.random()>0.5){
                        subject += (int) (Math.random() * m + 1);

                        subject += symbol[(int) (Math.random() * 4)];
                    }
                    else {
                        subject =subject+ (int) (Math.random() * m + 1)+'\'' +(int) (Math.random() * m + 1)+'/'+(int) (Math.random() * m + 1);

                        subject += symbol[(int) (Math.random() * 4)];
                    }

                } else if (temp == 3 && kuohao != 0) {//决定是否加入右括号

                    subject += (int) (Math.random() * m + 1);

                    subject += ")";

                    kuohao--;

                    subject += symbol[(int) (Math.random() * 4)];

                } else {

                    subject += (int) (Math.random() * m + 1);

                    subject += symbol[(int) (Math.random() * 4)];

                }
            }

            subject += (int) (Math.random() * m + 1);

            for (int i = kuohao; i > 0; i--) {
                subject = subject + ")";
            }
            strArray[j] = subject;

        }
     return strArray;
    }


}
