(ns project-euler.p010-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(fact "Sum of all primes below 2 million"
      (reduce + (take-while #(< % 2000000)
                            lazy-primes))
      => 142913828922)
