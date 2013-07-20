(ns project-euler.core
  (:use clojure.math.combinatorics
        clojure.math.numeric-tower
        clojure.core.memoize
        clojure.set))

(def fib-seq
  "Lazy sequence of fibonacci numbers"
  ((fn rfib [a b]
     (lazy-seq (cons a (rfib b (+' a b)))))
   0 1))

(defn multiple-of [n]
  #(= 0 (mod % n)))

(defn digits [n]
  (loop [d nil
         n n]
    (if (zero? n) d
        (recur (cons (mod n 10) d) (quot n 10)))))

(defn to-num [digits]
  (loop [acc 0
         digits digits]
    (if (empty? digits) acc
        (recur (+ (first digits) (* 10 acc))
               (rest digits)))))

(defn reverse-num [n]
  (loop [acc 0
         n n]
    (if (zero? n)
      acc
      (recur (+ (* 10 acc) (mod n 10))
             (quot n 10)))))

(defn palindrome? [n]
  (= n (reverse-num n)))

(defn lazy-sieve
  [[n & ns]]
  (cons n
        (lazy-seq (lazy-sieve (filter #(> (mod % n) 0) ns)))))

(defn prime? [n]
  (.isProbablePrime (BigInteger/valueOf n) 50))

(def lazy-primes (filter prime? (iterate inc 1)))

(defn primes-below [n]
  (take-while #(< % n) lazy-primes))

(defn factors [n]
  (loop [primes (primes-below(inc n))
         factors ()
         n n]
    (let [p (first primes)]
      (cond (= 1 n) factors
            (zero? (mod n p)) (recur primes (cons p factors) (/ n p))
            :else (recur (rest primes) factors n)))))

(defn divisors [n]
  (let [f (factors n)]
    (->> (range (inc (count f)))
         (mapcat #(combinations f %))
         (map #(reduce * %))
         distinct
         sort
         (remove #(= n %)))))

(defn fetch-text-url
  "Fetch an url with text data"
  [url]
  (with-open [stream (.openStream (java.net.URL. url))]
    (let [buf (java.io.BufferedReader.
               (java.io.InputStreamReader. stream))]
      (apply str (line-seq buf)))))



(defn num-cycles [n]
  (let [count (ceil (Math/log10 n))
        factor (expt 10 (dec count))]
    (set ( loop [numbers []
              cur n
              c count]
        (let [next (+ (* factor (mod cur 10)) (quot cur 10))]
          (if (zero? c) numbers
              (recur (conj numbers cur) (int next) (dec c))))))))

(defn perfect? [n]
  (= n (reduce + (divisors n))))

(defn deficient? [n]
  (> n (reduce + (divisors n))))

(defn abundant? [n]
  (< n (reduce + (divisors n))))

