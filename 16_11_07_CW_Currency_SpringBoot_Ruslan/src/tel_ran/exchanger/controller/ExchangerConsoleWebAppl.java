package tel_ran.exchanger.controller;

import tel_ran.exchanger.web.CurrencyWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * Created by Ruslan on 03/11/16.
 */
public class ExchangerConsoleWebAppl {
    private static final String SPLITTER = " ";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line=null;
        CurrencyWeb currencyWeb = new CurrencyWeb();
        while (true){
            displayMessage("Input operation: 1(cunrrencies) 2(cunrrency) 3(convert)  4 - exit");
            try {
                line= reader.readLine();
            } catch (IOException e) {
                displayMessage(e.getMessage());
            }
            if (line==null) {
                displayMessage("Input operation 1) cunrrencies 2) cunrrency 3) convert");
                continue;
            }

            if (line.contains("exit")) break;
            String[] operations = line.split(SPLITTER);
            if (operations.length==0 ) continue;

            switch (operations[0]) {
                case "1":
                    try {
                        Method method = currencyWeb.getClass().getDeclaredMethod("get_Cunrrency");
                        method.setAccessible(true);
                        method.invoke(currencyWeb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    break;
                case "2":
                    try {
                        Method method = currencyWeb.getClass().getDeclaredMethod("get_Cunrrency",String[].class);
                        method.setAccessible(true);
                        method.invoke(currencyWeb, operations);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        Method method = currencyWeb.getClass().getDeclaredMethod("get_Convert",String.class);
                        method.setAccessible(true);
                        method.invoke(currencyWeb, operations);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }

    }
    public static void displayMessage(String str){
        System.out.println(str);
    }


}

