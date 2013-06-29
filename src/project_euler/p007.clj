(ns project-euler.p007
  (:use [project-euler.core]))

(defn p007
  "10001st prime"
  []
  (nth lazy-primes 10000)) 
