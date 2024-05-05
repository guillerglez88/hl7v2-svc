(ns hl7v2-svc.attachment
  (:import java.util.Base64))

(defn b64-dec [payload]
  (.decode (Base64/getDecoder)
           payload))

(defn b64-enc [payload]
  (.encodeToString (Base64/getEncoder)
                   payload))

(comment
  (b64-enc
   (b64-dec
    "aGkgbW9tIQ=="))
  ;; => "aGkgbW9tIQ=="

  :.)
