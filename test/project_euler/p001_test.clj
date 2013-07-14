(ns project-euler.p001-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(defn f []
  (reduce + (filter #(or (= 0 (mod % 3))
                         (= 0 (mod % 5)))
                    (range 1000))))

(fact :solved
      "Sum all multiples of 3 or 5 below 1000"
      (time (f))
      => 233168)


