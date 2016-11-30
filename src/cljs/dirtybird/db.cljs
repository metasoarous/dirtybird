(ns dirtybird.db)


(def blank-board
  {:hits []
   :pieces []})

(def blank-player
  {:board blank-board
   :pending-placements [2 3 3 4 5]})

(def default-db
  {:name "Dirty bird"
   :player-a blank-player
   :player-b blank-player
   :turn :player-a
   :phase :placing})
