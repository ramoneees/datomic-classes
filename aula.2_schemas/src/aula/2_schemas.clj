(ns aula.2-schemas
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :dev-local
                       :system "dev"}))

(d/create-database client {:db-name "movies"})

(d/delete-database client {:db-name "movies"})

(def conn (d/connect client {:db-name "movies"}))
(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def movie-schema [{:db/ident :movie/title
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc "The title of the movie"}

                   {:db/ident :movie/genre
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc "The genre of the movie"}

                   {:db/ident :movie/release-year
                    :db/valueType :db.type/long
                    :db/cardinality :db.cardinality/one
                    :db/doc "The year the movie was released in theaters"}])

(def first-movies [{:movie/title "The Goonies"
                    :movie/genre "action/adventure"
                    :movie/release-year "1985"}
                   {:movie/title "Commando"
                    :movie/genre "thriller/action"
                    :movie/release-year 1985}
                   {:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1984}])