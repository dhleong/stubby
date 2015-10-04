# stubby

A Clojure library designed to provide support for optional dependencies

## Usage

Create a stub of the namespace, and fill it with stubs:

```clojure
(ns myproj.stubs.opt-dep
  (:require [stubby.core :refer [defstub]]))

(defstub fun1) ;; by default, using a stub throws an exception
(defstub fun2
  [arg1]
  ;; optionally provide an implementation
  (str "Stubbed impl: " arg1))
```

Instead of requiring it in the namespace, use stubby:

```clojure
(ns myproj.awesome
  (:require [stubby.core :refer [require-stub]]))
(require-stub opt-dep.core :as o :else myproj.stubs.opt-dep)
```

## Motivation

My [xRadar](http://github.com/dhleong/x-radar) and 
[xAtis](http://github.com/dhleong/x-atis) projects depend on a
proprietary network protocol, so the library implementing it
cannot be published to any public Maven repo, which means that
anyone wishing to contribute that doesn't have access to that
library would not be able to build the project. Stubby is
extracted from an effort in xRadar to get around this problem,
enabling travis-ci to work, as well as allowing potential 
contributors to work on the core project even if they don't
have access to the network.

## License

Copyright © 2015 Daniel Leong

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
