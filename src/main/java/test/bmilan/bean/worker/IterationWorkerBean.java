package test.bmilan.bean.worker;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class IterationWorkerBean
{

    private List<Integer> primes;
    private List<Integer> fibonacci;

    @PostConstruct
    public void setup()
    {
        primes = new ArrayList<>();
        fibonacci = new ArrayList<>();
    }

    public void generatePrimes(int numPrimes)
    {
        validate(numPrimes);
        handleTrivialPrimes(numPrimes);

        if (numPrimes < 4)
            return;

        fillPrimes(numPrimes);
    }

    private void fillPrimes(int numPrimes)
    {
        int filled = primes.size();
        int current = primes.get(filled-1)+1;

        while (filled < numPrimes)
        {
            final int c = current++;
            Optional<Integer> divisor = primes
                    .stream()
                    .filter( p -> p!=1 )
                    .filter(p -> c % p == 0)
                    .findFirst();

            if (divisor.isEmpty()){
                primes.add(c);
                filled++;
            }
        }

    }

    private void handleTrivialPrimes(int numPrimes)
    {
        switch(numPrimes){
            case 0 :
                break;
            case 1 :
                primes.add(1);
                break;
            case 2 :
                primes.add(1);
                primes.add(2);
                break;
            default :
                primes.add(1);
                primes.add(2);
                primes.add(3);
                break;
        }
    }

    private void validate(int num)
    {
        if (num<0)
        {
            throw new RuntimeException("Number is below 0");
        }
    }

    public List<Integer> getPrimes()
    {
        return primes;
    }

    public List<Integer> getFibonacci()
    {
        return fibonacci;
    }

    public void generateFibonacci(int numFibonacci)
    {
        validate(numFibonacci);
        handleTrivialFibonacci(numFibonacci);

        if (numFibonacci < 3){
            return;
        }

        fillFibonacci(numFibonacci);
    }

    private void handleTrivialFibonacci(int numFibonacci)
    {
        switch(numFibonacci){
            case 0 :
                break;
            case 1 :
                fibonacci.add(1);
                break;
            default :
                fibonacci.add(1);
                fibonacci.add(1);
                break;
        }
    }

    private void fillFibonacci(int numFibonacci)
    {
        int filled = fibonacci.size();

        while (filled < numFibonacci)
        {
            int pos = fibonacci.size()-1;
            fibonacci.add(fibonacci.get(pos) + fibonacci.get(pos-1));
            filled++;
        }
    }


    public int getFactorial(int numFactorial)
    {
        validate(numFactorial);

        int factorial = 1;
        for (int i = 1; i <= numFactorial; i++) {
             factorial = factorial*i;
        }

        return factorial;
    }

    public void reset()
    {
        this.setup();
    }

}
