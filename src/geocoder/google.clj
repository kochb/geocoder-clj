(ns geocoder.google
  (:require [clojure.string :refer [lower-case]]
            [geo.core :refer [point point-x point-y]]
            [geocoder.util :refer [fetch-json format-point]]
            [inflections.core :refer [underscore]]))

(def request
  {:request-method :get
   :url "http://maps.google.com/maps/api/geocode/json"
   :query-params {:sensor false :language "en"}})

(defn- extract-type
  "Extract the address type from response."
  [response type]
  (let [type (underscore (name type))]
    (->> (:address-components response)
         (filter #(contains? (set (:types %)) type) )
         (first))))

(defn long-name
  "Extract the long name of the address type from response."
  [response type]
  (:long-name (extract-type response type)))

(defn short-name
  "Extract the short name of the address type from response."
  [response type]
  (:short-name (extract-type response type)))

(defn city
  "Returns the city of `address`."
  [address]
  (:long-name (extract-type address :locality)))

(defn country
  "Returns the country of `address`."
  [address]
  {:name (long-name address :country)
   :iso-3166-1-alpha-2 (lower-case (short-name address :country))})

(defn location
  "Returns the geographical location of `address`."
  [address]
  (if-let [location (:location (:geometry address))]
    (point 4326 (:lng location) (:lat location))))

(defn street-name
  "Returns the street name of `address`."
  [address]
  (long-name address :route))

(defn street-number
  "Returns the street number of `address`."
  [address]
  (long-name address :street-number))

(defn postal-code
  "Returns the postal code of `address`."
  [address]
  (long-name address :postal-code))

(defn region
  "Returns the region of `address`."
  [address]
  (long-name address :administrative-area-level-1))

(defn geocode-address
  "Geocode an address."
  [address & {:as opts}]
  (-> (update-in request [:query-params] #(merge %1 opts))
      (assoc-in [:query-params :address] address)
      (fetch-json)
      :results))

(defn geocode-location
  "Geocode a geographical location."
  [point & {:as opts}]
  (-> (update-in request [:query-params] #(merge %1 opts))
      (assoc-in [:query-params :latlng] (format-point point))
      (fetch-json)
      :results))
