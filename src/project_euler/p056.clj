(use 'clojure.math.numeric-tower)

(defn sum-digits [n]
  (reduce + (map #(- (int %) 48) (seq (str n)))))

(defn max-sum-digits [a]
  (apply max (map #(sum-digits ( expt a %)) (range 100))))

(defn p056 []
  (apply max (map max-sum-digits (range 100))))
