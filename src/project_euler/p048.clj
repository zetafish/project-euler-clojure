(defn p48
  "Find the last ten digits of the series 1^1 + 2^2 ... 1000^1000"
  []
  (let [sum (reduce + (map #(expt % %) (range 1 1001)))]
    (mod sum (reduce * (take 10 (repeat 10))))))