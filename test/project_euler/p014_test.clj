(ns project-euler.p014-test
  (:use [project-euler.core])
  (:use [midje.sweet])
  (:use [clojure.repl]))

(def cache {})

(defn colatz-length [n]
  (loop [acc 1
         n n]
    (cond (= 1 n) acc
          (even? n) (recur (inc acc) (/ n 2))
          :else     (recur (inc acc) (inc (* 3 n))))))

(defn colatz-length2 [n]
  (let [x (cache n)]
    (if (nil? x)
      (let [v (cond (= 1 n) 1
                    (even? n) (inc (colatz-length2 (/ n 2)))
                    :else     (inc (colatz-length2 (inc (* 3 n)))))]
        (do (def cache (assoc cache n v))
            v))
      x)))

(defn seek [f n]
  (let [ll (map f (range 1 n))
        mm (reduce max ll)]
    (inc (count  (take-while #(not (= % mm)) ll)))))

(fact "Starting number under one million that produces longest colatz sequence"
      (time (seek colatz-length2 1000000))
      => 837799)


