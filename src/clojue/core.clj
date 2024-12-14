(ns clojue.core
  (:require [lanterna.screen :as s]))

(def scr (s/get-screen))

(s/start scr)

(s/put-string scr 10 10 "Hello World")
(s/redraw scr)

#_(s/get-key-blocking)
