(ns project-euler.p003-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(fact "The largest prime factor of 600851475143"
      (let [num 600851475143N
            primes (reverse (primes-below (Math/sqrt num)))]
        (first (drop-while #(not (= 0 (mod num %)))
                           primes)))
      => 6857)


