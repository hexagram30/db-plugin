(ns hxgm30.db.plugin.component
  (:require
    [com.stuartsierra.component :as component]
    [hxgm30.db.plugin.config :as config]
    [hxgm30.db.plugin.util :as util]
    [taoensso.timbre :as log])
  (:import
    (clojure.lang Keyword)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   Utility Functions   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn get-cfg
  [system]
  (get-in system [:config :data]))

(defn backend-plugin
  [system]
  (get-in (get-cfg system) [:backend :plugin]))

(defn backend-subtype
  [system]
  (get-in (get-cfg system) [:backend :subtype]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   Config Component API   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn db-conn
  ([system]
    (db-conn system (backend-plugin system)))
  ([system ^Keyword backend]
    ((util/get-var backend :component 'get-conn) system)))

(defn factory
  ([system]
    (factory system (backend-plugin system)))
  ([system ^Keyword backend]
    ((util/get-var backend :component 'get-factory) system)))

(defn db-call
  ([system func]
    (db-call system func []))
  ([system func args]
    (db-call system (backend-plugin system) func args))
  ([system ^Keyword backend func args]
    (let [db-call-fn (util/get-var backend :component 'db-call)]
      (log/debugf "Using db-call %s with args %s ..."
                  db-call-fn
                  [system func args])
      (db-call-fn system func args))))

(defn factory-call
  ([system func]
    (factory-call system func []))
  ([system func args]
    (factory-call system (backend-plugin system) func args))
  ([system ^Keyword backend func args]
    ((util/get-var backend :component 'factory-call) system func args)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   Component Lifecycle Implementation   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrecord DBPluginConfig
  [data])

(defn start
  [this]
  (log/info "Starting db plugin config component ...")
    (log/trace "Using configuration:" (:data this))
    (log/debug "Started db plugin config component.")
    this)

(defn stop
  [this]
  (log/info "Stopping db plugin config component ...")
  (log/debug "Stopped db plugin config component.")
  (assoc this :data nil))


(def lifecycle-behaviour
  {:start start
   :stop stop})

(extend DBPluginConfig
  component/Lifecycle
  lifecycle-behaviour)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   Component Constructor   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-component
  ""
  [data]
  (map->DBPluginConfig {:data data}))
