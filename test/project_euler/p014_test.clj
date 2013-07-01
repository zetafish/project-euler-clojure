(ns project-euler.p014-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(fact "Starting number under one million that produces longest colatz sequence"
      nil => 837799)
