package test.bmilan.bean.measurement;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AnalyticsBean
{
    private long startTime;
    private long stopTime;

    private List<Long> runtimes;

    private double mean;
    private double sigma;

    @PostConstruct
    public void setup()
    {
        runtimes = new ArrayList<>();
    }

    public void startMeasurement()
    {
        startTime = System.nanoTime();
    }

    public void stopMeasurement()
    {
        stopTime = System.nanoTime();
        runtimes.add(stopTime - startTime);
    }

    public List<Long> getRuntimes()
    {
        return runtimes;
    }

    public void calculate()
    {
        calculateMean();
        calculateSigma();
    }

    private void calculateMean()
    {
        if (runtimes.isEmpty()) {
            mean = 0d;
        }

        BigDecimal akku = runtimes
                .stream()
                .map(l -> BigDecimal.valueOf(l))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(runtimes.size()), 200, RoundingMode.HALF_UP);

        mean = akku.doubleValue();
    }

    private void calculateSigma()
    {
        if (runtimes.isEmpty()) {
            sigma = 0d;
        }

        BigDecimal akku = runtimes
                .stream()
                .map(l -> BigDecimal.valueOf(l))
                .map(B -> B.subtract(BigDecimal.valueOf(mean)))
                .map(B -> B.multiply(B))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add)
                .divide(BigDecimal.valueOf(runtimes.size()), 200, RoundingMode.HALF_UP)
                .sqrt(MathContext.DECIMAL64);

        sigma = akku.doubleValue();
    }


    public double getMean()
    {
        return mean;
    }

    public double getSigma()
    {
        return sigma;
    }

    public void reset()
    {
        runtimes = new ArrayList<>();
        startTime = 0l;
        stopTime = 0l;
        mean = 0d;
        sigma = 0d;
    }
}
