(defn palindrome? [a]
  (let [s (seq (str a))]
    (= s (reverse s))))

(defn rev-num [n]
  (loop [n n acc 0]
    (if (zero? n)
      acc
      (recur (quot n 10) (+ (mod n 10) (* 10N acc))))))

(defn lychrel? [n]
  (loop [n n tries 0]
;    (println n)
    (if (= 50 tries)
      true
      (let [m (+ n (rev-num n))]
        (if (palindrome? m)
          false
          (recur m (inc tries)))))))

(defn p055 []
  (filter lychrel? (range 10000)))
