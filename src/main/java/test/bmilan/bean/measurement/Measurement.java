package test.bmilan.bean.measurement;

import java.util.List;

public interface Measurement
{
    void runPrimes();
    void runFibonacci();
    void runFactorial();
    List<AnalyticsSeries> getMeasurements();
}
