(defn p44
  "Find the pair of pentagonal numbers with sum and difference also pantagonal"
  []
  (let [numbers (iterate inc 1)
        pentagonals (map #(/ (* % (dec (* 3 %))) 2) numbers)
        pentagonal? (fn [n] (= n (first (drop-while #(< % n) pentagonals))))]
    (map pentagonal? (range 1 20))))