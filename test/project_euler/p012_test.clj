(ns project-euler.p012-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

;; triangle numbers
;;
;;  1: 1,
;;  3: 1,3
;;  6: 1,2,3,6
;; 10: 1,2,5,10
;; 15: 1,3,5,15
;; 21: 1,3,7,21
;; 28: 1,2,4,7,14,28

(defn divisor-count [n]
  (->> (factors n)
       (partition-by identity)
       (map #(inc (count %)))
       (reduce *)))

(defn tri [n]
  (* (/ n 2) (inc n)))

(defn f []
  (->> (iterate inc 1)
       (map tri)
       (filter #(> (divisor-count %) 500))
       first))

(fact :solved
      "First triangle number with over 500 divisors"
      (time (f))
      => 76576500)

