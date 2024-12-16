(ns clojue.core
  (:require [lanterna.screen :as s]
            [clojure.math :refer [round]]))

(def scr (s/get-screen :swing))

;; Lanterna Interface

(defn center-char [cols rows]
  (let [halve #(round (/ % 2))
        x (halve cols)
        y (halve rows)]
    (s/put-string scr x y "@")))

(defn clear-scr [cols rows]
  (let [blank (apply str (repeat cols " "))]
    (doseq [row (doall (range rows))]
      (s/put-string scr 0 row blank))))

(defn debug-size [x y]
  (let [text (str "X: " x " Y: " y)
        text-indent (round (/ (count text) 2))]
    (s/put-string scr (- (/ x 2) text-indent) 0 text))
  (let [x (dec x)
        y (dec y)]
    (s/put-string scr 0 0 "0")
    (s/put-string scr 0 y "Y")
    (s/put-string scr x 0 "X")
    (s/put-string scr x y  "1")))

(defn handle-resize [xs ys]
  ;; The initial redraw is used refresh screen's size
  (s/redraw scr)
  (println "Resize Screen: \n"
           "  X: " xs "\n"
           "  Y: " ys)
  (clear-scr xs ys)
  (debug-size xs ys)
  (center-char xs ys)
  (s/redraw scr))

(s/start scr)
(s/add-resize-listener scr handle-resize)


(comment (s/redraw scr)
         (s/get-size scr)
         (s/get-key-blocking scr)
         (apply clear-scr (s/get-size scr))
         (s/stop scr))
