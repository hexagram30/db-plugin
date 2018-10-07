(ns hxgm30.db.plugin.protocols
  "This namespace is provivded as a sanity check to use during the build
  process: a place to load all the protocols and compile them."
  (:refer-clojure :exclude [drop flush]))

(load "/hxgm30/db/plugin/protocols/db"
      "/hxgm30/db/plugin/protocols/factory"
      "/hxgm30/db/plugin/protocols/graphdb"
      "/hxgm30/db/plugin/protocols/langdb"
      "/hxgm30/db/plugin/protocols/sessiondb")
