(ns dirtybird.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [dirtybird.events]
              [dirtybird.subs]
              [dirtybird.views :as views]
              [dirtybird.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
