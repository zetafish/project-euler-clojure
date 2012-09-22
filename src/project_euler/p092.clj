(defn digits [n]
  (map #(- (int %) (int \0)) (seq (str n))))

(defn sum-square-digits [n]
  (reduce + (map #(* % %) (digits n))))

(def step (memoize sum-square-digits))

(defn walk [n]
  (cond (= 1 n) 1
        (= 89 n) 89
        :else (last (take-while #(not (or (= 1 %) (= 89 %)))
                                (iterate step n)))))
(defn sentinel [n]
  (step (walk [n])))


(defn solve92 [n]
  (count (filter #(= % 89)
                 (map sentinel (range 1 n)))))