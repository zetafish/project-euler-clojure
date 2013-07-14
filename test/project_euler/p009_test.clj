(ns project-euler.p009-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet])
  (:use [midje.config]))

;; aa+bb=cc, a+b+c=1000
;;   {c=1000-a-b}
;;=> aa+bb=(1000-a-b)(1000-a-b)
;;=> aa+bb=1000000-1000a-1000b-1000a+aa+ab-1000b+ab+bb
;;=> 0=1000000-2000a-2000b+2ab

(defn check [[a b]]
  (zero? (+ 1000000
            (- (* 2000 a))
            (- (* 2000 b))
            (* 2 a b))))

(defn f []
  (->> (for [a (range 1 1000)
             b (range (inc a) 1000)]
         [a b])
       (filter check)
       (map #(conj % (- 1000 (% 0) (% 1))))
       (filter #(< (% 0) (% 1)))
       (filter #(< (% 1) (% 2)))
       first
       (reduce *)))

(fact :solved
      "Special pythagorian triples"
      (time (f))
      => 31875000)

