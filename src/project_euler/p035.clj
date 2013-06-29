(defn p35
  "How many circular primes are there below one million?"
  [lim]
  (let [primes (set (sieve lim))]
    (count (filter empty?
                   (map #(clojure.set/difference % primes)
                        (map num-cycles primes))))))

