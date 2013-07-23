(ns project-euler.p012-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(defn tri [n]
  (* (/ n 2) (inc n)))

(defn f []
  (->> (iterate inc 1)
       (map tri)
       (filter #(> (divisors-count %) 500))
       first))

(fact :solved
      "First triangle number with over 500 divisors"
      (time (f)) => 76576500)

