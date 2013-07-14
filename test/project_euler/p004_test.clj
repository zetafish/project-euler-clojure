(ns project-euler.p004-test
  (:use [project-euler.core])
  (:use [midje.sweet]))

(defn d10 [n]
  (zero? (mod n 10)))

(defn reverse-num [n]
  (loop [acc 0
         n n]
    (if (zero? n)
      acc
      (recur (+ (* 10 acc) (mod n 10))
             (quot n 10)))))

(defn f []
  (->> (for [x (remove d10 (range 100 999))
             y (remove d10 (range x 999))]
         (* x y))
       (filter #(= % (reverse-num %)))
       (reduce max)))

(fact :solved
      "Largest palindrome made from the product of two 3-digit numbers"
      (time (f))
      => 906609)

