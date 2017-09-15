package test.project1.stringanalyzers;

import test.project1.Constants.AnalyzerType;
import test.project1.stringanalyzers.impl.ClosestByLexicalAnalyzer;
import test.project1.stringanalyzers.impl.ClosestByValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StringAnalyzersFactory {
    private Map<AnalyzerType,IDataAnalyzer<String>> stringAnalayzersMap;

    public StringAnalyzersFactory() {
        stringAnalayzersMap = new HashMap<>();
        stringAnalayzersMap.put(AnalyzerType.lexical, new ClosestByLexicalAnalyzer());
        stringAnalayzersMap.put(AnalyzerType.value, new ClosestByValue());
    }

    public Collection<IDataAnalyzer<String>> getAllAnalyzers(){
        return stringAnalayzersMap.values();
    }

    public IDataAnalyzer getStringAnalyzer(AnalyzerType type){
        return stringAnalayzersMap.get(type);
    }
}
