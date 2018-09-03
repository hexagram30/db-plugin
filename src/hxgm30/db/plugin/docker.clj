(ns hxgm30.db.plugin.docker
  (:require
    [clojure.java.io :as io]
    [clojure.java.shell :as shell])
  (:gen-class))

(defn get-uid
  []
  (:out (shell/sh "id" "-u" "$USER")))

(defn read-compose-file
  [filename]
  (->> filename
       (format "docker/%s")
       io/resource
       slurp))

(defn compose-up
  [filename]
  (println "Starting up database ...")
  (:out (shell/sh "docker-compose" "-f" "-" "up" "-d"
                :in (read-compose-file filename)
                :env {:uid (get-uid)})))

(defn compose-down
  [filename]
  (println "Shuting down database ...")
  (:out (shell/sh "docker-compose" "-f" "-" "down"
              :in (read-compose-file filename)
              :env {:uid (get-uid)})))

(defn -main
  [cmd & args]
  (case cmd
    "read" (read-compose-file (first args))
    "up" (compose-up (first args))
    "down" (compose-down (first args)))
  (shutdown-agents)
  (println "Done."))
