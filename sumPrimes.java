import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class sumPrimes {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        int threadCount = 8;
        long n = 100000000;
        long nPart = n / threadCount;

        sumPrimesThread[] threads = new sumPrimesThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new sumPrimesThread(nPart * i, nPart * (i + 1));
            threads[i].start();
        }

        long sum = 0;
        long primesFound = 0;

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
            sum += threads[i].getSum();
            primesFound += threads[i].getPrimesFound();
        }
        // get top 10 primes
        String topTen = threads[threadCount - 1].getTopTen();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        String result = duration + "ms " + primesFound + " " + sum + "\n" + topTen;
        writeToFile(result);

    }

    public static void writeToFile(String text) {
        File file = new File("output.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(text);
        } catch (IOException e) {
            System.err.println("An error occurred when writing to file!");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class sumPrimesThread extends Thread {
    private long start, end, sum, primesFound;
    private long[] topTen; // 10 largest primes

    public sumPrimesThread(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        this.sum = 0;
        this.primesFound = 0;
        this.topTen = new long[10];
        for (long i = this.end + 1; i > this.start; i--) {
            if (isPrime(i)) {
                this.sum += i;
                if (primesFound < 10) {
                    topTen[(int) primesFound] = i;
                }
                this.primesFound += 1;

            }
        }
    }

    boolean isPrime(long n) {
        if (n <= 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;

        int limit = (int) Math.sqrt(n) + 1;
        for (int i = 3; i < limit; i += 2) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    long getSum() {
        return this.sum;
    }

    long getPrimesFound() {
        return this.primesFound;
    }

    String getTopTen() {
        String s = "";
        for (int i = 0; i < 10; i++) {
            s += topTen[i] + "\n";
        }
        return s;
    }
}
