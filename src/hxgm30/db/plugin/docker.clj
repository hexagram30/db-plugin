(ns hxgm30.db.plugin.docker
  (:require
    [clojure.string :as string]
    [clojure.java.io :as io]
    [clojure.java.shell :as shell])
  (:gen-class))

(defn get-out
  "Get OUT!"
  [result]
  (string/trim (:out result)))

(defn get-uid
  []
  (get-out (shell/sh "id" "-u" "$USER")))

(defn get-pwd
  []
  (get-out (shell/sh "pwd")))

(defn read-compose-file
  [filename]
  (->> filename
       (format "docker/%s")
       io/resource
       slurp))

(defn compose-up
  [filename]
  (println "Starting up database ...")
  (get-out (shell/sh "docker-compose" "-f" "-" "up" "-d"
                     :in (read-compose-file filename)
                     :env {:uid (get-uid)
                           :pwd (get-pwd)})))

(defn compose-down
  [filename]
  (println "Shuting down database ...")
  (get-out (shell/sh "docker-compose" "-f" "-" "down"
                     :in (read-compose-file filename)
                     :env {:uid (get-uid)
                           :pwd (get-pwd)})))

(defn -main
  [cmd & args]
  (case cmd
    "read" (read-compose-file (first args))
    "up" (compose-up (first args))
    "down" (compose-down (first args)))
  (shutdown-agents)
  (println "Done."))
