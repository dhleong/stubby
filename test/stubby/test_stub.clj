(ns stubby.test-stub
  (:require [stubby.core :refer [defstub]]))

(defstub throws-exc)
(defstub has-body
  []
  (str "body"))
