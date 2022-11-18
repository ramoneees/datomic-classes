(ns aula6-predicates.exercicio
  (:require [datomic.client.api :as d]))


;; 1 - criar query onde listam artistas que não incluem o nome "Chico"
;; 2 - listar albuns que tenham ano inferior a um ano colocado via parâmetro
;; 3 - retornar nomes de albuns todos em upper case 
;; 4 - retornar contagem de todos elementos do banco de dados.
;; 5 - limitar quantidade de resultados a 2 albuns (bonus)

(def genre [:pop :rock :mpb :bossa :reaggue :rap :sertanejo])

(def pattern-album
  [:album/id
   :album/name
   :album/release-year
   {:album/artist
    [:artist/name]}])

(defn make-idents
  [x]
  (mapv #(hash-map :db/ident %) x))
(def music-store [{:db/ident :artist/id
                   :db/cardinality :db.cardinality/one
                   :db/unique :db.unique/identity
                   :db/valueType :db.type/long}
                  {:db/ident :artist/name
                   :db/cardinality :db.cardinality/one
                   :db/valueType :db.type/string}

                  {:db/ident :album/id
                   :db/cardinality :db.cardinality/one
                   :db/unique :db.unique/identity
                   :db/valueType :db.type/long}
                  {:db/ident :album/name
                   :db/cardinality :db.cardinality/one
                   :db/valueType :db.type/string}
                  {:db/ident :album/artist
                   :db/cardinality :db.cardinality/many
                   :db/valueType :db.type/ref}
                  {:db/ident :album/release-year
                   :db/cardinality :db.cardinality/one
                   :db/valueType :db.type/long}
                  {:db/ident :album/music-genre
                   :db/cardinality :db.cardinality/one
                   :db/valueType :db.type/ref}])

(def artists [{:artist/id 1
               :artist/name "Fresno"}
              {:artist/id 2
               :artist/name "Gal Costa"}
              {:artist/id 3
               :artist/name "Milton 'Bituca' Nascimento"}
              {:artist/id 4
               :artist/name "Belchior"}
              {:artist/id 5
               :artist/name "Chico Buarque"}
              {:artist/id 6
               :artist/name "Silva"}])

(def more-artists
  [{:artist/id 7
    :artist/name "Chico Science"}])


(def albuns [{:album/id 1
              :album/name "A Sinfonia que em tudo há"
              :album/artist [:artist/id 1]
              :album/release-year 2017
              :album/music-genre :rock}
             {:album/id 10
              :album/name "Ciano"
              :album/artist [:artist/id 1]
              :album/release-year 2005
              :album/music-genre :rock}
             {:album/id 2
              :album/name "Construção"
              :album/artist [:artist/id 5]
              :album/release-year 1971
              :album/music-genre :mpb}
             {:album/id 3
              :album/release-year 1986
              :album/name "Caçador de mim"
              :album/artist [:artist/id 3]
              :album/music-genre :rock}])


(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "albuns"})

  (d/delete-database client {:db-name "albuns"})

  (def conn (d/connect client {:db-name "albuns"}))
  
  (d/transact conn {:tx-data albuns})
  
  (d/pull (d/db conn) [:artist/name
                       :album/_artist] [:artist/id 1])
  
  
  (def db (d/db conn)))