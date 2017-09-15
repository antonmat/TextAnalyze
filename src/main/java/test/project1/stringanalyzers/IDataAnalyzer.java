package test.project1.stringanalyzers;
import test.project1.Constants.AnalyzerType;

import java.util.Collection;

/*
Interface for all implementations responsible for retrieving the closest data to the provided data entry according to a certain defined logic
 */
public interface IDataAnalyzer<T> {
    Collection<T> getClosest(T data);
    void updateData(T data);
    AnalyzerType getType();

}
