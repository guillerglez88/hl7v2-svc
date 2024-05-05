(ns hl7v2-svc.routes)

(defn root [_req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "hi mom!"})

(defn post-message [req]
  {:status 200
   :body (:body-params req)})

(def routes
  [["/"         {:get {:handler root}}]
   ["/Message"  {:post {:handler post-message}}]])
