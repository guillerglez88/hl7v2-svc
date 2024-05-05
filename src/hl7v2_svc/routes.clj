(ns hl7v2-svc.routes
  (:require
   [hl7v2-svc.inspect.core :as insp]))

(defn root [_req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "hi mom!"})

(defn post-message [req]
  {:status 200
   :body (insp/introspecton (:body-params req))})

(def routes
  [["/"                 {:get {:handler root}}]
   ["/Introspection"    {:post {:handler post-message}}]])
