(ns app
  (:require [clojure.string :as string]))

(def changes [[:new "Added two swamp biome maps" []]
              [:new "Introduced 8 new unit traits"
               [[:plain "Xenophobe: gain +1 ATK for each friendly unit of the same class deployed" []]
                [:plain "Guarded: gain +1 DEF for each friendly unit of the same class deployed" []]
                [:plain "Complementarian: gain +1 ATK for each friendly, unique class deployed" []]
                [:plain "TeamWarded: gain +1 DEF for each friendly, unique class deployed" []]
                [:plain "Bloodbound: gain +1 ATK for each friendly unit of the same element deployed" []]
                [:plain "Bloodblessed: gain +1 DEF for each friendly unit of the same element deployed" []]
                [:plain "Bloodblocked: gain +1 ATK for each friendly, unique element deployed" []]
                [:plain "Bloodbuilt: gain +1 DEF for each friendly, unique element deployed" []]]]
              [:changed "Tweaked procedural run generation, shops and mazes are less likely to appear early on and more likely to appear when most useful." []]])

(defn type->html [type]
  (case type
    :new "<b>NEW</b>"
    :changed "<b>CHANGED</b>"
    :removed "<b>REMOVED</b>"
    :plain ""))

(defn changes->markdown [changelist])

(defn changes->html [changelist]
  (if (> (count changelist) 0)
    (str "<ul>\n"
         (string/join "\n" (map (fn [[type text children]] (str "<li>" (type->html type) " " text (if (> (count children) 0) "\n" "") (changes->html children) "</li>")) changelist))
         "\n</ul>")
    ""))

(defn type->steam [type]
  (case type
    :new "[b]NEW[/b]"
    :changed "[b]CHANGED[/b]"
    :removed "[b]REMOVED[/b]"
    :plain ""))

(defn changes->steam [changelist]
  (if (> (count changelist) 0)
    (str "[list]\n"
         (string/join "\n" (map (fn [[type text children]] (str "[*] " (type->steam type) " " text (if (> (count children) 0) "\n" "") (changes->steam children))) changelist))
         "\n[/list]")
    ""))

(changes->steam changes)
(println (changes->steam changes))