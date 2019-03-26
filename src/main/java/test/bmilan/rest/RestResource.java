package test.bmilan.rest;

import test.bmilan.bean.measurement.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/resources")
public class RestResource {

    private static final String PARAM_REPEAT = "repeat";
    private static final String PARAM_PRIMES = "numPrimes";
    private static final String PARAM_FIBONACCI = "numFibonacci";
    private static final String PARAM_FACTORIAL = "numFactorial";
    private static final String PARAM_PRIMES_MIN = "minPrimes";
    private static final String PARAM_PRIMES_MAX = "maxPrimes";
    private static final String PARAM_FIBONACCI_MIN = "minFibonacci";
    private static final String PARAM_FIBONACCI_MAX = "maxFibonacci";
    private static final String PARAM_FACTORIAL_MIN = "minFactorial";
    private static final String PARAM_FACTORIAL_MAX = "maxFactorial";

    @EJB
    private MeasurementFactory factory;

    public RestResource()
    {
    }

    protected RestResource(MeasurementFactory factory)
    {
        this.factory = factory;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/simple")
    public List<AnalyticsSeries> runSimple(
            @DefaultValue("100") @QueryParam(PARAM_REPEAT) int repeat,
            @DefaultValue("10000") @QueryParam(PARAM_PRIMES) int numPrimes,
            @DefaultValue("10000") @QueryParam(PARAM_FIBONACCI) int numFibonacci,
            @DefaultValue("23") @QueryParam(PARAM_FACTORIAL) int numFactorial
    )
    {
        Measurement simple = factory.createSimpleMeasurement(repeat,numPrimes,numFibonacci,numFactorial);

        ((SimpleMeasurement) simple).setup();  // todo: stateless does not get destroyed

        simple.runPrimes();
        simple.runFibonacci();
        simple.runFactorial();

        return simple.getMeasurements();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/random/{seed}")
    public List<AnalyticsSeries> runRandom(
            @NotNull @PathParam("seed") long seed,
            @DefaultValue("100") @QueryParam(PARAM_REPEAT) int repeat,
            @DefaultValue("900") @QueryParam(PARAM_PRIMES_MIN) int minPrimes,
            @DefaultValue("1200") @QueryParam(PARAM_PRIMES_MAX) int maxPrimes,
            @DefaultValue("900") @QueryParam(PARAM_FIBONACCI_MIN) int minFibonacci,
            @DefaultValue("1200") @QueryParam(PARAM_FIBONACCI_MAX) int maxFibonacci,
            @DefaultValue("12") @QueryParam(PARAM_FACTORIAL_MIN) int minFactorial,
            @DefaultValue("21") @QueryParam(PARAM_FACTORIAL_MAX) int maxFactorial
    )
    {
        RandomMeasurement random = (RandomMeasurement) factory.createRandomMeasurement(repeat);

        random.setup();

        random.modifyRandom(seed, minPrimes, maxPrimes,true);
        random.runPrimes();

        random.modifyRandom(seed, minFibonacci, maxFibonacci,true);
        random.runFibonacci();

        random.modifyRandom(seed, minFactorial, maxFactorial,true);
        random.runFactorial();

        return random.getMeasurements();
   }

}
