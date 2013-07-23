(ns project-euler.p026-test
  (:use [project-euler.core])
  (:use [clojure.math.combinatorics])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(defn reciprocal-cycle [n]
  (loop [cycle []
         used {}
         k 1]
    (cond (contains? used k) (let [pos (used k)]
                               [n (take pos cycle) (drop pos cycle)])
          (< k n) (recur cycle
                         (assoc used k (count cycle))
                         (* 10 k))
          :else   (recur (conj cycle (quot k n))
                         (assoc used k (count cycle))
                         (mod k n)))))

(defn f []
  (->> (range 2 1000)
       (map reciprocal-cycle)
       (reduce (fn [a b] (if (>= (count (nth a 2))
                                (count (nth b 2))) a b)))
       first))

(fact :solved
      "Find d < 1000 with 1/d longest recurring cycle"
      (time (f)) => 983)

