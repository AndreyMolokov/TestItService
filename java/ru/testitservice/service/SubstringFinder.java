package ru.testitservice.service;

import org.springframework.stereotype.Component;
import ru.testitservice.tasktype.TaskType;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class SubstringFinder implements TaskResolveSemiService{
    TaskType taskType;
    public SubstringFinder(){
        taskType=TaskType.FindSubstrings;
    }
    @Override
    public String processing(String userEnter) {
        List<String> ansList = new ArrayList<>();
        String[] stringList = userEnter.split("\\[");
        List<String> stringList1 = Arrays.stream(stringList).collect(Collectors.toList());
        String[] wordLis1=stringList1.get(1).split("\"");
        List<String> wordListMas1 = Arrays.stream(wordLis1).collect(Collectors.toList());
        String[] wordLis2=stringList1.get(2).split("\"");
        List<String> wordListMas2 = Arrays.stream(wordLis2).collect(Collectors.toList());
        List<String> fromStringtoList1 = new ArrayList<>();
        List<String> fromStringtoList2 = new ArrayList<>();
        for (int i = 0;i<wordListMas1.size();i++) {

            if(i%2==1){

                fromStringtoList1.add(wordListMas1.get(i));

            }
        }
        for (int i = 0;i<wordListMas2.size();i++) {

            if(i%2==1){

                fromStringtoList2.add(wordListMas2.get(i));

            }
            ansList =substringFinder(fromStringtoList1,fromStringtoList2);
        }
        return ansList.toString();
    }
    public List<String> substringFinder(List<String> stringList1, List<String> stringList2){

        TreeSet<String> r = new TreeSet<>(Comparator.naturalOrder());

        for(String s1:stringList1){
            for (String s2 : stringList2) {
                if (s2.contains(s1))
                    r.add(s1);
            }
        }

        List<String> rlist = r.stream().collect(Collectors.toList());
        return rlist;
    }

    @Override
    public String validate(String userString) {
        if(userString.equals(userString))
            return "format must be [\"****\",\"****\",...,\"****\"][\"****\",\"****\",...,\"****\"]";
        String[] stringList = userString.split("\\[");
        List<String> stringList1 = Arrays.stream(stringList).collect(Collectors.toList());



        String[] wordListMas1=stringList1.get(1).split("\"");
        List<String> wordList1 = Arrays.stream(wordListMas1).collect(Collectors.toList());

        String[] wordLis2=stringList1.get(2).split("\"");
        List<String> wordListMas2 = Arrays.stream(wordLis2).collect(Collectors.toList());

        if(stringList1.size()!=3 || ValidHelp.haveOnlyOne(stringList1.get(0),"")
                || ValidHelp.check(wordListMas2) || ValidHelp.check(wordList1))
            return null;
        else
            return "format must be [\"****\",\"****\",...,\"****\"][\"****\",\"****\",...,\"****\"]";
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }
}
