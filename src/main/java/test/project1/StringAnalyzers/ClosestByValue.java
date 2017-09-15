package test.project1.StringAnalyzers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import static java.lang.StrictMath.abs;

public class ClosestByValue implements IStringAnalayzer {
    private TreeMap<Integer,Collection<String>> stringMap = new TreeMap<>();
    final String StringAnalyzerName = "Value";

    private Collection<String> CompareStringsByValues(Integer sumOfText, Integer biggerValue, Integer smallerValue) {
        Collection<String> stringToReturn = new ArrayList<>();

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
    public String GetName() {
        return StringAnalyzerName;
    }
    public Collection<String> GetClosest(String text) {
        Collection<String> stringToReturn = new ArrayList<>() ;
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
        return stringToReturn;

    }

    public void UpdateData(String text) {
        Integer sumOfText = calculateStringValue(text);
        Collection<String> stringsPerKey;
        stringsPerKey =stringMap.get(sumOfText);
        if (stringsPerKey == null)
        {
            stringsPerKey = new ArrayList<>();
            stringsPerKey.add(text);
            stringMap.put(sumOfText,stringsPerKey);
        }
        else{
            stringsPerKey.add(text);
        }
    }
}


