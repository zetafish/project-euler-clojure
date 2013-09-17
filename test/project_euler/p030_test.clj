(ns project-euler.p030-test
  (:use [project-euler.core])
  (:use [clojure.math.combinatorics])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn power-sum? [n p]
  (= n (->> (digits n)
            (map #(expt % p))
            (reduce +))))

(defn solve
  []
  (->> (range 2 999999)
       (filter #(power-sum? % 5))
       (reduce +)))

(fact :slow :solved
 "Distinct powers"
 (time (solve)) => 443839)
