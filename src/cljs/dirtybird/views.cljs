(ns dirtybird.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]))


(defn title []
  (let [name (re-frame/subscribe [:name])]
    [:h1 @name]))


(defn placing-prompt
  []
  [:div
   [:h3 "Place a piece (length "
    @(re-frame/subscribe [:next-placement-length]) ")"]])


(defn controls
  []
  [:div
   [:h2 "Current player: "
    (case @(re-frame/subscribe [:turn])
      :player-a "Player A"
      :player-b "Player B")]
   (case @(re-frame/subscribe [:phase])
     :placing [placing-prompt]
     :playing "Make a hit!")])


(defn tile
  [i j]
  (let [status @(re-frame/subscribe [:cell-status i j])]
    [:div {:style {:display "flex"
                   :border-style "solid"
                   :border-width "2px"
                   :border-color "grey"
                   :background-color (case status
                                       (:placed :hit-miss) "grey"
                                       :hit "red"
                                       "lightgrey")
                   :width "76px"
                   :height "76px"}
           :on-click (fn [_] (re-frame/dispatch [:click-cell i j]))}]))


(defn board []
  [:div {:style {:width "800px"
                 :display "flex"
                 :flex-wrap "wrap"}}
   (for [i (range 10)
         j (range 10)]
     [tile i j])])


(defn main-panel []
  [:div
   [title]
   [controls]
   [board]])

