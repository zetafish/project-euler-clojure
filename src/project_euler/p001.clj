(ns project-euler.core)

(defn p1
  "Sum all multiples of 3 or 5 below 1000"
  []
  (reduce + (filter #(or (= 0 (mod % 3))
                         (= 0 (mod % 5)))
                    (range 1000))))

