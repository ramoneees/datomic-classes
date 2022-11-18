(ns aula.7-time-travel
  (:require [datomic.client.api :as d]))

(def uuid-generate
  (java.util.UUID/randomUUID))

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

  (d/create-database client {:db-name "bank"})
  (def conn (d/connect client {:db-name "bank"}))

  (d/delete-database client {:db-name "bank"})

  (d/transact conn {:tx-data bank-schema})

  (d/transact conn {:tx-data [{:account/id #uuid "a55756fd-0884-4b4e-afa7-b1bedbd5d5f8"
                                 :account/balance 1200.0
                                 :db/txInstant #inst "2021-12-05"}]})
  (d/as-of (d/db conn) #inst "2022-10-01")

  (d/since (d/db conn) 13194139533324)

  (d/history (d/db conn))

  (d/pull (d/as-of (d/db conn) #inst "2021-12-05") '[*]
          [:account/id #uuid "a55756fd-0884-4b4e-afa7-b1bedbd5d5f8"])

  (d/pull (d/db conn) '[*] [:account/id #uuid "a55756fd-0884-4b4e-afa7-b1bedbd5d5f8"])

  (d/q '[:find (pull ?e [*])
       :in $ $since
       :where
       [$ ?e :account/id]
       [$since ?e :account/balance ?qtde]]
     (d/db conn) (d/since (d/db conn) #inst "2022-12-04"))
  (d/q
   '[:find ?id ?balance ?tx
     :in $
     :where
     [?e :account/id ?id]
     [?e :account/balance ?balance]
     [?e :db/txInstant ?tx]]
   (d/history (d/db conn)))

  (d/q
   '[:find ?tx :where
     [?tx :db/txInstant]]
   (d/db conn))
  )