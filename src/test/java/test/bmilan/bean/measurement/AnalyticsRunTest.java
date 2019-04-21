package test.bmilan.bean.measurement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.timer.Timer;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyticsRunTest
{
    public static final int BOUND_1SEC_LOW = 999000000;
    public static final int BOUND_1SEC_HIGH = 1001000000;
    public static final int BOUND_2SEC_LOW = 1999000000;
    public static final int BOUND_2SEC_HIGH = 2001000000;

    private AnalyticsRun analyticsRun;

    @BeforeEach
    void setUp()
    {
        analyticsRun = new AnalyticsRun();
    }

    @Test
    @DisplayName("empty constructor")
    public void beanNotNull()
    {
        assertNotNull(analyticsRun);
    }

    @Test
    @DisplayName("measure 1 second")
    @Disabled
    public void measure1Second()
    {
        long result = 0;

        try
        {
            analyticsRun.startMeasurement();
            Thread.sleep(Timer.ONE_SECOND);
            analyticsRun.stopMeasurement();
            result = analyticsRun.getRuntimes().get(0);
        }
        catch (InterruptedException e)
        {
            fail(e);
        }

        assertTrue(BOUND_1SEC_LOW < result && result < BOUND_1SEC_HIGH);
    }

    @Test
    @DisplayName("measure 2 seconds")
    @Disabled
    public void measure2Seconds()
    {
        long result = 0;

        try
        {
            analyticsRun.startMeasurement();
            Thread.sleep(Timer.ONE_SECOND*2);
            analyticsRun.stopMeasurement();
            result = analyticsRun.getRuntimes().get(0);
        }
        catch (InterruptedException e)
        {
            fail(e);
        }

        assertTrue(BOUND_2SEC_LOW < result && result < BOUND_2SEC_HIGH);
    }

    @Test
    @DisplayName("perform 3 measurements")
    public void Measure3Times()
    {
        analyticsRun.startMeasurement();
        analyticsRun.stopMeasurement();
        analyticsRun.startMeasurement();
        analyticsRun.stopMeasurement();
        analyticsRun.startMeasurement();
        analyticsRun.stopMeasurement();

        List<Long> result = analyticsRun.getRuntimes();

        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("calculate - 3 measurements")
    public void calc3()
    {
        analyticsRun.getRuntimes().add(2l);
        analyticsRun.getRuntimes().add(3l);
        analyticsRun.getRuntimes().add(4l);
        analyticsRun.calculate();

        assertAll(
                () -> assertEquals(3, analyticsRun.getRuntimes().size()),
                () -> assertEquals(3d, analyticsRun.getMean()),
                () -> assertEquals(0.8, analyticsRun.getSigma(), 0.1)
        );
    }

    @Test
    @DisplayName("calculate - 5 measurements")
    public void calc5()
    {
        analyticsRun.getRuntimes().add(2l);
        analyticsRun.getRuntimes().add(3l);
        analyticsRun.getRuntimes().add(4l);
        analyticsRun.getRuntimes().add(5l);
        analyticsRun.getRuntimes().add(6l);
        analyticsRun.calculate();

        assertAll(
                () -> assertEquals(5, analyticsRun.getRuntimes().size()),
                () -> assertEquals(4d, analyticsRun.getMean()),
                () -> assertEquals(1.4, analyticsRun.getSigma(),0.1)
        );
    }


}
