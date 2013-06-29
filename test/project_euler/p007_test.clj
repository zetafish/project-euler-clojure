(ns project-euler.p007-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet])
  (:use [midje.config]))

(fact "10001st prime"
      (nth lazy-primes 10000)
      => 104743)


