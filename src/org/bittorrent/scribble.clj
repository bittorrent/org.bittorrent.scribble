(ns org.bittorrent.scribble
  (:import (clojure.lang PersistentVector))
  (:require [clojure.string :as str]))

(defn build-host [host-map]
  (if (and (get host-map :passwd)
           (not (get host-map :user)))
    (throw (Exception. "Passwd field without user field.")))

  (str (or (:scheme host-map) "http")
       "://"
       (:user host-map)
       (if (contains? host-map :passwd) ":")
       (:passwd host-map)
       (if (contains? host-map :user) "@")
       (:host host-map)
       (if (contains? host-map :port) ":")
       (:port host-map)
       "/render"))

(defn param-val-to-vector [val]
  (if (vector? val)
    val
    [val]))

(defn build-params-for-key [pair]
  (map #(str (name (key pair)) "=" %) (param-val-to-vector (val pair))))

(defn build-params [params]
  (if (not (empty? params))
    (str "?" (str/join "&" (flatten (map build-params-for-key params))))
    ""))

