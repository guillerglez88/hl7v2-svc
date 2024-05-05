(ns hl7v2-svc.api.message
  (:require
   [hl7v2.core :as hl7]
   [hl7v2-svc.attachment :refer [b64-dec]]))

(defn message [msg]
  (merge msg
         {:status "complete"
          :data (hl7/parse (b64-dec (get-in msg [:content :data])))}))

(comment
  (message (read-string (slurp "test/data/message-0BF864E6.edn")))

  :.)
