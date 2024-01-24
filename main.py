import time

def is_prime(n):
    ''' Check whether a given number is prime '''

    if n == 1:
        return False
    
    for i in range(2, n): 
        if n % i == 0:
            return False
        
    return True

start_time = time.time()
n = 100000
sum = 0
primes_found = 0
for i in range(2, n):
    if is_prime(i):
        sum += i
        primes_found += 1

print(f"{time.time() - start_time} {primes_found} {sum}")


