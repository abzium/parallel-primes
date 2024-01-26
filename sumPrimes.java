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

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println(duration + "ms " + primesFound + " " + sum);

    }
}

class sumPrimesThread extends Thread {
    private long start, end, sum, primesFound;

    public sumPrimesThread(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        this.sum = 0;
        this.primesFound = 0;
        for (long i = this.start; i < this.end + 1; i++) {
            if (isPrime(i)) {
                this.sum += i;
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
}
