(use 'project-euler.core)

(def dvec (into [] (cons 0 (map #(reduce + (divisors %)) (range 1 28124)))))
(defn d [n] (get dvec n))

(defn abundant? [n]
  (< n (d n)))

(def abu (filter #(abundant? %) (range 1 28124)))

(defn p022 []
  (->> (for [x abu]
         (for [y abu]
           (+ x y)))
       (mapcat identity)
       (into (set {}))
       (reduce +)))
