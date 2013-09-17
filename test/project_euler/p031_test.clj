(ns project-euler.p031-test
  (:use [project-euler.core])
  (:use [clojure.math.combinatorics])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn solve []
  ((fn aux [coins goal]
     (cond (zero? goal) 1
           (empty? coins) 0
           (> (first coins) goal) (aux (rest coins) goal)
           :else (+ (aux coins (- goal (first coins)))
                    (aux (rest coins) goal))))
   [200 100 50 20 10 5 2 1] 200))

(fact 
 "Coin sums"
 (time (solve)) => 73682)
