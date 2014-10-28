(ns org.bittorrent.scribble)

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
