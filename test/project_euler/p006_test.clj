(ns project-euler.p006-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet])
  (:use [midje.config]))

(fact "Sum square differences"
      (let [rng (range 1 101)
            square #(* % %)]
        (- (square (reduce + rng))
           (reduce + (map square rng))))
      => 25164150)


