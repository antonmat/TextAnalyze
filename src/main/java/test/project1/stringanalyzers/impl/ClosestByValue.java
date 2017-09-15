package test.project1.stringanalyzers.impl;

import test.project1.Constants.AnalyzerType;
import test.project1.stringanalyzers.IDataAnalyzer;

import java.util.*;

import static java.lang.StrictMath.abs;

public class ClosestByValue implements IDataAnalyzer<String> {
    private TreeMap<Integer,Set<String>> stringMap = new TreeMap<>();
    final String StringAnalyzerName = "Value";

    private Set<String> CompareStringsByValues(Integer sumOfText, Integer biggerValue, Integer smallerValue) {
        Set<String> stringToReturn;

        int ValueComparedToBigger = biggerValue-sumOfText;
        int ValueComparedToSmaller = sumOfText -smallerValue;

        if (abs(ValueComparedToBigger) < abs(ValueComparedToSmaller)) {
            stringToReturn = stringMap.get(biggerValue);
        } else if (abs(ValueComparedToBigger) > abs(ValueComparedToSmaller)) {
            stringToReturn = stringMap.get(smallerValue);
        } else {
            stringToReturn = stringMap.get(biggerValue);
            stringToReturn.addAll(stringMap.get(smallerValue));
        }
        return stringToReturn;
    }
    private Integer calculateStringValue(String text)
    {
        System.out.println(text.toLowerCase().chars().sum());
        return text.toLowerCase().chars().map(ch->ch+1 -'a').sum();
    }
    public AnalyzerType getType() {
        return AnalyzerType.value;
    }

    @Override
    public Collection<String> getClosest(String text) {
        System.out.println("getting closest by value for : " + text);

        Set<String> stringToReturn;
        Integer sumOfText = calculateStringValue(text);
        Integer smallerValue = stringMap.floorKey(sumOfText);
        Integer biggerValue = stringMap.ceilingKey(sumOfText);

        if (biggerValue!=null && smallerValue!=null && smallerValue!=biggerValue) {
            stringToReturn = CompareStringsByValues(sumOfText,biggerValue,smallerValue);
        }
        else if (biggerValue != null) {
            stringToReturn = stringMap.get(biggerValue);
        }
        else if (smallerValue != null) {
            stringToReturn = stringMap.get(smallerValue);
        }
        else{
            stringToReturn = null;
        }

        System.out.println("Closest by value for " + text + " is " + stringToReturn);
        return stringToReturn;

    }
    @Override
    public void updateData(String text) {
        Integer sumOfText = calculateStringValue(text);
        Set<String> stringsPerKey;
        stringsPerKey =stringMap.get(sumOfText);
        if (stringsPerKey == null)
        {
            stringsPerKey = new HashSet<>();
            stringsPerKey.add(text);
            stringMap.put(sumOfText,stringsPerKey);
        }
        else{
            stringsPerKey.add(text);
        }
    }
}


