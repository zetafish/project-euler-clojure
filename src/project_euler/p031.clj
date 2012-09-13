(defn p31
  "How many ways can we make 2 BP?"
  [n]
  ((fn aux [coins goal]
     (cond (zero? goal) 1
           (empty? coins) 0
           (> (first coins) goal) (aux (rest coins) goal)
           :else (+ (aux coins (- goal (first coins)))
                    (aux (rest coins) goal))))
   [200 100 50 20 10 5 2 1] n))