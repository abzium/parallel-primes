# parallel-primes
Programming Assignment 1

## Compilation
This program is written in Java. JDK needs to be installed to compile it.
In a terminal open at the file location, enter the following to compile:
`javac sumPrimes.java`
This will create a sumPrimes.class file. Now enter the following to run:
`java sumPrimes`
Once the program has finished processing, the output will be written to output.txt in the same folder.

## Output format
`<execution time in milliseconds> <total number of primes found> <sum of all primes found>`
`<top 10 maximum primes from lowest to highest>`

## Summary of approach
This algorithm involves iterating over all numbers between 0 and 10^8 and checking if each one is prime.
The function that checks whether or not a given number is prime checks if the number is divisible by any factors up to the square root of the given number.
Using multiple threads, 10^8 was divided by 8 and assigned to each thread to find primes. After all the threads have finished running, the number of primes found and the total sum found in each thread are combined to get the final result.