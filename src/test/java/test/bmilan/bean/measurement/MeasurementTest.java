package test.bmilan.bean.measurement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.bmilan.bean.worker.IterationWorkerBean;
import test.bmilan.bean.worker.RandomWorkerBean;

import static org.junit.jupiter.api.Assertions.*;

public class MeasurementTest
{

    public static final long EXAMPLE_SEED = 133246574984l;
    public static final int EXPECTED_INTEGER = 1146240010;
    private static final int EXPECTED_INTEGER2 = -539032706;
    private static final int MIN = 5;
    private static final int MAX = 10;

    private IterationWorkerBean iterationWorker;
    private RandomWorkerBean randomWorker;
    private AnalyticsBean analyticsBean;
    private SimpleMeasurement simple;
    private RandomMeasurement random;
    private MeasurementFactory factory;


    @BeforeEach
    void setUp()
    {
        iterationWorker = new IterationWorkerBean();
        iterationWorker.setup();
        randomWorker = new RandomWorkerBean();
        randomWorker.setup();
        prepareRandomForTest();
        analyticsBean = new AnalyticsBean();
        analyticsBean.setup();

        simple = new SimpleMeasurement(iterationWorker, analyticsBean);
        simple.setup();

        random = new RandomMeasurement(iterationWorker, randomWorker, analyticsBean);
        random.setup();

        factory = new MeasurementFactory(simple, random);
    }

    private void prepareRandomForTest()
    {
        randomWorker.setBounded(true);
        randomWorker.setMin(MIN);
        randomWorker.setMax(MAX);
        randomWorker.setSeed(EXAMPLE_SEED);
        randomWorker.restart();
    }

    @AfterEach
    void clean()
    {
    }

    @Test
    @DisplayName("factory not null")
    public void notNull()
    {
        assertNotNull(factory);
    }

    @Test
    @DisplayName("create simple measurement - not null")
    public void createSimpleMeasurement()
    {
        Measurement result = factory.createSimpleMeasurement();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(simple, result)
        );
    }

    @Test
    @DisplayName("create simple measurement - run primes default")
    public void createSimpleMeasurementRunPrimes()
    {
        Measurement simple = factory.createSimpleMeasurement();
        simple.runPrimes();

        assertAll(
                () -> assertEquals(1, simple.getMeasurements().size()),
                () -> assertEquals(100, simple.getMeasurements().get(0).getData().size())
        );
    }

    @Test
    @DisplayName("create simple measurement - run primes x99")
    public void createSimpleMeasurement99()
    {
        Measurement simple = factory.createSimpleMeasurement(99, 100,100,100);
        simple.runPrimes();

        assertAll(
                () -> assertEquals(1, simple.getMeasurements().size()),
                () -> assertEquals(99, simple.getMeasurements().get(0).getData().size())
        );
    }

    @Test
    @DisplayName("create simple measurement - run all x10")
    public void createSimpleMeasurement10()
    {
        Measurement simple = factory.createSimpleMeasurement(10, 100, 101, 102);
        simple.runPrimes();
        simple.runFibonacci();
        simple.runFactorial();

        assertAll(
                () -> assertEquals(3, simple.getMeasurements().size()),
                () -> assertEquals(10, simple.getMeasurements().get(0).getData().size()),
                () -> assertEquals(10, simple.getMeasurements().get(1).getData().size()),
                () -> assertEquals(10, simple.getMeasurements().get(2).getData().size())
        );
    }

    @Test
    @DisplayName("create random measurement - not null")
    public void createRandomMeasurement()
    {
        Measurement result = factory.createRandomMeasurement();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(random, result)
        );
    }

    @Test
    @DisplayName("create random measurement - run primes default")
    public void createRandomMeasurementDef()
    {
        Measurement random = factory.createRandomMeasurement();
        random.runPrimes();

        assertAll(
                () -> assertEquals(1, random.getMeasurements().size()),
                () -> assertEquals(100, random.getMeasurements().get(0).getData().size())
        );
    }

    @Test
    @DisplayName("create random measurement - run primes x99")
    public void createRandomMeasurement99()
    {
        Measurement random = factory.createRandomMeasurement(99);
        random.runPrimes();

        assertAll(
                () -> assertEquals(1, random.getMeasurements().size()),
                () -> assertEquals(99, random.getMeasurements().get(0).getData().size())
        );
    }

    @Test
    @DisplayName("create random measurement - run all x10")
    public void createRandomMeasurement10()
    {
        Measurement random = factory.createRandomMeasurement(10);
        random.runPrimes();
        random.runFibonacci();
        random.runFactorial();

        assertAll(
                () -> assertEquals(3, random.getMeasurements().size()),
                () -> assertEquals(10, random.getMeasurements().get(0).getData().size()),
                () -> assertEquals(10, random.getMeasurements().get(1).getData().size()),
                () -> assertEquals(10, random.getMeasurements().get(2).getData().size())
        );
    }

    @Test
    @DisplayName("create random measurement - modifyRandom")
    public void modifyRandom()
    {
        RandomMeasurement random = (RandomMeasurement) factory.createRandomMeasurement();
        random.modifyRandom(EXAMPLE_SEED, MIN, MAX, true);

        RandomWorkerBean result = random.getRandomWorkerBean();

        assertAll(
                () -> assertEquals(EXAMPLE_SEED, result.getSeed()),
                () -> assertEquals(MIN, result.getMin()),
                () -> assertEquals(MAX, result.getMax()),
                () -> assertTrue(result.isBounded())
        );

    }

}
