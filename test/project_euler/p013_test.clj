(ns project-euler.p013-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(fact "First ten digits of the sum of many 50-digit numbers"
      nil => 5537376230)
