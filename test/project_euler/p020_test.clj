(ns project-euler.p020-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(defn f []
  (->> (range 1N 101N)
       (reduce *)
       digits
       (reduce +)))

(fact :solved
      "Find the sum of the digits in the number 100!"
      (time (f)) => 648)
