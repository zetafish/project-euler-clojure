(ns project-euler.p023-test
  (:use [project-euler.core])
  (:use [clojure.math.combinatorics])
  (:use [midje.sweet]))

;; all integers > 28123 can be written as sum of 2 abundant numbers
(def MAX 28124)

(def summable (vec (repeat MAX false)))

(defn fill-summable []
  (let [rr (filter abundant? (range 1 MAX))]
    (doseq [n (filter #(< % MAX)
                      (for [x rr y rr] (+ x y)))]
      (def summable (assoc summable n true)))))

(defn f []
  (fill-summable)
  (->> summable
       (map-indexed (fn [index item] (if (not item) index nil)))
       (filter identity)
       (reduce +)))

;; very slow => takes 1 minute
(fact :slow :solved
      "Find the sum of all the positive integers
which cannot be written as the sum of two abundant numbers."
      (time (f)) => 4179871)


