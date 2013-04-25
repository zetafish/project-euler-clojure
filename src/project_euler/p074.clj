(use 'clojure.math.numeric-tower)

(defn fact [n]
  (loop [n n acc 1]
    (if (zero? n)
      acc
      (recur (dec n) (* n acc)))))

(def fact (into [] (map fact (range 10))))

(defn digits [n]
  (map #(- (int %) 48) (seq (str n))))

(defn step [n]
  (reduce + (map #(get fact %) (digits n))))

(def step-memo (memoize step))

(defn count-pearls [n]
  (count (into (set {}) (take 60 (iterate step-memo n)))))

(defn p074 []
  (count (filter #(= 60 %) (map count-pearls (range 1000000)))))


