(defn p5
  "Find the smallest positive number that is divisible by 1..20"
  []
  (reduce lcm 1 (range 1 21)))

