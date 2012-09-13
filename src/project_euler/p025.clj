(defn p25
  "What is the first term in the Fibonacci sequence to contain 1000 digits?"
  [digits]
  (let [lim (expt 10 (dec digits))]
    (count (take-while #(< % lim) fib-seq))))

