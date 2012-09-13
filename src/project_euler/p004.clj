(defn p4
  "What is the largest palindrome made from the product of two 3 digit numbers"
  []
  (last
   (sort
    (filter palindrome?
            (for [x (range 100 999) y (range 100 999)]
              (* x y))))))

