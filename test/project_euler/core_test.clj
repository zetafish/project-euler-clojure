(ns project-euler.core-test
  (:use project-euler.core
        midje.sweet))

(fact "palindrome"
  (palindrome? 1) => true
  (palindrome? 10) => false)


