(ns hxgm30.db.plugin.util
  (:import
    (clojure.lang Keyword)
    (clojure.lang Symbol)))

(def base-ns "hxgm30.db.plugin")

(defn construct-ns
  [^Keyword backend ns-suffix]
  (symbol (format "%s.%s.%s" base-ns (name backend) ns-suffix)))

(defn construct-db-ns
  [^Keyword backend]
  (construct-ns backend "api.db"))

(defn construct-factory-ns
  [^Keyword backend]
  (construct-ns backend "api.factory"))

(defn construct-component-ns
  [^Keyword backend]
  (construct-ns backend "components.core"))

(def ns-lookup
  {:db construct-db-ns
   :factory construct-factory-ns
   :component construct-component-ns})

(defn get-ns
  [^Keyword backend ^Keyword ns-type ^Symbol sym]
  ((ns-type ns-lookup) backend))

(defn get-var
  [^Keyword backend ^Keyword ns-type ^Symbol sym]
  (let [ns (get-ns backend ns-type sym)]
    (require ns)
    (ns-resolve ns sym)))
