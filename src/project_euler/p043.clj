(ns project-euler.p043
  (:use (clojure.math.combinatorics)))

(defn multiple-of [n]
  (fn [x] (zero? (rem x n))))

(defn useful? [n]
  (some #(zero? (rem n %)) '(2 3 5 7 11 13 17)))

(def triples
  (map #(->> %
             clojure.string/join
             Integer/parseInt)
       (combinations (range 10) 3)))



(defn multiples [n]
  (take-while #(< )))