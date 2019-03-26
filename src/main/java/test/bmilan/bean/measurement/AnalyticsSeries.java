package test.bmilan.bean.measurement;

import java.util.List;

public class AnalyticsSeries<T>
{
    private List<T> data;
    private double mean;
    private double sigma;
    private String name;

    public AnalyticsSeries()
    {
    }

    public AnalyticsSeries(String name, List<T> data, double mean, double sigma)
    {
        this.name = name;
        this.data = data;
        this.mean = mean;
        this.sigma = sigma;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    public double getMean()
    {
        return mean;
    }

    public void setMean(double mean)
    {
        this.mean = mean;
    }

    public double getSigma()
    {
        return sigma;
    }

    public void setSigma(double sigma)
    {
        this.sigma = sigma;
    }

}
