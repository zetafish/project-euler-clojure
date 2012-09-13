(defn p34
  "Find sum of all curious numbers (equal to ! of it's digits)"
  []
  (let [limit (* 6 (reduce * (range 1 10)))
        fac (loop [v [0] f 1 n 1]
              (if (= n 10) v
                  (recur (conj v f) (* f (inc n)) (inc n))))
        fac-sum (fn [n] (reduce + (map #(get fac %) (digitize n))))]
    (filter #(= % (fac-sum %)) (range 1 limit))))