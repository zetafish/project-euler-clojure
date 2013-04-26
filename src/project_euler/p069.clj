(use 'clojure.math.numeric-tower)

(def lazy-primes3
  (letfn [(enqueue [sieve n step]
            (let [m (+ n step)]
              (if (sieve m)
                (recur sieve m step)
                (assoc sieve m step))))
          (next-sieve [sieve candidate]
            (if-let [step (sieve candidate)]
              (-> sieve
                (dissoc candidate)
                (enqueue candidate step))
              (enqueue sieve candidate (+ candidate candidate))))
          (next-primes [sieve candidate]
            (if (sieve candidate)
              (recur (next-sieve sieve candidate) (+ candidate 2))
              (cons candidate 
                (lazy-seq (next-primes (next-sieve sieve candidate) 
                            (+ candidate 2))))))]
    (cons 2 (lazy-seq (next-primes {} 3)))))

(defn factorize [n]
  (loop [n n p lazy-primes3 acc ()]
    (if (= 1 n)
      acc
      (if (zero? (mod n (first p)))
        (recur (/ n (first p)) p (cons (first p) acc))
        (recur n (rest p) acc)))))

(defn tot [n]
  (* n (reduce * (map #(- 1 (/ 1 %))
                      (into (set {}) (factorize n))))))

(defn coprime [a b]
  (= 1 (gcd a b)))

(defn totient [n]
  (count (filter #(coprime n %) (range 1 n))))

(defn p069 []
  (apply max (map #(/ totient %) (range 1 (inc 100000)))))
