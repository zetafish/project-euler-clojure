(defn p6
  "Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum."
  []
  (let [rng (range 1 101)
        square #(* % %)]
    (- (square (reduce + rng))
       (reduce + (map square rng)))))

