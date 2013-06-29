(ns project-euler.p002-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(fact "Sum of all fibonacci numbers less than 1 million"
      (reduce + (filter even?
                        (take-while #(< % 4000000) fib-seq)))
      => 4613732)


