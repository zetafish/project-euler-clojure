(ns project-euler.p002-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(defn f []
  (reduce + (filter even?
                    (take-while #(< % 4000000) fib-seq))))

(fact :solved
      "Sum of all fibonacci numbers less than 1 million"
      (time (f))
      => 4613732)


