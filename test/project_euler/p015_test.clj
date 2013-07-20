(ns project-euler.p015-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn count-paths
  "Number of paths w/o backtracking:
|   |    |    |    |    |
|---+----+----+----+----|
| 2 |  3 |  4 |  5 |  6 |
| 3 |  6 | 10 | 15 | 21 |
| 4 | 10 | 20 | 35 | 56 |
| 5 | 15 | 35 | 70 |    |
| 6 | 21 |    |    |    |
"
  [n]
  (let [div (reduce *' (range 1 (inc n)))
        num (reduce *' (range (inc n) (inc (* 2 n))))]
    (/ num div)))



(defn f []
  (count-paths 20))

(fact :solved
      "Count number of lattice paths"
      (time (f))
      => 137846528820)
