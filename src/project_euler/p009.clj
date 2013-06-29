(ns project-euler.p009
  (:use [project-euler.core]))

(defn pythagorian? [v]
  (let [[a b c] v]
   (= (+ (* a a) (* b b))
      (* c c))))

(defn special? [v]
  (let [[a b c] v]
   (= 1000 (+ a b c))))


(defn p009
  "Special Pythagorean triplet"
  []
  (filter #(and (pythagorian? %)
                (special? %))
   (for [a (range 1000)
         b (range (inc a) 1000)
         c (range (inc b) 1000)]
     [a b c])))
