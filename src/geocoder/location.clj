(ns geocoder.location)

(defrecord Location [latitude longitude])

(defn make-location [latitude longitude]
  (Location.
   (Double/parseDouble (str latitude))
   (Double/parseDouble (str longitude))))

(defprotocol ILocation
  (latitude [location]
    "Returns the latitude of location.")
  (longitude [location]
    "Returns the longitude of location."))

(extend-type clojure.lang.IPersistentMap
  ILocation
  (latitude [this]
    (:latitude this))
  (longitude [this]
    (:longitude this)))