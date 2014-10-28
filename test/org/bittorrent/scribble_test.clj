(ns org.bittorrent.scribble-test
  (:require [clojure.test :refer :all]
            [org.bittorrent.scribble :refer :all]))

(def host-map-1  {:host "graphite.bittorrent.com"})

(def host-map-2  {:scheme "https"
                  :user "user"
                  :passwd "passwd"
                  :host "graphite.bittorrent.com"
                  :port 1234})

(def bad-map-1  {:scheme "https"
                 :passwd "passwd"
                  :host "graphite.bittorrent.com"
                  :port 1234})

(deftest host-test
  (testing ""
    (is (= (build-host host-map-1)
           "http://graphite.bittorrent.com/render"))
    (is (= (build-host host-map-2)
           "https://user:passwd@graphite.bittorrent.com:1234/render"))
    (is (thrown? Exception (build-host bad-map-1)))
    ))

;;from=-2hours&height=20&until=now&width=120&hideGrid=true&hideLegend=true&hideAxes=true&bgcolor=white&fgcolor=black&margin=0&colorList=black

(def params {:from "-2hours"
             ;; &height=20
             ;; &until=now
             ;; &width=120
             ;; &hideGrid=true&hideLegend=true&hideAxes=true&bgcolor=white&fgcolor=black&margin=0&colorList=black
})

;; (deftest a-test
;;   (testing "FIXME, I fail."
;;     (is (= 0 1))))
