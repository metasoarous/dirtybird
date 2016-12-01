(ns dirtybird.events
    (:require [re-frame.core :as re-frame]
              [clojure.core.match :refer-macros [match]]
              [dirtybird.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(defn placement
  [[i j] [i' j'] placement-length]
  (cond
    (= i i'
      (cond (> j j')))
    (= j j'
      ())))

(defn valid-placement?
  [[i j] [i' j']]
  (or (= i i') (= j j')))

(re-frame/reg-event-db
  :click-cell
  (fn [{:as db :keys [turn phase]} [_ i j]]
    (js/console.log (str "click: " i " & " j))
    (if (= phase :placing)
      (let [{:as player :keys [placing-phase placement-start]} (get-in db turn)]
        (case placing-phase
          :start
          (update-in db [turn :placement-start] [i j])
          :end
          (if (valid-placement? placement-start [i j])
            (-> db
              (assoc-in [turn :placement-start] nil)
              (update-in [turn :placements] conj (placement placement-start [i j])))
            db))))))

(-> data (some-fn an-arg) (another-fn more args))
(another-fn (some-fn data an-arg) more args)

