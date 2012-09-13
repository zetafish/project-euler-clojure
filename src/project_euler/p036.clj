(defn p36
  "Find sum of all numbers <1M that are palindrome base-10 and base-2"
  [limit]
  (let [bin-str (fn [n] (loop [d [] n n]
                         (if (zero? n) d
                             (recur (cons (bit-and n 1) d)
                                    (bit-shift-right n 1)))))
        palindrome? (fn [s] (= (reverse s) s))]
    (reduce + (filter #(and (palindrome? (seq (bin-str %)))
                            (palindrome? (seq (str %))))
                      (range 1 limit)))))