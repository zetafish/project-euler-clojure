(ns project-euler.p009-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet])
  (:use [midje.config]))

(defn pythagorian? [v]
  (let [[a b c] v]
    (= (+ (* a a) (* b b)) (* c c))))

(defn special? [v]
  (let [[a b c] v]
    (= 1000 (+ a b c))))

(fact "Special pythagorian triples"
      (->> (for [a (range 1000)
                 b (range (inc a) 1000)
                 c (range (inc b) 1000)]
             [a b c])
           (filter special?)
           (filter pythagorian?)
           first
           (reduce *))
      => 31875000)
