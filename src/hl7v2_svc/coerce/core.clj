(ns hl7v2-svc.coerce.core
  (:require
   [hl7v2.core :as hl7]
   [clojure.java.io :as io]
   [hl7v2-svc.attachment :refer [b64-dec]]))

(defn introspecton [msg]
  (let [er7 (b64-dec (get-in msg [:content :data]))
        enc (hl7/encoding (io/reader er7))]
    (merge msg
           {:status "complete"
            :encoding enc
            :tokens (hl7/tokenize (io/reader er7) enc)})))

(defn message [msg]
  (merge msg
         {:status "complete"
          :data (hl7/parse (b64-dec (get-in msg [:content :data])))}))

(comment
  (introspecton (read-string (slurp "test/data/introsp-3912D187.edn")))

  (message (read-string (slurp "test/data/introsp-3912D187.edn")))

  :.)
