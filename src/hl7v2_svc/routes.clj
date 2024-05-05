(ns hl7v2-svc.routes
  (:require
   [hl7v2-svc.coerce.core :refer [introspecton message]]))

(defn root [_req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "hi mom!"})

(defn post-introspection [req]
  {:status 200
   :body (introspecton (:body-params req))})

(defn post-message [req]
  {:status 200
   :body (message (:body-params req))})

(def routes
  [["/"                 {:get {:handler root}}]
   ["/Message"          {:post {:handler post-message}}]
   ["/Introspection"    {:post {:handler post-introspection}}]])
