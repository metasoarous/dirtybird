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
    (let [phase @(re-frame/subscribe [:phase])]
      (case phase
        :placing :TODO2
        :playing :TODO1))))
