(ns project-euler.p022-test
  (:use [project-euler.core])
  (:use [clojure.java.io])
  (:use [midje.sweet]))

(def url "http://projecteuler.net/project/names.txt")

(defn fetch-url[address]
  (with-open [stream (.openStream (java.net.URL. address))]
    (let  [buf (java.io.BufferedReader. 
                (java.io.InputStreamReader. stream))]
      (apply str (line-seq buf)))))


(defn score-word [word]
  (->> word
       seq
       (map #(inc (- (int %) (int \A))))
       (reduce +)))

(defn f []
  (->> (fetch-url url)
       ((partial #(clojure.string/split % #",")))
       (map #(clojure.string/replace % "\"" ""))
       sort
       (map-indexed (fn [index word] (* (score-word word) (inc index))))
       (reduce +)))

(fact :solved
      "Get total score of a file"
      (time (f)) => 871198282)
