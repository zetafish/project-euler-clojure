(defn binomial
  [n k]
  (loop [n n k k acc 1]
    (cond (or (= n k) (= 0 k)) acc
          :else (recur (dec n) (dec k) (* acc (/ n k))))))

(defn coefs
  [n]
  (map #(binomial n %) (range (inc n))))

(defn p53
  [n]
  (map #(->> (coefs %)
             (filter (fn [c] (> c 1000000)))
             count)
       (range 1 (inc n))))

