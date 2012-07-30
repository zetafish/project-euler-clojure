(ns project-euler.core-test
  (:use clojure.test
        project-euler.core
        midje.sweet))

(fact "palindrome"
  (palindrome? 1) => true
  (palindrome? 10) => false)

(fact "euler solutions"
  (p1) => 233168
  (p2) => 4613732
  (p3) => 6857
  (p4) => 906609
  (p5) => 232792560
  (p6) => 25164150

  (p14) => 837799)
 


