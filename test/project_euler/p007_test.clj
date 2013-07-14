(ns project-euler.p007-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet])
  (:use [midje.config]))

(defn f []
  (nth lazy-primes 10000))

(fact :solved
      "10001st prime"
      (time (f))
      => 104743)


