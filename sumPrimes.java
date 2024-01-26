import java.lang.Math;
import java.util.Arrays;

class sumPrimes {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int n = 100000000;
        long sum = 0;
        int primesFound = 0;

        boolean[] primesList = new boolean[n + 1];
        Arrays.fill(primesList, true);
        primesList[0] = false;
        primesList[1] = false;

        int limit = ((int) Math.sqrt(n)) + 1;
        for (int i = 2; i <= limit; i++) {

            if (primesList[i]) {
                for (int j = 0; j < n; j++) {

                    int index = (int) Math.pow(i, 2) + (j * i);
                    if (index >= n) {
                        break;
                    }
                    primesList[index] = false;

                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (primesList[i]) {

                sum += i;
                primesFound += 1;
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000;
        System.out.println(duration + "ms " + primesFound + " " + sum);
    }
};