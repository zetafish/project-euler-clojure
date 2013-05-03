(ns project-euler.solutions
  (:use project-euler.core))


(defn solve [f]
  (print (resolve f) (f)))

(solve p001)
(solve p002)
