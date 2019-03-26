package test.bmilan.bean.measurement;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MeasurementFactory
{
    @EJB
    private SimpleMeasurement simple;

    @EJB
    private RandomMeasurement random;

    public MeasurementFactory()
    {
    }

    protected MeasurementFactory(SimpleMeasurement simpleMeasurement, RandomMeasurement randomMeasurement)
    {
        this.simple = simpleMeasurement;
        this.random = randomMeasurement;
    }

    public Measurement createSimpleMeasurement()
    {
        return simple;
    }

    public Measurement createSimpleMeasurement(int repeat, int numPrimes, int numFibonacci, int numFactorial)
    {
        simple.setRepeat(repeat);
        simple.setNumPrimes(numPrimes);
        simple.setNumFibonacci(numFibonacci);
        simple.setNumFactorial(numFactorial);

        return simple;
    }

    public Measurement createRandomMeasurement()
    {
        return random;
    }

    public Measurement createRandomMeasurement(int repeat)
    {
        random.setRepeat(repeat);

        return random;
    }

}
