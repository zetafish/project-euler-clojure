(defn p19
  "How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?"
  []
  (count
   (filter #(= 7 %)
           (map day-of-week
                (for [year (range 1901 2001)
                      month (range 1 13)]
                  (date-time year month 1))))))

