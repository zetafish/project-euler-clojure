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