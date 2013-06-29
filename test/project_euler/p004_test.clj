(ns project-euler.p004-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(fact "Largest palindrom made from the product of two 3-digit numbers"
      (reduce max
              (filter palindrome?
                      (for [x (range 100 999)
                            y (range 100 999)]
                        (* x y))))
      => 906609)


