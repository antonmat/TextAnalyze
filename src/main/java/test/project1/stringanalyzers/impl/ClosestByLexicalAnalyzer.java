package test.project1.stringanalyzers.impl;

import test.project1.Constants.AnalyzerType;
import test.project1.stringanalyzers.IDataAnalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.abs;

public class ClosestByLexicalAnalyzer implements IDataAnalyzer<String> {

    private TreeSet<String> stringTree = new TreeSet();

    private Collection<String> CompareStringsLexically(String text, String biggerText, String smallerText) {
        ArrayList<String> stringToReturn = new ArrayList<>();

        int textComparedToBigger = text.compareTo(biggerText);
        int textComparedToSmaller = text.compareTo(smallerText);

        if (abs(textComparedToBigger) < abs(textComparedToSmaller)) {
            stringToReturn.add(biggerText);
        } else if (abs(textComparedToBigger) > abs(textComparedToSmaller)) {
            stringToReturn.add(smallerText);
        } else {
            stringToReturn.add(biggerText);
            stringToReturn.add(smallerText);
        }
        return stringToReturn;
    }
    @Override
    public AnalyzerType getType() {
        return AnalyzerType.lexical;
    }
    @Override
    public Collection<String> getClosest(String text){
        System.out.println("getting closest lexical value for : " + text);
        Collection<String> stringToReturn = new ArrayList<>();
        String biggerText = stringTree.ceiling(text);
        String smallerText = stringTree.floor(text);

        if (biggerText!=null && smallerText!=null && smallerText!=biggerText) {
            stringToReturn = CompareStringsLexically(text,biggerText,smallerText);
        }
        else if (biggerText != null) {
            stringToReturn.add(biggerText);
        }
        else if (smallerText != null) {
            stringToReturn.add(smallerText);
        }
        else{
            stringToReturn = null;
        }

        System.out.println("Closest lexical value for " + text + " is " + stringToReturn);
        return stringToReturn;

    }
    @Override
    public void updateData(String text){
        stringTree.add(text);
    }



}
