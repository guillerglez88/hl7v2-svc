(ns user
  (:require
   [hl7v2-svc.core :refer [start! stop!]]))

(defn start []
  (start!))

(defn stop []
  (stop!))

(defn restart []
  (stop!)
  (start!))

(comment

  (start)

  (stop)

  (restart)

  :.)
