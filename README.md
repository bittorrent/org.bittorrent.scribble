# org.bittorrent.scribble

A Clojure library designed to construct [Graphite](http://graphite.readthedocs.org/en/latest/index.html) graph URLs.

## Installation

Add this to your `project.clj`'s dependencies:

    [org.bittorrent.scribble "0.1.0"]

## Usage

    (ns my-app 
        (:require [org.bittorrent.scribble :as scribble]))
    
    (scribble/build-url host-params graph-params)
    => "http://graphite.whatever.com/render?from=-2hours&target=some.stats.one&target=some.stats.two"
    
`host-params` should be a map containing the keys: 
    
    {
        :host "graphite.whatever.com" ; required
        :scheme "https" ; optional - defaults to "http"
        :port 8080      : optional - defaults to unspecified, lets browser choose
        :user "myuser"  ; optional - for http basic auth
        :passwd "asdf"  ; optional - only include if you input a username
    }
    
`graph-params` should be a map containing valid Graphite arguments, similar to the following:

    {
        :from "-2hours"
        :until "now"
        :targets ["stats.info.firstTarget" "stats.info.secondTarget"]
    }
    
Any key may have a vector of multiple values; it will be repeated in the URL as a result. Doing so is optional, however, even for `:targets`.

## License

Copyright © 2014 BitTorrent, Inc. Written by Jason Whitlark and Aaron Cohen.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
