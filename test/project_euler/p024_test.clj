(ns project-euler.p024-test
  (:use [project-euler.core])
  (:use [clojure.math.combinatorics])
  (:use [midje.sweet]))

(defn f []
  (reduce (fn [a b] (+ (* 10 a) b))
          (nth (permutations (range 10)) 999999)))

(fact :solved
      "millionth permutation of 0,1,2,3,4,5,6,7,8,9"
      (time (f)) => 2783915460)
