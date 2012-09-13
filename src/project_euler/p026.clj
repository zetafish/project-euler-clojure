(ns project-euler.core)

;; cycle detection algorithms
;; See: http://en.wikipedia.org/wiki/Cycle_detection


(defn find-first-repetition
  [tortoise hare]
  (if (= (first tortoise) (first hare))
    [tortoise hare]
    (recur (-> tortoise rest)
           (-> hare rest rest))))

(defn find-first-repetition-with-mu
  [mu tortoise hare]
  (if (= (first tortoise) (first hare))
    [tortoise hare mu]
    (recur (inc mu) (rest tortoise) (rest hare))))

(defn find-shortest-cycle
  [lam tortoise hare]
  (if (= (first tortoise) (first hare))
    lam
    (recur (inc lam) tortoise (rest hare) )))

(defn floyd [seq]
  (let [[tortoise hare] (find-first-repetition
                         (-> seq rest)
                         (-> seq rest rest))]
    (let [[mu tortoise hare] (find-first-repetition-with-mu
                               0 seq hare)]
      (let [lam (find-shortest-cycle
                 1 tortoise (rest tortoise))]
        [lam, mu])))

  (defn p26
    "Find value d < 1000 for which 1/d contains the longest
   recurring cycle."
    []))