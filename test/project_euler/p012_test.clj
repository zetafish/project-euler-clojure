(ns project-euler.p012-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(def cache {})

(defn f []
  (->> (iterate inc 1)
       (map #(/ (* % (inc %)) 2))
       (filter #(> (count (divisors %)) 500))
       first))

(fact :slow
      "First triangle number with over 500 divisors"
      (time (f))
      => 76576500)
