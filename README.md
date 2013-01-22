# GEOCODE-CLJ [![Build Status](https://travis-ci.org/r0man/geocoder-clj.png)](https://travis-ci.org/r0man/geocoder-clj)

A Clojure library for various geocoder services.

## Installation

Via Clojars: http://clojars.org/geocoder-clj

## Usage

    (use 'geocoder.core)

    (geocode-address "Senefelderstraße 24, 10437 Berlin")
    ;=> ({:country {:name "Germany", :iso-3166-1-alpha-2 "de"},
    ;=>   :city "Berlin",
    ;=>   :location {:latitude 52.54258, :longitude 13.42299},
    ;=>   :street-name "Senefelderstraße",
    ;=>   :street-number "24",
    ;=>   :postal-code "10437",
    ;=>   :region "Berlin"})

    (geocode-location {:latitude 52.54258, :longitude 13.42299})
    ;=> ({:country {:name "Germany", :iso-3166-1-alpha-2 "de"},
    ;=>   :city "Berlin",
    ;=>   :location {:latitude 52.54258, :longitude 13.42299},
    ;=>   :street-name "Senefelderstraße",
    ;=>   :street-number "24",
    ;=>   :postal-code "10437",
    ;=>   :region "Berlin"})

    (geocode-ip-address "92.229.192.11")
    ;=> {:country {:name "Germany", :iso-3166-1-alpha-2 "de"},
    ;=>  :region {:id "16"},
    ;=>  :city "Berlin",
    ;=>  :location {:latitude 52.516693115234375, :longitude 13.399993896484375},
    ;=>  :area-code 0,
    ;=>  :dma-code 0,
    ;=>  :metro-code 0}

## Configuration

Most of the geocode services used by this library must be configured
before being accessible.

Add this to your "~/.lein/init.clj" file.

    (def geocoder-config
      {:bing {:key "BING-API-KEY"}
       :geonames {:key "GEONAMES-API-KEY"}
       :yahoo {:key "YAHOO-API-KEY"}})

Or add this to the Leiningen "project.clj" file of your project.

    (defproject my-project "1.0.0"
      :geocoder-config
      {:bing {:key "BING-API-KEY"}
       :geonames {:key "GEONAMES-API-KEY"}
       :yahoo {:key "YAHOO-API-KEY"}})

## License

Copyright (C) 2013 Roman Scherer

Distributed under the Eclipse Public License, the same as Clojure.
