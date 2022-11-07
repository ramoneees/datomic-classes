(ns aula.2-schemas
  (:require [datomic.client.api :as d]))



(defn year-pred
  [s]
  (< 1985 s))


(def employees [{:db/ident :department/id
                :db/valueType :db.type/uuid
                :db/cardinality :db.cardinality/one}
               {:db/ident :department/name
                :db/valueType :db.type/string
                :db/cardinality :db.cardinality/one}

               {:db/ident :project/id
                :db/valueType :db.type/uuid
                :db/cardinality :db.cardinality/one}
               {:db/ident :project/name
                :db/valueType :db.type/string
                :db/cardinality :db.cardinality/one}

               {:db/ident :employee/name
                :db/valueType :db.type/string
                :db/cardinality :db.cardinality/one}
               {:db/ident :employee/job-title
                :db/valueType :db.type/string
                :db/cardinality :db.cardinality/one}
               {:db/ident :employee/phone-number
                :db/valueType :db.type/string
                :db/cardinality :db.cardinality/one}
               {:db/ident :employee/salary
                :db/valueType :db.type/string
                :db/cardinality :db.cardinality/one}
               {:db/ident :employee/department
                :db/valueType :db.type/ref
                :db/cardinality :db.cardinality/one}
               {:db/ident :employee/project
                :db/valueType :db.type/ref
                :db/cardinality :db.cardinality/one}])

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
                    :db.attr/preds 'aula.2-schemas/year-pred
                    :db/cardinality :db.cardinality/one
                    :db/doc "The year the movie was released in theaters"}])



(def movie-schema-edited [{:db/id :movie/title
                           :db/ident :movie/name}
                          {:db/id :movie/genre
                           :db/cardinality :db.cardinality/many}])

(def first-movies [{:movie/title "Repo Man"
                    :movie/genre "punk dystopia"
                    :movie/release-year 1967}])

(def first-movies-new [{:movie/name "Corra"
                        :movie/genre "horror"
                        :movie/release-year 1985}])


(def a-mulher-rei [{:movie/name "A mulher rei"
                    :movie/genre "pancadaria"}])


(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "employees"}) 
  (def conn (d/connect client {:db-name "employees"}))

  (d/delete-database client {:db-name "movies"})

  )