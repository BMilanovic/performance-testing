package test.bmilan.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.bmilan.bean.measurement.AnalyticsSeries;
import test.bmilan.bean.measurement.MeasurementFactory;
import test.bmilan.bean.measurement.RandomMeasurement;
import test.bmilan.bean.measurement.SimpleMeasurement;
import test.bmilan.bean.worker.RandomWorkerBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RestResourceTest
{
    private static final String NAME1 = "Simple 1";
    private static final double MEAN1 = 12.5d;
    private static final double SIGMA1 = 1.2d;
    private static final String NAME2 = "Random 2";
    private static final double MEAN2 = 7.77d;
    private static final double SIGMA2 = 123.123d;
    private static final int REPEAT = 12;
    private static final int NUM_PRIMES = 10;
    private static final int NUM_FIBONACCI = 11;
    private static final int NUM_FACTORIAL = 4;
    private static final long EXAMPLE_SEED = 12456l;
    private static final int MIN_PRIMES = 9;
    private static final int MAX_PRIMES = 11;
    private static final int MIN_FIBONACCI = 9;
    private static final int MAX_FIBONACCI = 11;
    private static final int MIN_FACTORIAL = 3;
    private static final int MAX_FACTORIAL = 4;

    @Mock
    MeasurementFactory factory;
    @Mock
    SimpleMeasurement simple;
    @Mock
    RandomMeasurement random;
    @Mock
    RandomWorkerBean randomWorker;

    List<AnalyticsSeries> resultSimple;
    List<AnalyticsSeries> resultRandom;

    private RestResource bean;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);

        bean = new RestResource(factory);
        createResultSimple();
        createResultRandom();

        when(factory.createSimpleMeasurement(anyInt(),anyInt(),anyInt(),anyInt())).thenReturn(simple);
        when(factory.createRandomMeasurement(anyInt())).thenReturn(random);
        when(simple.getMeasurements()).thenReturn(resultSimple);
        when(random.getMeasurements()).thenReturn(resultRandom);
    }

    @AfterEach
    void clean()
    {
    }

    @Test
    @DisplayName("run simple")
    public void runSimple()
    {
        List<AnalyticsSeries> result = bean.runSimple(REPEAT, NUM_PRIMES, NUM_FIBONACCI, NUM_FACTORIAL);

        assertAll(
                () -> assertEquals(resultSimple, result),
                () -> verify(simple).runPrimes(),
                () -> verify(simple).runFibonacci(),
                () -> verify(simple).runFactorial()
        );
    }

    @Test
    @DisplayName("run random")
    public void runRandomDef()
    {
        List<AnalyticsSeries> result = bean.runRandom(EXAMPLE_SEED,
                REPEAT,
                MIN_PRIMES,
                MAX_PRIMES,
                MIN_FIBONACCI,
                MAX_FIBONACCI,
                MIN_FACTORIAL,
                MAX_FACTORIAL);

        assertAll(
                () -> assertEquals(resultRandom, result),
                () -> verify(random, times(3)).modifyRandom(eq(EXAMPLE_SEED),
                        anyInt(),
                        anyInt(),
                        eq(true))
        );

    }

    private void createResultSimple()
    {
        resultSimple = new ArrayList<>();
        resultSimple.add(new AnalyticsSeries(NAME1, Collections.emptyList(), MEAN1, SIGMA1));
    }

    private void createResultRandom()
    {
        resultRandom = new ArrayList<>();
        resultRandom.add(new AnalyticsSeries(NAME2, Collections.emptyList(), MEAN2, SIGMA2));
    }

}