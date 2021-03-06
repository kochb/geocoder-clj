# GEOCODE-CLJ [![Build Status](https://travis-ci.org/r0man/geocoder-clj.png)](https://travis-ci.org/r0man/geocoder-clj)

A Clojure library for various geocoder services.

## Installation

Via Clojars: http://clojars.org/geocoder-clj

## Usage

### Bing

    (require '[geocoder.bing :as bing])
    (bing/geocode-address "Senefelderstraße 24, 10437 Berlin")
    (bing/geocode-location "52.54258,13.42299")

### Geonames

    (require '[geocoder.geonames :as geonames])
    (geonames/geocode-address "Senefelderstraße 24, 10437 Berlin")
    (geonames/geocode-location "52.54258,13.42299")

### Google

    (require '[geocoder.google :as google])
    (google/geocode-address "Senefelderstraße 24, 10437 Berlin")
    (google/geocode-location "52.54258,13.42299")

### Maxmind

    (require '[geocoder.maxmind :as maxmind])
    (def db (geonames/make-db "/usr/share/GeoIP/GeoIP.dat"))
    (maxmind/geocode-ip-address db "92.229.192.11")

## License

Copyright (C) 2013 Roman Scherer

Distributed under the Eclipse Public License, the same as Clojure.
