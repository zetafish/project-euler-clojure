(ns project-euler.p005-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn f []
  (reduce lcm 1 (range 1 21)))

(fact :solved
      "Smallest positive number divisible bt 1..20"
      (time (f))
      => 232792560)


