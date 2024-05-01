(ns hl7v2-svc.core
  (:require
   [ring.adapter.jetty :as jetty]
   [ring.logger.timbre :as timbre]
   [taoensso.timbre :as log]
   [reitit.ring :as ring]
   [reitit.coercion.spec :refer [coercion]]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.coercion :as rrc]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.dev.pretty :as pretty]
   [reitit.ring.middleware.exception :as exception]
   [muuntaja.core :as m]
   [hl7v2-svc.config.core :as cfg]
   [hl7v2-svc.routes :refer [routes]])
  (:gen-class))

(defonce server (atom nil))

(def app
  (ring/ring-handler
   (ring/router
    routes
    {:exception pretty/exception
     :data {:coercion coercion
            :muuntaja m/instance
            :middleware [exception/exception-middleware
                         parameters/parameters-middleware
                         muuntaja/format-negotiate-middleware
                         muuntaja/format-request-middleware
                         muuntaja/format-response-middleware
                         rrc/coerce-request-middleware
                         rrc/coerce-response-middleware
                         timbre/wrap-with-logger]}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler
     {:not-found (constantly {:status 404, :body "Not found"})}))))

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
