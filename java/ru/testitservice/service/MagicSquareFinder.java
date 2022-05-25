package ru.testitservice.service;

import org.springframework.stereotype.Component;
import ru.testitservice.tasktype.TaskType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class MagicSquareFinder implements TaskResolveSemiService{
    TaskType taskType;
    public MagicSquareFinder(){
        taskType=TaskType.MagicSquare;
    }
    @Override
    public String processing(String userEnter) {
        userEnter =userEnter.replaceAll(" ","");
        List<Integer> numberList=new ArrayList<>();
        for (Character c:userEnter.toCharArray()) {
            numberList.add(Integer.parseInt(c.toString()));
        }
        List<Integer> perebList = Stream.of(0,0,0,0,0,0,0,0,0).collect(Collectors.toList());
        List<Integer> ansList = Stream.of(1,2,3,4,5,6,7,8,9).collect(Collectors.toList());
        Integer result = 45;
        for(int i=1111;i<10000;i++){

            perebList.set(0,i/1000);
            perebList.set(4,i/100%10);
            perebList.set(8,i/10%10);
            perebList.set(2,i%10);

            if(15-perebList.get(0)-perebList.get(2)<10)
                perebList.set(1,15-perebList.get(0)-perebList.get(2));
            else continue;

            if(15-perebList.get(2)-perebList.get(8)<10)
                perebList.set(5,15-perebList.get(2)-perebList.get(8));
            else continue;

            if(15-perebList.get(4)-perebList.get(5)<10)
                perebList.set(3,15-perebList.get(4)-perebList.get(5));
            else continue;

            if(15-perebList.get(1)-perebList.get(4)<10)
                perebList.set(7,15-perebList.get(1)-perebList.get(4));
            else continue;

            if(15-perebList.get(7)-perebList.get(8)<10)
                perebList.set(6,15-perebList.get(7)-perebList.get(8));
            else continue;

            if(perebList.get(6)<10 && perebList.get(3)<10){

            }else continue;

            if(perebList.stream().collect(Collectors.toSet()).size()==perebList.size()){

                Integer tempResult=0;
                for (int j = 0; j < 9; j++) {
                    tempResult = Math.abs(numberList.get(j)-perebList.get(j))+tempResult;
                }

                if(tempResult<result) {
                    result = tempResult;
                    ansList=perebList.stream().collect(Collectors.toList());
                }
            }
        }

        return ansList +"result ="+result;
    }
    @Override
    public String validate(String userString) {
        userString= userString.replaceAll(" ","");

        try {
            Long.parseLong(userString);
        }catch (NumberFormatException e){
            return "In this task you can set only numbers";
        }

        if(userString.length()!=9)
            return "In this task you can set only 9 symbols";

        Set set = new HashSet();
        for (int i = 0; i < userString.length(); i++) {
            set.add(userString.charAt(i));
        }
        if(set.size()!=userString.length())
            return "Symbols must be without duplicates";
        return null;
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

}

