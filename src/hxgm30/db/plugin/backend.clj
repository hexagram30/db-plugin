(ns hxgm30.db.plugin.backend
  "This namespace is for functions that support the plugin system used by
  hexagram30 projects, essentially all the setup required before initializing a
  component-based system.

  Once the system has been initialized and run, the backend component takes
  over. For instance, see `hxgm30.graphdb.components.backend` for more details on
  all backend functions that take a running system as an argument."
  (:require
    [hxgm30.db.plugin.util :as util]
    [taoensso.timbre :as log])
  (:import
    (clojure.lang Keyword)))

(defn create-component
  [^Keyword backend]
  ((util/get-var backend :component 'create-component)))

(defn get-component-deps
  [^Keyword backend]
  (var-get (util/get-var backend :component 'component-deps)))

(defn create-factory
  [^Keyword backend]
  ((util/get-var backend :factory 'create)))
