(ns project-euler.p010-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(defn f []
  (reduce + (take-while #(< % 2000000)
                        lazy-primes)))

(fact :solved :slow
      "Sum of all primes below 2 million"
      (time (f))
      => 142913828922)
