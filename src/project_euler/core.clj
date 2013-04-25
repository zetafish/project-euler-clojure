(ns project-euler.core
  (:use clojure.math.combinatorics
        clojure.math.numeric-tower
        clojure.core.memoize
        clojure.set
        clj-time.core))

(def fib-seq
  "Lazy sequence of fibonacci numbers"
  ((fn rfib [a b] 
     (lazy-seq (cons a (rfib b (+' a b)))))
   0 1))

(defn multiple-of [n]
  #(= 0 (mod % n)))

(defn palindrome?
  "Is a number a palindrome"
  [n]
  (let [s (seq (str n))]
    (= (reverse s) s)))

(defn lazy-sieve
  [[n & ns]]
  (cons n
        (lazy-seq (lazy-sieve (filter #(> (mod % n) 0) ns)))))

(defn sieve [n]
  (let [n (int n)]
    "Returns a list of all primes from 2 to n"
    (let [root (int (Math/round (Math/floor (Math/sqrt n))))]
      (loop [i (int 3)
             a (int-array n)
             result (list 2)]
        (if (>= i n)
          (reverse result)
          (recur (+ i (int 2))
                 (if (< i root)
                   (loop [arr a
                          inc (+ i i)
                          j (* i i)]
                     (if (>= j n)
                       arr
                       (recur (do (aset arr j (int 1)) arr)
                              inc
                              (+ j inc))))
                   a)
                 (if (zero? (aget a i))
                   (conj result i)
                   result)))))))

(defn factorize
  "Get prime factors of a number"
  [num]
  (loop [primes (sieve (Math/ceil (Math/sqrt num)))
         factors []
         n num]
    (let [p (first primes)]
      (cond (= 1 n) factors
            (empty? primes) (throw "asdf")
            (zero? (mod n p)) (recur primes (cons p factors) (/ n p))
            :else (recur (rest primes) factors n)))))

(defn colatz
  "Generate colatz sequence"
  [start]
  (reverse (loop [n start
           chain []]
      (cond (= 1 n) (cons 1 chain)
            (even? n) (recur (/ n 2) (cons n chain))
            (odd? n) (recur (inc (* 3 n)) (cons n chain))))))

(defn n-colatz
  "Get colatz sequence length"
  [start]
  (loop [n start
         s 1]
    (cond (= 1 n) s
          (even? n) (recur (/ n 2) (inc s))
          (odd? n) (recur (inc (* 3 n)) (inc s)))))

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

(defn digitize [n]
  (loop [digits [] n n]
    (if (zero? n)
      digits
      (recur (cons (mod n 10) digits) (quot n 10)))))



