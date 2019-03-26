package test.bmilan.bean.measurement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.timer.Timer;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyticsBeanTest
{
    public static final int BOUND_1SEC_LOW = 999000000;
    public static final int BOUND_1SEC_HIGH = 1001000000;
    public static final int BOUND_2SEC_LOW = 1999000000;
    public static final int BOUND_2SEC_HIGH = 2001000000;

    private AnalyticsBean bean;

    @BeforeEach
    void setUp()
    {
        bean = new AnalyticsBean();
        bean.setup();
    }

    @Test
    @DisplayName("bean not null")
    public void beanNotNull()
    {
        assertNotNull(bean);
    }

    @Test
    @DisplayName("measure 1 second")
    @Disabled
    public void measure1Second()
    {
        long result = 0;

        try
        {
            bean.startMeasurement();
            Thread.sleep(Timer.ONE_SECOND);
            bean.stopMeasurement();
            result = bean.getRuntimes().get(0);
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
            bean.startMeasurement();
            Thread.sleep(Timer.ONE_SECOND*2);
            bean.stopMeasurement();
            result = bean.getRuntimes().get(0);
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
        bean.startMeasurement();
        bean.stopMeasurement();
        bean.startMeasurement();
        bean.stopMeasurement();
        bean.startMeasurement();
        bean.stopMeasurement();

        List<Long> result = bean.getRuntimes();

        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("calculate - 3 measurements")
    public void calc3()
    {
        bean.getRuntimes().add(2l);
        bean.getRuntimes().add(3l);
        bean.getRuntimes().add(4l);
        bean.calculate();

        assertAll(
                () -> assertEquals(3, bean.getRuntimes().size()),
                () -> assertEquals(3d, bean.getMean()),
                () -> assertEquals(0.8, bean.getSigma(), 0.1)
        );
    }

    @Test
    @DisplayName("calculate - 5 measurements")
    public void calc5()
    {
        bean.getRuntimes().add(2l);
        bean.getRuntimes().add(3l);
        bean.getRuntimes().add(4l);
        bean.getRuntimes().add(5l);
        bean.getRuntimes().add(6l);
        bean.calculate();

        assertAll(
                () -> assertEquals(5, bean.getRuntimes().size()),
                () -> assertEquals(4d, bean.getMean()),
                () -> assertEquals(1.4, bean.getSigma(),0.1)
        );
    }

    @Test
    @DisplayName("reset")
    public void reset()
    {
        bean.startMeasurement();
        bean.stopMeasurement();
        bean.startMeasurement();
        bean.stopMeasurement();
        bean.calculate();
        bean.reset();

        assertAll(
                () -> assertTrue(bean.getRuntimes().isEmpty()),
                () -> assertEquals(0, bean.getMean()),
                () -> assertEquals(0, bean.getSigma())
        );
    }


}
