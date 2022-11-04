(ns aula.2-schemas
  (:require [datomic.client.api :as d]))


(def movie-schema-edited [{:db/id :movie/title
                           :db/ident :movie/name}
                          {:db/id :movie/genre
                           :db/cardinality :db.cardinality/many}])

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
                    :movie/release-year 1985}
                   {:movie/title "Commando"
                    :movie/genre "thriller/action"
                    :movie/release-year 1985}
                   {:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1984}])

(def first-movies-new [{:movie/name "Corra"
                        :movie/genre "horror"
                        :movie/release-year 1985}])


(def a-mulher-rei [{:movie/name "A mulher rei"
                    :movie/genre "pancadaria"}])


(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "movies"}) 
  (def conn (d/connect client {:db-name "movies"}))

  (d/delete-database client {:db-name "movies"})

  )