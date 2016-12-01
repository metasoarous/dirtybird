(ns dirtybird.db)


(def blank-board
  {:hits #{}
   :pieces #{}})

(def blank-player
  {:board blank-board
   :pending-placements [2 3 3 4 5]
   :placing-phase :start})

(def default-db
  {:name "Dirty bird"
   :player-a blank-player
   :player-b blank-player
   :turn :player-a
   :phase :placing})

(defn next-placement-length
  [db]
  (first (get-in db [(:turn db) :pending-placements])))

(defn current-board [{:as db :keys [phase turn]}]
  (let [player (case phase
                 :placing turn
                 :playing ({:player-a :player-b, :player-b :player-a} turn))]
    (get-in db [player :board])))

