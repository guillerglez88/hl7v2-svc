(ns hl7v2-svc.routes)

(def routes
  [["/" {:get {:handler (fn [_req]
                          {:status 200
                           :headers {"Content-Type" "text/html"}
                           :body "hi mom!"})}}]])
