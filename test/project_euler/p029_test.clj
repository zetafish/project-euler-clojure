(ns project-euler.p029-test
  (:use [clojure.math.combinatorics])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn solve
  []
  (let [rn (range 2 101)]
    (->>
     (for [a rn b rn] (expt a b))
     distinct
     count))
  )

(fact
 "Distinct powers"
 (time (solve)) => 9183)
