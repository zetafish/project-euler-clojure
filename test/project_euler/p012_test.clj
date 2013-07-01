(ns project-euler.p012-test
  (:use [project-euler.core])
  (:use [midje.sweet]))


(fact "First triangle number with over 500 divisors"
      (->> (iterate inc 1)
           (map #(/ (* % (inc %)) 2))
           (filter #(> (count (divisors %)) 500))
           first)
      => 76576500)
