(ns hl7v2-svc.core
  (:require
   [ring.adapter.jetty :as jetty]
   [ring.logger.timbre :as timbre]
   [taoensso.timbre :as log]
   [hl7v2-svc.config.core :as cfg])
  (:gen-class))

(defonce server (atom nil))

(defn handler [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "hi mom!"})

(def app
  (timbre/wrap-with-logger handler))

(defn start! []
  (let [port (:port @cfg/config)]
    (log/info "starting server...")
    (reset! server
            (jetty/run-jetty #'app
                             {:port port
                              :join? false}))
    (log/info "server started, listening at port " port)))

(defn stop! []
  (log/info "stopping server...")
  (.stop @server))

(defn -main [& _]
  (start!))
