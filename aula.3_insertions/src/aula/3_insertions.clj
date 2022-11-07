(ns aula.3-insertions
  (:require [datomic.client.api :as d]))

;;; Defined earlier, but repeated for clarity
(def colors [:red :green :blue :yellow])
(def sizes [:small :medium :large :xlarge])
(def types [:shirt :pants :dress :hat])


[{:db/ident :vermelho}
 {:db/ident :preto}
 {:db/ident :branco}
 {:db/ident :verde}
 {:db/ident :roxo}
 {:db/ident :amarelo}
 {:db/ident :encarnado}
 {:db/ident :coral}
 {:db/ident :rosa}
 {:db/ident :azul}
 {:db/ident :mostadara}
 {:db/ident :magenta}]
(def brecho [{:db/ident :roupa/id
              :db/cardinality :db.cardinality/one
              :db/unique :db.unique/identity
              :db/valueType :db.type/string}
             {:db/ident :roupa/nome
              :db/cardinality :db.cardinality/one
              :db/valueType :db.type/string}
             {:db/ident :roupa/cor
              :db/cardinality :db.cardinality/one
              :db/valueType :db.type/ref}
             {:db/ident :roupa/tamanho
              :db/cardinality :db.cardinality/one
              :db/valueType :db.type/ref}
             {:db/ident :roupa/tipo
              :db/cardinality :db.cardinality/one
              :db/valueType :db.type/ref}
             {:db/ident :roupa/quantidade
              :db/cardinality :db.cardinality/one
              :db/valueType :db.type/long}])



(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "brecho"})

  (d/delete-database client {:db-name "brecho"})

  (def conn (d/connect client {:db-name "brecho"}))
  (def db (d/db conn))
  (d/q
   '[:find ?id ?quantidade
     :where
     [?roupa :roupa/id ?id]
     [?roupa :roupa/quantidade ?quantidade]]
   db))