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
