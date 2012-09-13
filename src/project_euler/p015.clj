(defn p15
  "Number of paths w/o backtracking:
|   |    |    |    |    |
|---+----+----+----+----|
| 2 |  3 |  4 |  5 |  6 |
| 3 |  6 | 10 | 15 | 21 |
| 4 | 10 | 20 | 35 | 56 |
| 5 | 15 | 35 | 70 |    |
| 6 | 21 |    |    |    |
"
  [n]
  (let [div (reduce *' (range 1 (inc n)))
        num (reduce *' (range (inc n) (inc (* 2 n))))]
    (/ num div)))

