package ru.testitservice.service;

import java.util.List;

public class ValidHelp {
    public static boolean check(List<String> wordList1) {
        for (int i = 0; i< wordList1.size(); i++) {

            if(i==0){

                if(!haveOnlyOne(wordList1.get(i),""))
                    return false;

            }else if(i== wordList1.size()-1){

                if(!haveOnlyOne(wordList1.get(i),"]"))
                    return false;

            }else if(i%2==0){


                if(!haveOnlyOne(wordList1.get(i),","))
                    return false;

            }

        }
        return true;
    }

    public static boolean haveOnlyOne(String s,String one) {

        s=s.replaceAll(" ","");

        if(s.equals(one)) return
                true;

        else return
                false;

    }
}
