(ns project-euler.p003-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(defn f []
  (reduce max (factors 600851475143N)))

(fact :solved
      "The largest prime factor of 600851475143"
      (time (f))
      => 6857)

