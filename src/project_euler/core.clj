(ns project-euler.core
  (:use clojure.math.combinatorics
        clojure.math.numeric-tower
        clojure.core.memoize
        clojure.set
        clj-time.core))

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))

(def fib-seq 
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

(defn p1
  "Sum all multiples of 3 or 5 below 1000"
  []
  (reduce + (filter #(or (= 0 (mod % 3))
                         (= 0 (mod % 5)))
                    (range 1000))))

(defn p2
  "Sum of all even fibonacci numbers less than 1 million"
  []
  (reduce + (filter even?
                    (take-while #(< % 4000000) fib-seq))))

(defn p3
  "What is the largest prime factor of 600851475143"
  []
  (let [num 600851475143
        primes (reverse (sieve (Math/sqrt num)))]
    (first
     (drop-while #(not (= 0 (mod num %))) primes))))


(defn p4
  "What is the largest palindrome made from the product of two 3 digit numbers"
  []
  (last
   (sort
    (filter palindrome?
            (for [x (range 100 999) y (range 100 999)]
              (* x y))))))

(defn p5
  "Find the smallest positive number that is divisible by 1..20"
  []
  (reduce lcm 1 (range 1 21)))

(defn p6
  "Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum."
  []
  (let [rng (range 1 101)
        square #(* % %)]
    (- (square (reduce + rng))
       (reduce + (map square rng)))))

(defn p11
  []
  (let [vv [ 8 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91  8
            49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
            81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
            52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
            22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
            24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
            32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
            67 26 20 68 02 62 12 20 95 63 94 39 63  8 40 91 66 49 94 21
            24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
            21 36 23  9 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
            78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14  9 53 56 92
            16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
            86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
            19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
            04 52  8 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
            88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
            04 42 16 73 38 25 39 11 24 94 72 18  8 46 29 32 40 62 76 36
            20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
            20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
            01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48]
        to-east  [[0 0] [1 0] [2 0] [3 0]]
        points (fn [[a b c d]] (fn [xy] (map + a xy)))
        fitter (fn [[_ _ _ [dx dy]]] (fn [[x y]] (and
                                                (<= 0 (+ dx x))
                                                (< (+ dx x) 20)
                                                (<= 0 (+ dy y))
                                                (< (+ dy y) 20))))]
    
    (for [d [to-east]]
      (filter (fitter d) (for [x (range 20)
                               y (range 20)]
                           [x y]))))))

(defn p14
  "Find starting number for the longest chain"
  []
  (let [lengths (map n-colatz (range 1 1000000))
        max (reduce max lengths)
        pre (take-while #(not (= max %)) lengths)]
    (inc (count pre))))

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


(defn p16
  "What is the sum of the digits of the number 2^1000?"
  []
  (reduce + (map #(Integer. (str %))
                 (seq (str (expt 2 1000))))))

(defn p19
  "How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?"
  []
  (count
   (filter #(= 7 %)
           (map day-of-week
                (for [year (range 1901 2001)
                      month (range 1 13)]
                  (date-time year month 1))))))

(defn p22
  "What is the total name score in the file."
  []
  (let [url "http://projecteuler.net/project/names.txt"
        text (clojure.string/replace (fetch-text-url url) #"\"" "")
        names (sort (clojure.string/split text #","))
        name-weight (fn [name]
                      (reduce + (map #(inc (- (int %) (int \A))) (seq name))))]
    (reduce + (map-indexed (fn [i name] (* (inc i) (name-weight name))) names))))

(defn p24
  "What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?"
  []
  (nth (permutations (range 10)) 999999))

(defn p25
  "What is the first term in the Fibonacci sequence to contain 1000 digits?"
  [digits]
  (let [lim (expt 10 (dec digits))]
    (count (take-while #(< % lim) fib-seq))))

(defn p29
  "How many distinct terms are in the sequence generated by ab for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?"
  [lo hi]
  (let [rng (range 2 (inc hi))]
    (count (sort (distinct (for [a rng b rng] (expt a b)))))))

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

(defn p30
  "Find the sum of all the numbers that can be written as the sum of fifth powers of their digits."
  [pow]
  (let [sum-pow (fn [n] (reduce + (map #(expt % pow) (digitize n))))]
    (reduce + (filter #(= % (sum-pow %)) (range 2 (* pow (expt 9 pow)))))))

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

(defn p34
  "Find sum of all curious numbers (equal to ! of it's digits)"
  []
  (let [limit (* 6 (reduce * (range 1 10)))
        fac (loop [v [0] f 1 n 1]
              (if (= n 10) v
                  (recur (conj v f) (* f (inc n)) (inc n))))
        fac-sum (fn [n] (reduce + (map #(get fac %) (digitize n))))]
    (filter #(= % (fac-sum %)) (range 1 limit))))

(defn p35
  "How many circular primes are there below one million?"
  [lim]
  (let [primes (set (sieve lim))]
    (count (filter empty?
                   (map #(clojure.set/difference % primes)
                        (map num-cycles primes))))))

(defn p36
  "Find sum of all numbers <1M that are palindrome base-10 and base-2"
  [limit]
  (let [bin-str (fn [n] (loop [d [] n n]
                         (if (zero? n) d
                             (recur (cons (bit-and n 1) d)
                                    (bit-shift-right n 1)))))
        palindrome? (fn [s] (= (reverse s) s))]
    (reduce + (filter #(and (palindrome? (seq (bin-str %)))
                            (palindrome? (seq (str %))))
                      (range 1 limit)))))

(defn p44
  "Find the pair of pentagonal numbers with sum and difference also pantagonal"
  []
  (let [numbers (iterate inc 1)
        pentagonals (map #(/ (* % (dec (* 3 %))) 2) numbers)
        pentagonal? (fn [n] (= n (first (drop-while #(< % n) pentagonals))))]
    (map pentagonal? (range 1 20))))


(defn p45
  "Find the next triangle number that is also pentagonal and hexagonal > 40755."
  []
  (let [numbers (iterate inc 1)]
    (loop [[t & ts] (map #(/ (* % (inc %)) 2) numbers) ; triangles
           [p & ps] (map #(/ (* % (dec (* 3 %))) 2) numbers) ; pentagonals
           [h & hs] (map #(* % (dec (* 2 %))) numbers)] ; hexagonals
      (cond (and (= t p h) (> t 40755)) t
            (= t p h) (recur ts ps hs)
            (and (<= t p) (<= t h)) (recur ts (cons p ps) (cons h hs))
            (and (<= p t) (<= p h)) (recur (cons t ts) ps (cons h hs))
            (and (<= h t) (<= h p)) (recur (cons t ts) (cons p ps) hs)

            ))))

(defn p48
  "Find the last ten digits of the series 1^1 + 2^2 ... 1000^1000"
  []
  (let [sum (reduce + (map #(expt % %) (range 1 1001)))]
    (mod sum (reduce * (take 10 (repeat 10))))))


