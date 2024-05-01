(ns hl7v2-svc.config.env
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn dot-env [f]
  (when (.exists (io/as-file f))
    (->> (slurp f)
         (str/split-lines)
         (map str/trim)
         (remove #(or (empty? %) (str/starts-with? % "#")))
         (map (fn [line]
                (let [[first second] (str/split line #"=" 2)]
                  [(keyword first)
                   (str/replace second "\"" "")])))
         (remove (comp str/blank? second))
         (into {}))))

(defn sys-env []
  (update-keys (System/getenv) keyword))

(comment

  (dot-env ".env")

  (sys-env)

  :.)
