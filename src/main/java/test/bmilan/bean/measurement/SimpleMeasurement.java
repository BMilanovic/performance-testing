package test.bmilan.bean.measurement;

import test.bmilan.bean.worker.IterationWorkerBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class SimpleMeasurement extends BasicMeasurement
{
    @EJB
    private IterationWorkerBean worker;

    public SimpleMeasurement()
    {
    }

    protected SimpleMeasurement(IterationWorkerBean worker)
    {
        this.worker = worker;
    }

    @PostConstruct
    public void setup()
    {
        measurements = new ArrayList<>();
    }

    @Override
    public void runPrimes()
    {
        analyticsRun = new AnalyticsRun();
        for (int i = 0; i < getRepeat(); i++) {
            worker.reset();
            analyticsRun.startMeasurement();
            worker.generatePrimes(getNumPrimes());
            analyticsRun.stopMeasurement();
        }

        addNewSeries("Generate primes, up to " + getNumPrimes());
    }

    @Override
    public void runFibonacci()
    {
        analyticsRun = new AnalyticsRun();
        for (int i = 0; i < getRepeat(); i++) {
            worker.reset();
            analyticsRun.startMeasurement();
            worker.generateFibonacci(getNumFibonacci());
            analyticsRun.stopMeasurement();
        }

        addNewSeries("Generate fibonacci, up to " + getNumFibonacci());
    }

    @Override
    public void runFactorial()
    {
        analyticsRun = new AnalyticsRun();
        for (int i = 0; i < getRepeat(); i++) {
            analyticsRun.startMeasurement();
            worker.getFactorial(getNumFactorial());
            analyticsRun.stopMeasurement();
        }

        addNewSeries("Get factorial of " + getNumFactorial());
    }

}
