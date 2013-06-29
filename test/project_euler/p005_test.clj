(ns project-euler.p005-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(fact "Smallest positive number divisible bt 1..20"
      (reduce lcm 1 (range 1 21))
      => 232792560)


