package cc.fs.sample.com;

import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法设计
 */

public class ComUtil {

    /**
     * 输出类似
     * 　　　　     1
     * 　　　　    1 1
     * 　　　　   1 2 1
     * 　　　　  1 3 3 1
     * 　　　　 1 4 6 4 1
     * 　　　　1 5 10 10 5 1
     */
    public static int yanghui(int lines) {
        int sum = 0;
        List<Integer> numList = new ArrayList<>();
        for (int line = 0; line < lines; line++) {

            //第一位为1
            numList.add(1);
            sum++;
            if (sum < 3) {
                continue;
            } else if (sum == 3) {
                numList.add(1);
                sum++;
            }

            List<Integer> tempList = new ArrayList<>();
            for (int i = sum - line - 1; i < sum - 1; i++) {
                tempList.add(numList.get(i));
            }
            for (int i = 0; i < tempList.size() - 1; i++) {
                numList.add(tempList.get(i) + tempList.get(i + 1));
                sum++;
            }
            //最后一位是1
            numList.add(1);
            sum++;
        }


        StringBuilder strBuilder = new StringBuilder();
        int index = 0;
        for (int line = 0; line < lines; line++) {
            if (line == 0) {
                strBuilder.append("1" + "\n");
                index++;
                continue;
            }

            for (int j = 0; j < line + 1; j++) {
                strBuilder.append(numList.get(index) + " ");
                index++;

                if (j == line) {
                    strBuilder.append("\n");
                }
            }
        }

        System.out.print(strBuilder.toString());

        return numList.size();
    }


    /**
     * 一开始一群人数到3淘汰,只能留下一个人
     */
    public static int quit3(int num) {
        List<String> peoples = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            peoples.add(String.valueOf(i + 1));
        }

        int round = 0;//第几轮被淘汰
        int comTime = 1;//数数次数
        while (true) {
            if (peoples.size() == 1) {
                System.out.print("玩家" + peoples.get(0) + "赢得游戏");
                break;
            }

            round++;
            int curIndex = 0; //计算到哪个身上
            int time = 0;//当前判断次数
            int numTime = peoples.size();//当前参与参与人数
            List<String> outPeople = new ArrayList<>(); //被out的人
            while (time < numTime) {
                if (comTime / 3 == 1) {
                    //out
                    comTime = 0; //重新计算
                    outPeople.add("" + peoples.get(curIndex));//记录淘汰玩家编号
                }

                //开始计算淘汰
                comTime++;
                //到下一个数数
                curIndex++;
                if (curIndex == peoples.size()) {
                    curIndex = 0;
                }

                //计算每一轮次数
                time++;
            }

            //一轮结束移除玩家
            for (int i = 0; i < outPeople.size(); i++) {
                System.out.println("玩家" + outPeople.get(i) + "在第" + round + "轮结束游戏");
                peoples.remove("" + outPeople.get(i));
            }
        }

        return peoples.size();
    }


    /**
     * 字符翻转
     * 输入: the sky is blue  输出:blue is sky the
     */
    public static String reverseStr(String str) {
        if (str.isEmpty()) {
            return "";
        }

        String word = "";//单词
        String result = "";//句子

        int pos = 0;
        while (pos < str.length()) {
            char letter = str.charAt(pos);
            // 空格
            if (letter == 0x20) {
                word = letter + word;
                result = word + result;
                word = "";
            } else if (pos == str.length() - 1) {
                word = word + letter;
                result = word + result;
            } else {
                word = word + letter;
            }
            pos++;
        }

        System.out.println("结果: " + result);

        return result;
    }


}
