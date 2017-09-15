package test.project1.StringAnalyzers;

import java.util.Collection;

public interface IStringAnalayzer {
    Collection<String> GetClosest(String text);
    void UpdateData(String text);
    String GetName();

}
