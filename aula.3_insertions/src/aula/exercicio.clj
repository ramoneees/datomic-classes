(ns aula.exercicio 
  (:require [datomic.client.api :as d]))

(def genre [:pop :rock :mpb :bossa :reaggue :rap :sertanejo])


(defn make-idents
  [x]
  (mapv #(hash-map :db/ident %) x))

;; 1 - criar schema de album músical que contenha id (deve ser único), nome, artista
;; 2 - adicionar um (ou mais de um) album(s) ao nosso banco de dados
;; 3 - modificar nosso schema, adicionando o campo gênero (deve ser um "enum")
;; 4 - adicione gênero nos albuns já existentes
;; 5 - faça um retract, removendo um album ou um atributo
;; 6 - faça com que nome do álbum e artista sejam atributos obrigatórios (bônus)
;; 7 - faça uma query que liste todos os albuns (bônus 2)

(def album [{:db/ident :album/id
             :db/cardinality :db.cardinality/one
             :db/unique :db.unique/identity
             :db/valueType :db.type/long}
            {:db/ident :album/nome
             :db/cardinality :db.cardinality/one
             :db/valueType :db.type/string}
            {:db/ident :album/artista
             :db/cardinality :db.cardinality/one
             :db/valueType :db.type/string}])

(def album-atualizado [{:db/ident :album/genero
                        :db/cardinality :db.cardinality/one
                        :db/valueType :db.type/ref}])


(d/transact conn {:tx-data (make-idents genre)})

(d/transact conn {:tx-data [{:album/id 1
                             :album/nome "American Idiot"
                             :album/artista "Green Day"}
                            {:album/id 2
                             :album/nome "Amar Elo"
                             :album/artista "Emicida"}
                            {:album/id 3
                             :album/nome "Multitude"
                             :album/artista "Stromae"}]})


(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "albuns"})

  (d/delete-database client {:db-name "albuns"})

  (def conn (d/connect client {:db-name "albuns"}))
  
  (def db (d/db conn))
  
  (d/q
   '[:find ?id ?nome ?artista ?genero
     :where
     [?album :album/id ?id]
     [?album :album/nome ?nome]
     [?album :album/artista ?artista]
     [?album :album/genero ?genero]]
   
   db))

