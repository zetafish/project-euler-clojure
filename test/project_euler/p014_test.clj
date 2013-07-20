(ns project-euler.p014-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(def cache {})
(def cache-hit 0)

(defn collatz [n]
  (loop [n n
         v 1]
    (if (= 1 n) v
        (recur (if (even? n) (/ n 2)(inc (* 3 n)))
               (inc v)))))

(defn collatz2 [n]
  (if-let [x (cache n)]
    (do
      (def cache-hit (inc cache-hit))
      x)
    (let [v (if (= 1 n) 1
                (inc (collatz2 (if (even? n)
                                 (/ n 2)
                                 (inc (* 3 n))))))]
      (def cache (assoc cache n v))
      v)))

(defn g [f]
  (let [colatz (map f (range 1 1000000))
        max (reduce max colatz)]
    (inc (count (take-while #(< % max) colatz)))))


(fact :solved :slow
      "Starting number <1000000 with longest colatz sequence"
      (time (g collatz2))
      => 837799)

