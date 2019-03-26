package test.bmilan.bean.worker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomWorkerBeanTest
{
    public static final long EXAMPLE_SEED = 133246574984l;
    public static final int EXPECTED_INTEGER = 1146240010;
    private static final int EXPECTED_INTEGER2 = -539032706;
    private static final int MIN = 5;
    private static final int MAX = 10;

    private RandomWorkerBean bean;

    @BeforeEach
    void setUp()
    {
        bean = new RandomWorkerBean();
        bean.setup();
    }

    @Test
    @DisplayName("bean not null")
    public void beanNotNull()
    {
        assertNotNull(bean);
    }

    @Test
    @DisplayName("next random integer")
    public void nextRandInt()
    {
        assertNotNull(bean.nextRandomInteger());
    }

    @Test
    @DisplayName("restart")
    public void restart()
    {
        bean.nextRandomInteger();
        bean.nextRandomInteger();
        bean.nextRandomInteger();

        bean.setSeed(EXAMPLE_SEED);
        bean.restart();

        Integer result = bean.nextRandomInteger();

        assertEquals(EXPECTED_INTEGER, result);
    }

    @Test
    @DisplayName("random integer list")
    public void randIntegerList()
    {
        bean.setSeed(EXAMPLE_SEED);
        bean.restart();

        List<Integer> result = bean.randomIntegerList(100);

        assertAll(
                () -> assertEquals(100, result.size()),
                () -> assertEquals(EXPECTED_INTEGER, result.get(0)),
                () -> assertEquals(EXPECTED_INTEGER2, result.get(99))
        );

    }

    @Test
    @DisplayName("random integer list - bounded unset")
    public void boundedUnset()
    {
        bean.setBounded(false);
        bean.setSeed(EXAMPLE_SEED);
        bean.restart();

        List<Integer> result = bean.randomIntegerList(100);

        assertAll(
                () -> assertEquals(EXPECTED_INTEGER, result.get(0)),
                () -> assertEquals(EXPECTED_INTEGER2, result.get(99))
        );
    }

    @Test
    @DisplayName("random integer list - bounded default")
    public void boundedDefault()
    {
        bean.setBounded(true);
        bean.setSeed(EXAMPLE_SEED);
        bean.restart();

        List<Integer> result = bean.randomIntegerList(100);

        result.stream().forEach(i -> assertTrue(i >= bean.getMin() && i <= bean.getMax()));
    }

    @Test
    @DisplayName("random integer list - bounded min max")
    public void bounded()
    {
        bean.setBounded(true);
        bean.setMin(MIN);
        bean.setMax(MAX);
        bean.setSeed(EXAMPLE_SEED);
        bean.restart();

        List<Integer> result = bean.randomIntegerList(100);

        result.stream().forEach(i -> assertTrue(i >= MIN && i <= MAX));
    }

    @Test
    @DisplayName("get seed")
    public void getSeed()
    {
        bean.setSeed(EXAMPLE_SEED);
        bean.restart();

        assertEquals(EXAMPLE_SEED, bean.getSeed());
    }

}
