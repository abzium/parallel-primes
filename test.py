import math, time
start_time = time.time()
n = 100000000
sum = 0
primes_found = 0
boolvals = [True] * (n + 1)
boolvals[0] = False
boolvals[1] = False
for i in range(2, math.isqrt(n) + 1):
    if boolvals[i] == True:
        for j in range(n):
            index = (i ** 2) + j * i
            if index >= n:
                break
            boolvals[index] = False

for i in range(n):
    if boolvals[i] == True:
        sum += i
        primes_found += 1

print(f"{(time.time() - start_time) * 1000}ms {primes_found} {sum}")
