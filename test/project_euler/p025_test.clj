(ns project-euler.p025-test
  (:use [project-euler.core])
  (:use [clojure.math.combinatorics])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn f []
  (->> fib-seq
       (take-while #(< % (expt 10 999)))
       count))

(fact :solved
      "First fibonacci ordinal with 1000 digits"
      (time (f)) => 4782)
