(ns evil-corp.core
  (use clojure.string))

(defn replace-word-with-xxx
  [word]
  (clojure.string/replace word #"." "X"))

(defn is-prefix-blacklisted 
  [prefix word]
  (not (= (re-find (re-pattern (str "^" prefix)) word) nil)))

(defn censor-word 
  [blacklist word]
  (if (some #(is-prefix-blacklisted % word) blacklist) 
           (replace-word-with-xxx word)                        
           word))

(defn censorship 
  [blacklist string]  
  (clojure.string/join " " 
                       (map #(censor-word blacklist %)
                            (clojure.string/split string #"\s"))))

(defn translate-word
  [transtable word]
  (def matched-pair (filterv (fn [p] (= word (get p 0))) transtable))
  (if (= (nth matched-pair 0 "nothing matched") "nothing matched")
    word
    (get (get matched-pair 0) 1)))
 
(defn translator
  [transtable string]
  (clojure.string/join " "
                       (map #(translate-word transtable %)
                            (clojure.string/split string #"\s"))))
