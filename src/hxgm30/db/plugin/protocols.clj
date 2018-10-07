(ns hxgm30.db.plugin.protocols
  "This namespace is provivded as a sanity check to use during the build
  process: a place to load all the protocols and compile them."
  (:refer-clojure :exclude [drop flush]))

(load "protocols/db"
      "protocols/factory"
      "protocols/graphdb"
      "protocols/langdb"
      "protocols/sessiondb")
