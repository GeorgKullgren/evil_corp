(ns evil-corp.core
  (use clojure.string))

(defn replace-word-with-xxx
  [word]
  (clojure.string/replace word #"." "X"))

(defn censor-word 
  [blacklist word]
  (if (some #(= word %) blacklist) 
           (replace-word-with-xxx word)                        
           word))

(defn censorship 
  [blacklist string]  
  (clojure.string/join " " 
                       (map #(censor-word blacklist %)
                            (clojure.string/split string #"\s"))))

