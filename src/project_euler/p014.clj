(defn p14
  "Find starting number for the longest chain"
  []
  (let [lengths (map n-colatz (range 1 1000000))
        max (reduce max lengths)
        pre (take-while #(not (= max %)) lengths)]
    (inc (count pre))))

