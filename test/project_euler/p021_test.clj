(ns project-euler.p021-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(def MAX 10000)

(def d divisors-proper-sum)

(defn amicable? [a]
  (let [b (d a)]
    (and (not (= a b))
         (<= b MAX)
         (= a (d b)))))

(defn f []
  (->> (range 2 (inc MAX))
       (filter amicable?)
       (reduce +)))

(fact :solved
      "Evaluate the sum of all the amicable numbers under 10000."
      (time (f)) => 31626)
