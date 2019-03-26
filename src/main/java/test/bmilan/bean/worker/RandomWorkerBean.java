package test.bmilan.bean.worker;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Singleton
public class RandomWorkerBean
{
    private Random rand;
    private Long seed;
    private boolean bounded;
    private int min;
    private int max;

    @PostConstruct
    public void setup()
    {
        seed = System.currentTimeMillis();
        rand = new Random(seed);
        bounded = false;
        min = 0;
        max = 100;
    }

    @Lock(LockType.READ)
    public Integer nextRandomInteger()
    {
        if (bounded)
        {
            return rand.nextInt(max-min+1)+min;
        }
        else {
            return rand.nextInt();
        }
    }

    @Lock(LockType.WRITE)
    public void setSeed(long seed)
    {
        this.seed = seed;
    }

    @Lock(LockType.WRITE)
    public void restart()
    {
        rand = new Random(seed);
    }

    @Lock(LockType.READ)
    public List<Integer> randomIntegerList(int num)
    {
        validate(num);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            list.add(nextRandomInteger());
        }

        return list;
    }

    private void validate(int num)
    {
        if (num < 0
            || (bounded && max-min < 0) )
        {
            throw new RuntimeException("Number is below 0");
        }
    }

    @Lock(LockType.WRITE)
    public void setBounded(boolean bounded)
    {
        this.bounded = bounded;

        if (bounded){
            validate(0);
        }
    }

    @Lock(LockType.WRITE)
    public void setMin(int min)
    {
        this.min = min;

        if (bounded){
            validate(0);
        }
    }

    @Lock(LockType.WRITE)
    public void setMax(int max)
    {
        this.max = max;

        if (bounded){
            validate(0);
        }
    }

    @Lock(LockType.WRITE)
    public void setMinMax(int min, int max)
    {
        this.min = min;
        this.max = max;

        if (bounded){
            validate(0);
        }
    }

    @Lock(LockType.READ)
    public int getMin()
    {
        return min;
    }

    @Lock(LockType.READ)
    public int getMax()
    {
        return max;
    }

    @Lock(LockType.READ)
    public long getSeed()
    {
        return seed;
    }

    @Lock(LockType.READ)
    public boolean isBounded()
    {
        return bounded;
    }
}
