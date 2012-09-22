(defn digits [n]
  ( map #(- (int %) 48) (str n))) 

(defn factorial [n]
  (loop [n n acc 1]
    (if (= 0 n)
      acc
      (recur (dec n) (* n acc)))))

(defn curious? [n]
  (= n (reduce + (map factorial (digits n)))))

(reduce + (filter curious? (range 3 50000)))