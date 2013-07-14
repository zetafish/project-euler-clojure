(ns project-euler.p006-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet])
  (:use [midje.config]))

(defn f []
  (let [rng (range 1 101)
            square #(* % %)]
        (- (square (reduce + rng))
           (reduce + (map square rng)))))

(fact :solved
      "Sum square differences"
      (time (f))
      => 25164150)


