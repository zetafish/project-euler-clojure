(defn p30
  "Find the sum of all the numbers that can be written as the sum of fifth powers of their digits."
  [pow]
  (let [sum-pow (fn [n] (reduce + (map #(expt % pow) (digitize n))))]
    (reduce + (filter #(= % (sum-pow %)) (range 2 (* pow (expt 9 pow)))))))