(use 'clojure.math.numeric-tower)

(defn expand [val]
  (+ 1N (/ 1 (inc val))))

(defn numerator-exceeds-denominator [v]
  (> (count (str (numerator v)))
     (count (str (denominator v)))))

(defn p057 []
  (count (filter identity
                 (map numerator-exceeds-denominator
                      (take 1000 (iterate expand 3/2))))))
