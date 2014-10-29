(ns org.bittorrent.scribble
  (:import (clojure.lang PersistentVector)
           (java.net URLEncoder))
  (:require [clojure.string :as str]))

(defn build-host [host-map]
  "Builds host portion of URL"
  (if (not (contains? host-map :host))
    (throw (Exception. "No host specified.")))
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

(defn ensure-vector [val]
  "Wraps value in vector if not a vector"
  (if (vector? val)
    val
    [val]))

(defn build-params-for-key [pair]
  "Generates vector full of 'key=value' strings"
  (map #(str (name (key pair)) "=" (URLEncoder/encode (str %))) (ensure-vector (val pair))))

(defn build-params [params]
  "Builds entire parameters section of URL"
  (if (not (empty? params))
    (str "?" (str/join "&" (flatten (map build-params-for-key params))))
    ""))

(defn build-url [host-params graph-params]
  "Combines host params and graph/target params into a URL that will return a graph image.
  Requires two maps, host-params, and graph-params.

  host-params keys: :host (required), :scheme (defaults to http), :user (optional), :passwd (optional), :port (optional)
  graph-params keys: anything supported by graphite. Values can be a vector to repeat multiple targets."
  (str (build-host host-params) (build-params graph-params)))
