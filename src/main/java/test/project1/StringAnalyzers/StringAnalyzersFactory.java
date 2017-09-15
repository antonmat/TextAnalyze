package test.project1.StringAnalyzers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StringAnalyzersFactory {
    private Collection<IStringAnalayzer> stringAnalayzers = new ArrayList<>();

    public StringAnalyzersFactory() {
        stringAnalayzers.add(new ClosestByLexicalAnalyzer());
        stringAnalayzers.add(new ClosestByValue());
    }

    public Collection<IStringAnalayzer> getAnalyzers()
    {
        return stringAnalayzers;
    }
}
