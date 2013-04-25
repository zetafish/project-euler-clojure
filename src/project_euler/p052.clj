(defn digits [n]
  (seq (str n)))

(def numbers
  (iterate inc 1))

(defn eqi-digits?
  [n]
  (= 1 (count (into #{}
                    (map #(into #{} (digits (* % n)))
                         [2 3 4 5 6])))))

(defn search []
  (first (drop-while #(not (eqi-digits? %)) numbers)))
