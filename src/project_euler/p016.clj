(defn p16
  "What is the sum of the digits of the number 2^1000?"
  []
  (reduce + (map #(Integer. (str %))
                 (seq (str (expt 2 1000))))))

