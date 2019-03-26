package test.bmilan.bean.measurement;

import test.bmilan.bean.worker.IterationWorkerBean;
import test.bmilan.bean.worker.RandomWorkerBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class RandomMeasurement extends BasicMeasurement
{
    @EJB
    private IterationWorkerBean iterationWorkerBean;

    @EJB
    private RandomWorkerBean randomWorkerBean;

    public RandomMeasurement()
    {
    }

    protected RandomMeasurement(IterationWorkerBean iterationWorkerBean, RandomWorkerBean randomWorkerBean, AnalyticsBean analyticsBean)
    {
        this.iterationWorkerBean = iterationWorkerBean;
        this.randomWorkerBean = randomWorkerBean;
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
            iterationWorkerBean.reset();
            analyticsBean.startMeasurement();
            iterationWorkerBean.generatePrimes(randomWorkerBean.nextRandomInteger());
            analyticsBean.stopMeasurement();
        }

        addNewSeries("Generate primes " + getRandomText());
    }

    @Override
    public void runFibonacci()
    {
        for (int i = 0; i < getRepeat(); i++) {
            iterationWorkerBean.reset();
            analyticsBean.startMeasurement();
            iterationWorkerBean.generateFibonacci(randomWorkerBean.nextRandomInteger());
            analyticsBean.stopMeasurement();
        }

        addNewSeries("Generate fibonacci " + getRandomText());
    }

    @Override
    public void runFactorial()
    {
        for (int i = 0; i < getRepeat(); i++) {
            analyticsBean.startMeasurement();
            iterationWorkerBean.getFactorial(randomWorkerBean.nextRandomInteger());
            analyticsBean.stopMeasurement();
        }

        addNewSeries("Get factorial " + getRandomText());
    }

    private String getRandomText()
    {
        return "randomly between "
                + randomWorkerBean.getMin()
                + " and "
                + randomWorkerBean.getMax()
                + " (seed "
                + randomWorkerBean.getSeed()
                + ")";
    }

    public void modifyRandom(long seed, int min, int max, boolean bounded)
    {
        randomWorkerBean.setSeed(seed);
        randomWorkerBean.setMinMax(min, max);
        randomWorkerBean.setBounded(bounded);
        randomWorkerBean.restart();
    }

    protected RandomWorkerBean getRandomWorkerBean()
    {
        return randomWorkerBean;
    }
}
