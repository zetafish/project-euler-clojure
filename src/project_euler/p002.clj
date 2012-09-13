(ns project-euler.core)

(defn p2
  "Sum of all even fibonacci numbers less than 1 million"
  []
  (reduce + (filter even?
                    (take-while #(< % 4000000) fib-seq))))

