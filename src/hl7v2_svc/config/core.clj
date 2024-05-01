(ns hl7v2-svc.config.core
  (:require
   [hl7v2-svc.config.env :as env]))

(def config
  (delay
    (let [{:keys [PORT]} (merge (env/dot-env ".env")
                                (env/sys-env))]
      {:port (parse-long (or PORT "8080"))})))

(comment

  @config

  :.)
