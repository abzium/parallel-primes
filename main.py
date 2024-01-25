import time, math, threading

def is_prime(n):
    ''' Check whether a given number is prime '''

    if n <= 1:
        return False
    
    if n == 2:
        return True
    
    if n % 2 == 0:
        return False
    
    for i in range(3, math.isqrt(n) + 1, 2):
        if n % i == 0:
            return False
        
    return True

def sum_primes(start, end, results, index):
    #start_time = time.time()
    sum = 0
    primes_found = 0
    for i in range(start, end + 1):
        if is_prime(i):
            sum += i
            primes_found += 1
    #print(f"{time.time() - start_time} {primes_found} {sum}")
    results[index] = (primes_found, sum)

start_time = time.time()
thread_count = 8
n = 10000000
n_part = n // thread_count
num_of_primes = 0
sum = 0

threads = [None] * thread_count
results = [(0, 0)] * thread_count

for i in range(len(threads)):
    threads[i] = threading.Thread(target=sum_primes, args=(n_part * i, n_part * (i + 1), results, i))
    threads[i].start()

for i in range(len(threads)):
    threads[i].join()

for i in results:
    num_of_primes += i[0]
    sum += i[1]

end_time = time.time() - start_time

print(f"Parallel Time: {end_time}")
print(f"Total number of primes: {num_of_primes}")
print(f"Sum: {sum}")

sum_primes(0, n, results, 0)