(ns aula.4-queries1
  (:require [datomic.client.api :as d]))

(def recipe-pattern
  [:recipe/id
   :recipe/name
   {:recipe/ingredients
    [:ingredient/id
     :ingredient/name]}
   {:recipe/owner
    [:owner/name]}])

(def recipes-schema
  [{:db/ident :ingredient/id
    :db/valueType :db.type/long
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :ingredient/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   
   {:db/ident :owner/id
    :db/valueType :db.type/long
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :owner/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   
   {:db/ident :recipe/id
    :db/valueType :db.type/long
    :db/unique :db.unique/identity
    :db/cardinality :db.cardinality/one}
   {:db/ident :recipe/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :recipe/ingredients
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/many}
   {:db/ident :recipe/price
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one}
   {:db/ident :recipe/owner
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}])

(def ingredient-samples 
  (mapv (fn [i]
          {:ingredient/id i
           :ingredient/name (str "ingredient " i)})
        (range 1 31)))
(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "recipes"})

  (d/delete-database client {:db-name "recipes"})

  (def conn (d/connect client {:db-name "recipes"}))
  (def db (d/db conn))
  (d/q '[:find ?recipe-id ?recipe-name ?ingredient-name ?owner-name
         :keys recipe-id recipe-name ingredient-name owner-name
         :where
         [?recipe :recipe/id ?recipe-id]
         [?recipe :recipe/name ?recipe-name]
         [?recipe :recipe/ingredients ?ingredients]
         [?ingredients :ingredient/name ?ingredient-name]
         [?recipe :recipe/owner ?owner]
         [?owner :owner/name ?owner-name]]
       (d/db conn))
  )