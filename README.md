# performance-testing
Example of a REST performance tester for diverse application servers

Usage:

1. Run in the main directory:
> mvn install

2. Start an application server with EE 8 support (Wildfly 15)

3. Deploy the performance-testing-1.0-SNAPSHOT.war file from /target to your application server.

4. Open a browser and run the performance tests via REST calls:

http://127.0.0.1:8080/performance-testing-1.0-SNAPSHOT/rest/resources/simple

http://127.0.0.1:8080/performance-testing-1.0-SNAPSHOT/rest/resources/simple?repeat=1000&numPrimes=500&numFibonacci=1000&numFactorial=23

http://127.0.0.1:8080/performance-testing-1.0-SNAPSHOT/rest/resources/random/12345123

(12345123 is here just a random seed, so that the same random distribution can be repeated across different application servers)

http://127.0.0.1:8080/performance-testing-1.0-SNAPSHOT/rest/resources/random/12345123?repeat=10&minPrimes=100&maxPrimes=120&minFibonacci=110&maxFibonacci=130&minFactorial=12&maxFactorial=18

The higher the "repeat" parameter, the better the statistics, other parameters control the performance. Results are in a JSON format. The data array contains runtimes in microseconds for each repeat-loop, as well as the the mean and the sigma of all loops.
