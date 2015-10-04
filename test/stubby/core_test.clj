(ns stubby.core-test
  (:require [clojure.test :refer :all]
            [stubby.core :refer :all]))

(require-stub nonexistent :as n :else stubby.test-stub)

(deftest require-stub-test
  (testing "Creates Alias"
    (is (false? (contains? (ns-aliases *ns*) 's)))
    (require-stub nonexistent :as s :else stubby.test-stub)
    (is (contains? (ns-aliases *ns*) 's))
    (is (not (nil? (get (ns-aliases *ns*) 's))))
    (is (= (find-ns 'stubby.test-stub)
           (get (ns-aliases *ns*) 's)))
    (ns-unalias *ns* 's)
    (is (false? (contains? (ns-aliases *ns*) 's)))))

(deftest use-stub-test
  (testing "No-body stub throws UnsupportedOperationException"
    (is (thrown? UnsupportedOperationException
                 (n/throws-exc))))
  (testing "has-body stub just works"
    (is (= "body" (n/has-body)))))
