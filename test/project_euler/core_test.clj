(ns project-euler.core-test
  (:use [project-euler.core])
  (:use [midje.sweet]))


(fact "divisors count: http://en.wikipedia.org/wiki/Divisor_function"
      (divisors-count 6) => 4
      (divisors-count 12) => 6
      (divisors-count 24) => 8)

(fact "divisors sum"
      (divisors-sum 6) => 12
      (divisors-sum 12) => 28
      (divisors-sum 24) => 60)

(fact "divisors-propert-sum"
      (divisors-proper-sum 6) => 6
      (divisors-proper-sum 12) => 16
      (divisors-proper-sum 220) => 284
      (divisors-proper-sum 284) => 220)
