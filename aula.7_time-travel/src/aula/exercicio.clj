(ns aula.exercicio
  (:require [datomic.client.api :as d]))



(def bank-schema [{:db/ident :account/id
                   :db/valueType :db.type/uuid
                   :db/unique :db.unique/identity
                   :db/cardinality :db.cardinality/one}
                  {:db/ident :account/balance
                   :db/valueType :db.type/float
                   :db/cardinality :db.cardinality/one}])


(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "banco"})
  (def conn (d/connect client {:db-name "banco"}))

  (d/delete-database client {:db-name "banco"})

  (d/transact conn {:tx-data bank-schema}))