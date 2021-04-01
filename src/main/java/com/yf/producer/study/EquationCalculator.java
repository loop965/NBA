package com.yf.producer.study;

import java.util.*;

/**
 * @author: yf
 * @date: 2020/12/21  14:00
 * @desc:
 */
public class EquationCalculator {

    /** 变量集合 **/
    private static List<String> varList = new LinkedList<>();
    /** 变量和值的key-value **/
    private static Map<String, Float> varListWithValue = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你需要计算的公式:");
        String formula = scanner.nextLine().replaceAll(" ", "");
        //String formula = "f=k * (((m1 + m2) * (40 * x + 60 * y)) + 100) * m2 / (G1 + G2)".replaceAll(" ", ""); fortest
        String equalationLeft = formula.split("=")[0];
        String equalationRight = formula.split("=")[1];
        /** 获取变量表: **/
        getVarList(equalationRight);
        /** 给变量赋值 **/
        assignmentVarList(varList);
        /** 代回公式进行替换 **/
        String newFormula = replaceEquation(formula);
        /** 进行计算 **/
        System.out.println();
        float result = calc(newFormula);
        System.out.println("最终结果:" + equalationLeft + "=" + result);
    }

    /** 进行计算 **/
    private static float calc(String newFormula) {
        boolean stillHaveCalcSymbol = false;
        do{
            //System.out.println("before:" + newFormula);
            /** 寻找最后一个左括号里面到第一个右括号里面1的内容 **/
            char formulaArray[] = newFormula.toCharArray();
            for (int i = 0; i < formulaArray.length; i++) {
                if (formulaArray[i] == '+' || formulaArray[i] == '-'
                        || formulaArray[i] == '*' || formulaArray[i] == '/'
                        || formulaArray[i] == '(' || formulaArray[i] == ')') {
                    stillHaveCalcSymbol = true;
                } else {
                    stillHaveCalcSymbol = false;
                }
            }
            if (stillHaveCalcSymbol) {
                String resultFormula = "";
                //找最内层的括号里面的内容出来（含括号）
                for (int i = 0; i < formulaArray.length; i++) {
                    if (formulaArray[i] == ')') {
                        int begin = 0;
                        for (int j = i; j >= 0; j--) {
                            if (formulaArray[j] == '(') {
                                begin = j;
                                break;
                            }
                        }
                        String calcString = newFormula.substring(begin, i + 1);
                        resultFormula = newFormula.replace(calcString, calcProc(calcString) + "");
                        //System.out.println(calcString);
                        break;
                    }
                }
                newFormula = resultFormula;
            }
        } while(stillHaveCalcSymbol);
        //最后得到普通的顺序无括号公式：
        System.out.println(newFormula);
        //最后一次计算:
        float result = calcProc("(" + newFormula.split("=")[1] + ")");
        return result;
    }

    /**详细计算过程**/
    private static float calcProc(String calcString) {
//		if(calcString.contains("=")){
//			calcString = calcString.split("=")[1];
//		}
        //calcString = calcString.replace("(", "");
        //calcString = calcString.replace(")", "");

        String calcSymbol[] = {"\\*", "\\/", "\\+", "\\-"};
        char calcSymbolChar[] = {'*', '/', '+', '-'};
        boolean haveSymbol = true;
        float result = 0f;
        while(haveSymbol){
            System.out.println("calcStr:" + calcString);
            char calcCharArr[] = calcString.toCharArray();
            result = 0f;
            for (int i = 0; i < calcSymbol.length; i++) {
                boolean alreadyFind = false;
                for(int j = 0; j < calcCharArr.length; j++){
                    if(calcCharArr[j] == calcSymbolChar[i]){
                        //System.out.println("找到了" + calcSymbolChar[i]);
                        //以符号为中心，以左右两边的其他符号为边界找到两边的数
                        float num1 = 0f;
                        float num2 = 0f;
                        int bottom = 0;
                        for(int k = j - 1; k >= 0 && (calcCharArr[k] >= '0' && calcCharArr[k] <= '9' || calcCharArr[k] == '.') ; k--){
                            //System.out.println(calcCharArr[k] + "");
                            bottom = k;
                        }
                        System.out.println("[j, bottom]:" + String.format("[%d, %d]", j, bottom));
                        num1 = Float.parseFloat(calcString.substring(bottom, j));
                        System.out.println("num1:" + num1);
                        int top = 0;
                        for(int k = j + 1; k < calcString.length() && (calcCharArr[k] >= '0' && calcCharArr[k] <= '9' || calcCharArr[k] == '.'); k++){
                            top = k;
                        }
                        num2 = Float.parseFloat(calcString.substring(j + 1, top + 1));
                        System.out.println("num2:" + num2);
                        switch(calcSymbolChar[i]){
                            case '*':
                                result = num1 * num2;
                                break;
                            case '/':
                                result = num1 / num2;
                                break;
                            case '+':
                                result = num1 + num2;
                                break;
                            case '-':
                                result = num1 - num2;
                                break;
                        }
                        //System.out.println("bottom to top:" + calcString.substring(bottom + 1, top + 1));
                        calcString = calcString.replace(calcString.substring(bottom, top + 1), String.format("%.5f", result));
                        //System.out.println("end_calcStr:" + calcString);
                        alreadyFind = true;
                        break;
                    }
                }
                if(alreadyFind) break;
            }
            haveSymbol = false;
            if(calcString.contains("*") || calcString.contains("/") || calcString.contains("+") || calcString.contains("-")){
                haveSymbol = true;
                //System.out.println("找到");
            } else {
                //System.out.println("找不到");
            }
        }
        //System.out.println("result:" + result);
        return result;
    }

    /** 代回公式进行替换 **/
    private static String replaceEquation(String formula) {
        String newFormula = new String(formula);
        for (String key : varList) {
            newFormula = newFormula.replaceAll(key, varListWithValue.get(key)
                    + "");
        }
        System.out.println(newFormula);
        return newFormula;
    }

    /**
     * 给变量赋值
     *
     * @param varList
     *            变量列表
     **/
    private static void assignmentVarList(List<String> varList) {
        System.out.println("请输入各变量的对应值");
        Scanner scanner = new Scanner(System.in);
        for (String key : varList) {
            System.out.print(key + "'s value is:");
            varListWithValue.put(key, scanner.nextFloat());
        }
		/*for (String key : varList) {
			System.out.println("key:" + key + ", value:"
					+ varListWithValue.get(key));
		}*/
    }

    /**
     * 获取变量表
     *
     * @param equalationRight
     *            输入等式的右边
     **/
    private static void getVarList(String equalationRight) {
        System.out.println("等式右边:" + equalationRight);
        char[] formulaCharArr;
        formulaCharArr = equalationRight.toCharArray();
        //清理所有运算符
        for (int i = 0; i < formulaCharArr.length; i++) {
            if (formulaCharArr[i] == '+' || formulaCharArr[i] == '-'
                    || formulaCharArr[i] == '*' || formulaCharArr[i] == '/'
                    || formulaCharArr[i] == '(' || formulaCharArr[i] == ')') {
                formulaCharArr[i] = ' ';
            }
        }
		/*String temp = "";
		for (int i = 0; i < formulaCharArr.length; i++) {
			if (formulaCharArr[i] == ' ') {
				String content = temp.trim();
				if (content.length() > 0) {
					boolean okGo = true;
					if(content.charAt(0) >= '0' && content.charAt(0) <= '9'){
						okGo = false;
					}
					if (okGo) {
						varList.add(content);
					}
				}
				temp = "";
			} else {
				temp += formulaCharArr[i];
			}
		}*/
        String pa[] = new String(formulaCharArr).split(" ");
        for(String temp : pa){
            if(temp != null && temp != "" && !temp.isEmpty()){
                boolean okGo = true;
                if(temp.charAt(0) >= '0' && temp.charAt(0) <= '9'){
                    okGo = false;
                }
                if (okGo) {
                    varList.add(temp);
                }
            }
        }
        System.out.println("变量列表:");
        for (int h = 0; h < varList.size(); h++) {
            String var = varList.get(h);
            System.out.println(var);
        }
    }

}

