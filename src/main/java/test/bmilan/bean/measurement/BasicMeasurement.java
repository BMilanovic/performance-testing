package test.bmilan.bean.measurement;

import java.util.List;

public abstract class BasicMeasurement implements Measurement
{
    private int repeat = 100;

    private int numPrimes = 100;
    private int numFibonacci = 20;
    private int numFactorial = 4;

    List<AnalyticsSeries> measurements;
    AnalyticsRun analyticsRun = new AnalyticsRun();

    public int getRepeat()
    {
        return repeat;
    }

    public void setRepeat(int repeat)
    {
        this.repeat = repeat;
    }

    public int getNumPrimes()
    {
        return numPrimes;
    }

    public void setNumPrimes(int numPrimes)
    {
        this.numPrimes = numPrimes;
    }

    public int getNumFibonacci()
    {
        return numFibonacci;
    }

    public void setNumFibonacci(int numFibonacci)
    {
        this.numFibonacci = numFibonacci;
    }

    public int getNumFactorial()
    {
        return numFactorial;
    }

    public void setNumFactorial(int numFactorial)
    {
        this.numFactorial = numFactorial;
    }

    public List<AnalyticsSeries> getMeasurements()
    {
        return measurements;
    }

    void addNewSeries(String name)
    {
        analyticsRun.calculate();
        measurements.add( new AnalyticsSeries<>(
                                name,
                                analyticsRun.getRuntimes(),
                                analyticsRun.getMean(),
                                analyticsRun.getSigma()
                            )
        );
    }

}
