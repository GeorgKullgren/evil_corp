(ns evil-corp.core-test
  (:require [evil-corp.core :refer :all])
  (:use [midje.sweet]))


(fact "given a word if the word is in the list it should replaced with XXX"
  (def blacklist ["nice"])
  (censorship blacklist "nice") => "XXXX")

(fact "Given two words and second word is censored returns word XXXX"
  (def blacklist ["nice"])
  (censorship blacklist "evil nice") => "evil XXXX")

(fact "Given two blacklisted words and four words only middle words are censored"
  (def blacklist ["nice" "baksheesh"])
  (censorship blacklist "evil baksheesh nice murder") => "evil XXXXXXXXX XXXX murder")

(fact "Given a word if the begining of the word matches with a blacklisted word it should return XXXX"
  (def blacklist ["friend"])
  (censorship blacklist "you are friendly") => "you are XXXXXXXX")

(fact "Given word that ends with blacklisted word, return word"
  (def blacklist ["friendly"])
  (censorship blacklist "you are unfriendly") => "you are unfriendly")

(fact "Given a single word, if the word is in the translation table it will be translated"
  (def tranlation-table [["fun" "timewaste"]]) 
  (translator tranlation-table "fun") => "timewaste")

(fact "Given a two words where one exists in translation table, replace one word"
  (def tranlation-table [["fun" "timewaste"]]) 
  (translator tranlation-table "fun evil") => "timewaste evil")

(fact "when word is in the translation table it should be translated"
  (def translation-table [
                          ["bad" "ungood"]
                          ["better" "gooder"]
                          ["objection" "thoughtcrime"]
                          ["agree" "crimestop"]
                          ])
  (translator translation-table "Objection is bad, a better thing to do, is to agree.") => "Thoughtcrime is ungood, a gooder thing to do, is to crimestop.")
