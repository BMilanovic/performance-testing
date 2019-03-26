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

    protected SimpleMeasurement(IterationWorkerBean worker, AnalyticsBean analyticsBean)
    {
        this.worker = worker;
        super.analyticsBean = analyticsBean;
    }

    @PostConstruct
    public void setup()
    {
        measurements = new ArrayList<>();
    }

    @Override
    public void runPrimes()
    {
        for (int i = 0; i < getRepeat(); i++) {
            worker.reset();
            analyticsBean.startMeasurement();
            worker.generatePrimes(getNumPrimes());
            analyticsBean.stopMeasurement();
        }

        addNewSeries("Generate primes, up to " + getNumPrimes());
    }

    @Override
    public void runFibonacci()
    {
        for (int i = 0; i < getRepeat(); i++) {
            worker.reset();
            analyticsBean.startMeasurement();
            worker.generateFibonacci(getNumFibonacci());
            analyticsBean.stopMeasurement();
        }

        addNewSeries("Generate fibonacci, up to " + getNumFibonacci());
    }

    @Override
    public void runFactorial()
    {
        for (int i = 0; i < getRepeat(); i++) {
            analyticsBean.startMeasurement();
            worker.getFactorial(getNumFactorial());
            analyticsBean.stopMeasurement();
        }

        addNewSeries("Get factorial of " + getNumFactorial());
    }

}
