(ns hxgm30.db.plugin.config
  (:require
   [clojure.edn :as edn]
   [clojure.string :as string]
   [hxgm30.common.file :as common]))

(def config-file "hexagram30-config/db.edn")

(defn get-backend-type
  [cfg-data]
  (or (keyword (System/getProperty "db.backend"))
      (get-in cfg-data [:backend :plugin])))

(defn clean-config
  "Only include backend config data for the enabled backend."
  [cfg backend]
  (-> cfg
      (assoc :backend (select-keys
                       (:backend cfg)
                       [:plugin backend]))
      (assoc-in [:backend :plugin] backend)))

(defn data
  ([]
    (data config-file))
  ([filename]
    (let [cfg (common/read-edn-resource filename)]
      (clean-config cfg (get-backend-type cfg)))))
