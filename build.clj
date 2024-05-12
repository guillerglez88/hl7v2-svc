;; https://clojure.org/guides/tools_build#_compiled_uberjar_application_build
(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'hl7v2-svc)
(def class-dir "target/classes")
(def uber-file (format "target/%s-standalone.jar" (name lib)))

;; delay to defer side effects (artifact downloads)
(def basis
  (delay
    (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (println "cleaning `target`...")
  (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (println "building uberjar" uber-file "...")
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis @basis
                  :ns-compile ['hl7v2-svc.core]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis @basis
           :main 'hl7v2-svc.core}))
