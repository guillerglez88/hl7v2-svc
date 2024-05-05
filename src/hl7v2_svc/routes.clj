(ns hl7v2-svc.routes
  (:require
   [hl7v2-svc.api.introspection :as intr]
   [hl7v2-svc.api.message :as msg]
   [hl7v2-svc.api.structure :as struct]))

(defn root [_req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "hi mom!"})

(defn post-introspection [req]
  {:status 200
   :body (intr/introspecton (:body-params req))})

(defn post-message [req]
  {:status 200
   :body (msg/message (:body-params req))})

(defn post-struct [req]
  {:status 201
   :body (struct/create (:body-params req))})

(def routes
  [["/"                 {:get   {:handler root}}]
   ["/Message"          {:post  {:handler post-message}}]
   ["/Introspection"    {:post  {:handler post-introspection}}]
   ["/Structure"        {:post  {:handler post-struct}}]])
