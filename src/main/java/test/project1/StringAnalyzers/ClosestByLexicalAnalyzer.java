package test.project1.StringAnalyzers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.abs;

public class ClosestByLexicalAnalyzer implements IStringAnalayzer {

    private TreeSet<String> stringTree = new TreeSet();
    final String StringAnalyzerName = "Lexical";

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

    public String GetName() {
        return StringAnalyzerName;
    }

    public Collection<String> GetClosest(String text){
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
        return stringToReturn;

    }
    public Collection<String> GetClosestByValue(String text)
    {
        return null;//todo
    }
    public void UpdateData(String text)
    {
        stringTree.add(text);
    }


}
