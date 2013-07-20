(ns project-euler.p016-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn f []
  (->>
   (expt 2 1000)
   digits
   (reduce +)))

(fact :solved
      "What is the sum of the digits of the number 2^1000"
      (time (f)) => 1366)
