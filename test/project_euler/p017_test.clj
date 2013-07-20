(ns project-euler.p017-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [clojure.string :only [lower-case]])
  (:use [clojure.pprint :only [cl-format]])
  (:use [midje.sweet]))

(def letters (into #{} (seq "abcdefghijklmnopqrstuvwxyz")))

(def to-english
  (partial cl-format nil "~@(~@[~R~]~^ ~A.~)"))

(defn count-letters [n]
  (+ (if (and (> n 100) (not (zero? (mod n 100))))
       3 ; "and"
       0)
     (->> n
          to-english
          lower-case
          seq
          (filter #(contains? letters %))
          count)))

(defn f []
  (->> (range 1 1001)
       (map count-letters)
       (reduce +)))

(fact :solved
      "Count the letters in english numbers"
      (time (f)) => 21124)
