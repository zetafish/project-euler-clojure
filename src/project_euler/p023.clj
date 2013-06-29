;; (ns project-euler.p023
;;   (:use project-euler.core
;;         clojure.set))

;; (def limit 28124)

;; (def abu (into (set {}) (filter abundant? (range 1 limit))))

;; (def abu-sums
;;   (into (set {})
;;         (filter #(< % limit)
;;                 (mapcat identity
;;                         (for [x abu]
;;                           (for [y abu]
;;                             (+ x y)))))))

;; (defn p23 []
;;   "Non abundant sums"
;;   (reduce + (difference (into (set {}) (range 1 limit))
;;                         abu-sums)))
