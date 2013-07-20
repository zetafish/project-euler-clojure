(ns project-euler.p019-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [clj-time.core :only [day-of-week date-time]])
  (:use [midje.sweet]))

(defn f []
  (->> (for [y (range 1901 2001)
             m (range 1 13)]
         (date-time y m 1))
       (map day-of-week)
       (filter #(= 7 %))
       count))


(fact :solved
      "How many Sundays fell on the first of the month during
the twentieth century (1 Jan 1901 to 31 Dec 2000)?"
      (time (f)) => 171)
