package test.bmilan.bean.worker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IterationWorkerBeanTest
{

    private IterationWorkerBean bean;


    @BeforeEach
    void setUp()
    {
        bean = new IterationWorkerBean();
        bean.setup();
    }

    @Test
    @DisplayName("bean not null")
    public void beanNotNull()
    {
        assertNotNull(bean);
    }

    @Test
    @DisplayName("generate primes")
    public void genPrimes()
    {
        bean.generatePrimes(12);
    }

    @Test
    @DisplayName("generate primes - below zero")
    public void genPrimesLTZ()
    {
        assertThrows(RuntimeException.class, () -> bean.generatePrimes(-5));
    }

    @Test
    @DisplayName("generate primes - trivial 0")
    public void genPrimes0()
    {
        bean.generatePrimes(0);

        assertTrue(bean.getPrimes().isEmpty());
    }

    @Test
    @DisplayName("generate primes - trivial 1")
    public void genPrimes1()
    {
        bean.generatePrimes(1);

        assertAll(
                () -> assertEquals(1, bean.getPrimes().size()),
                () -> assertEquals(1, bean.getPrimes().get(0))
        );
    }

    @Test
    @DisplayName("generate primes - trivial 2")
    public void genPrimes2()
    {
        bean.generatePrimes(2);

        assertAll(
                () -> assertEquals(2, bean.getPrimes().size()),
                () -> assertEquals(1, bean.getPrimes().get(0)),
                () -> assertEquals(2, bean.getPrimes().get(1))
        );
    }

    @Test
    @DisplayName("generate primes - trivial 3")
    public void genPrimes3()
    {
        bean.generatePrimes(3);

        assertAll(
                () -> assertEquals(3, bean.getPrimes().size()),
                () -> assertEquals(1, bean.getPrimes().get(0)),
                () -> assertEquals(2, bean.getPrimes().get(1)),
                () -> assertEquals(3, bean.getPrimes().get(2))
        );
    }

    @Test
    @DisplayName("generate primes - 4")
    public void genPrimes4()
    {
        bean.generatePrimes(4);

        assertEquals(5, bean.getPrimes().get(3));
    }

    @Test
    @DisplayName("generate primes - 5")
    public void genPrimes5()
    {
        bean.generatePrimes(5);

        assertEquals(7, bean.getPrimes().get(4));
    }

    @Test
    @DisplayName("generate primes - 6")
    public void genPrimes6()
    {
        bean.generatePrimes(6);

        assertEquals(11, bean.getPrimes().get(5));
    }

    @Test
    @DisplayName("generate primes - 25")
    public void genPrimes25()
    {
        bean.generatePrimes(25);

        assertEquals(89, bean.getPrimes().get(24));
    }

    @Test
    @DisplayName("generate primes - 100")
    public void genPrimes100()
    {
        bean.generatePrimes(100);

        assertEquals(523, bean.getPrimes().get(99));
    }

    @Test
    @DisplayName("generate fibonacci")
    public void genFibonacci()
    {
        bean.generateFibonacci(4);
    }

    @Test
    @DisplayName("generate fibonacci - below zero")
    public void genFibonacciLTZ()
    {
        assertThrows(RuntimeException.class, () -> bean.generateFibonacci(-5));
    }

    @Test
    @DisplayName("generate fibonacci - trivial 0")
    public void genFibonacci0()
    {
        bean.generateFibonacci(0);

        assertTrue(bean.getFibonacci().isEmpty());
    }

    @Test
    @DisplayName("generate fibonacci - trivial 1")
    public void genFibonacci1()
    {
        bean.generateFibonacci(1);

        assertAll(
                () -> assertEquals(1, bean.getFibonacci().size()),
                () -> assertEquals(1, bean.getFibonacci().get(0))
        );
    }

    @Test
    @DisplayName("generate fibonacci - trivial 2")
    public void genFibonacci2()
    {
        bean.generateFibonacci(2);

        assertAll(
                () -> assertEquals(2, bean.getFibonacci().size()),
                () -> assertEquals(1, bean.getFibonacci().get(0)),
                () -> assertEquals(1, bean.getFibonacci().get(1))
        );
    }

    @Test
    @DisplayName("generate fibonacci - 3")
    public void genFibonacci3()
    {
        bean.generateFibonacci(3);

        assertEquals(2, bean.getFibonacci().get(2));
    }

    @Test
    @DisplayName("generate fibonacci - 4")
    public void genFibonacci4()
    {
        bean.generateFibonacci(4);

        assertEquals(3, bean.getFibonacci().get(3));
    }

    @Test
    @DisplayName("generate fibonacci - 5")
    public void genFibonacci5()
    {
        bean.generateFibonacci(5);

        assertEquals(5, bean.getFibonacci().get(4));
    }

    @Test
    @DisplayName("generate fibonacci - 6")
    public void genFibonacci6()
    {
        bean.generateFibonacci(6);

        assertEquals(8, bean.getFibonacci().get(5));
    }

    @Test
    @DisplayName("get factorial - 1")
    public void getFactorial1()
    {
        int result = bean.getFactorial(1);

        assertEquals(1, result);
    }

    @Test
    @DisplayName("get factorial - 2")
    public void getFactorial2()
    {
        int result = bean.getFactorial(2);

        assertEquals(2, result);
    }

    @Test
    @DisplayName("get factorial - 3")
    public void getFactorial3()
    {
        int result = bean.getFactorial(3);

        assertEquals(6, result);
    }

    @Test
    @DisplayName("get factorial - 4")
    public void getFactorial4()
    {
        int result = bean.getFactorial(4);

        assertEquals(24, result);
    }

    @Test
    @DisplayName("get factorial - 5")
    public void getFactorial5()
    {
        int result = bean.getFactorial(5);

        assertEquals(120, result);
    }

    @Test
    @DisplayName("reset")
    public void reset()
    {
        bean.generatePrimes(2);
        bean.generateFibonacci(2);
        bean.reset();

        assertAll(
                () -> assertTrue(bean.getPrimes().isEmpty()),
                () -> assertTrue(bean.getFibonacci().isEmpty())
        );
    }


}