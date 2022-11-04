(defproject aula.2_schemas "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.datomic/dev-local "1.0.243"]]
  :repositories [["cognitect-dev-tools" {:url      "https://dev-tools.cognitect.com/maven/releases/"
                                         :username "ramonp.rios@gmail.com"
                                         :password "5D2A78EAEF55B18129E64F3C2086B620F92B53D6"}]]
  :repl-options {:init-ns aula.2-schemas})
