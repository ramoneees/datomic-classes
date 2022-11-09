(ns aula.exercicio
  (:require [datomic.client.api :as d]))

;; 1 - adicionar schema para termos artista como uma entidade separada com id e nome
;; 2 - criar query para listar albuns e seus artistas (uma query para cada)
;; 3 - criar query para listar albuns e seus artistas (album e o artista em uma só estrutura de mapa)
;; 4 - criar query para listar um album por nome
;; 5 - listar albuns por artista. (bônus)

;; referência -- https://docs.datomic.com/cloud/query/query-executing.html


(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))
  
  (d/create-database client {:db-name "albuns"})

  (d/delete-database client {:db-name "albuns"})

  (def conn (d/connect client {:db-name "albuns"}))
  (def db (d/db conn))
  )