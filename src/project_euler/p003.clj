(defn p3
  "What is the largest prime factor of 600851475143"
  []
  (let [num 600851475143
        primes (reverse (sieve (Math/sqrt num)))]
    (first
     (drop-while #(not (= 0 (mod num %))) primes))))

