package test.bmilan.bean.measurement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyticsSeriesTest
{
    private static final String DEFAULT_NAME = "Power Series X7";
    private static final List<Integer> DEFAULT_INT_LIST = Arrays.asList(new Integer[]{1, 2, 3});
    private static final List<Double> DEFAULT_DOUBLE_LIST = Arrays.asList(new Double[]{1.5, 2.5, 3.5});
    private static final double DEFAULT_MEAN = 0.5;
    private static final double DEFAULT_SIGMA = 0.1;

    private AnalyticsSeries series;


    @BeforeEach
    void setUp()
    {
        series = new AnalyticsSeries();
    }

    @AfterEach
    void clean()
    {
    }

    @Test
    @DisplayName("bean not null")
    public void beanNotNull()
    {
        assertNotNull(series);
    }

    @Test
    @DisplayName("parametrised constructor - integer")
    public void param()
    {
        series = new AnalyticsSeries(DEFAULT_NAME, DEFAULT_INT_LIST, DEFAULT_MEAN, DEFAULT_SIGMA);

        assertAll(
                () -> assertEquals(DEFAULT_NAME, series.getName()),
                () -> assertEquals(DEFAULT_INT_LIST, series.getData()),
                () -> assertEquals(DEFAULT_MEAN, series.getMean()),
                () -> assertEquals(DEFAULT_SIGMA, series.getSigma())
        );
    }

    @Test
    @DisplayName("parametrised constructor - double")
    public void param2()
    {
        series = new AnalyticsSeries(DEFAULT_NAME, DEFAULT_DOUBLE_LIST, DEFAULT_MEAN, DEFAULT_SIGMA);

        assertAll(
                () -> assertEquals(DEFAULT_NAME, series.getName()),
                () -> assertEquals(DEFAULT_DOUBLE_LIST, series.getData()),
                () -> assertEquals(DEFAULT_MEAN, series.getMean()),
                () -> assertEquals(DEFAULT_SIGMA, series.getSigma())
        );
    }

}