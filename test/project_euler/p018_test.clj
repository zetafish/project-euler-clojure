(ns project-euler.p018-test
  (:use [project-euler.core])
  (:use [clojure.math.numeric-tower])
  (:use [midje.sweet]))

(def numbers
  [75
   95 64
   17 47 82
   18 35 87 10
   20  4 82 47 65
   19  1 23 75  3 34
   88  2 77 73  7 63 67
   99 65 04 28  6 16 70 92
   41 41 26 56 83 40 80 70 33
   41 48 72 33 47 32 37 16 94 29
   53 71 44 65 25 43 91 52 97 51 14
   70 11 33 28 77 73 17 78 39 68 17 57
   91 71 52 38 17 14 91 43 58 50 27 29 48
   63 66  4 68 89 53 67 30 73 16 69 87 40 31
    4 62 98 27 23  9 70 98 73 93 38 53 60  4 23])


(defn tri [n] (/ (* n (inc n)) 2))

(defn slice [row]
  (->> numbers
       (drop (tri (dec row)))
       (take row)
       vec))

(defn improve [a b]
  (vec
   (map-indexed (fn [index v]
                  (+ v
                     (cond (= index 0) (a index)
                           (= index (count a)) (a (dec index))
                           :else (max (a (dec index))
                                      (a index)))))
                b)))

(defn f [n]
  (loop [best (slice 1)]
    (let [row (count best)]
      (if (= row n)
        (reduce max best)
        (recur (improve best (slice (inc row))))))))

(fact :solved
      "Find the maximum total from top to bottom of a triangle"
      (time (f 15)) => 1074)



