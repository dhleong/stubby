(ns ^{:author "Daniel Leong"
      :doc "Sometimes we can't include the real thing,
           so let's have stubs so tests can still
           run without them"}
  stubby.core)

(defmacro defstub
  "Define a stubbed function, optionally providing
  a stub implementation."
  [fn-name & stub-body]
  (if (empty? stub-body)
    `(defn ~fn-name
       [& ~'args]
       (throw (UnsupportedOperationException. "Stub!")))
    `(defn ~fn-name
       ~@stub-body)))

(defmacro require-stub
  "Usage: (require-stub aileron.core :as a :else myproj.stubs.aileron)"
  [required-ns as the-alias else stub-ns-name]
  `(try
     ;; take care loading aileron so we don't explode
     ;;  when it is not available
     (require '[~required-ns :as ~the-alias])
     (catch java.io.FileNotFoundException ~'e
       (println ~(str "WARN: Using stub for " required-ns))
       (require (quote ~stub-ns-name))
       (alias (quote ~the-alias) 
              (quote ~stub-ns-name)))))
