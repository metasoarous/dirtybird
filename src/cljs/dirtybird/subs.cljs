(ns dirtybird.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
  :phase
  (fn [db]
    (:phase db)))

(re-frame/reg-sub
  :turn
  (fn [db]
    (:turn db)))

(re-frame/reg-sub
  :next-placement-length
  (fn [db]
    (first (get-in db [(:turn db) :pending-placements]))))

(re-frame/reg-sub
  :cell-status
  (fn [db [_ i j]]
    (let [phase @(re-frame/subscribe [:phase])
          ;; TODO : if playing need to get the _other_ players board
          player-board (get db (:turn db))]
      (case phase
        :placing (when (get-in player-board [:pieces [i j]]) :placed)
        ;; TODO Need to handle hit and hit-miss
        :playing (if (get-in player-board [:pieces [i j] :placed]))))))
