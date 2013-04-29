(use 'project-euler.core)

(def dvec (into [] (cons 0 (map #(reduce + (divisors %)) (range 1 10000)))))
(defn d [n] (get dvec n))

(defn amicable? [a b]
  (and (= (d a) b)
       (= (d b) a)))

(defn sum-amicable [n]
  (reduce +
          (for [x (range 1 n)]
            (reduce +
                    (for [y (range (inc x) n)]
                      (if (amicable? x y) (+ x y) 0))))))

(defn p021 []
  (print (sum-amicable 10000)))
