(ns dirtybird.events
    (:require [re-frame.core :as re-frame]
              [dirtybird.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :click-cell
  (fn [db [_ i j]]
    (js/console.log (str "click: " i " & " j))))
