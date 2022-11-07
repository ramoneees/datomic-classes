(ns aula.exercicio)

(def genre [:pop :rock :mpb :bossa :reaggue :rap :sertanejo])


;; 1 - criar schema de album músical que contenha id (deve ser único), nome, artista
;; 2 - adicionar um (ou mais de um) album(s) ao nosso banco de dados
;; 3 - modificar nosso schema, adicionando o campo gênero (deve ser um "enum")
;; 4 - adicione gênero nos albuns já existentes
;; 5 - faça um retract, removendo um album ou um atributo
;; 6 - faça com que nome do álbum e artista sejam atributos obrigatórios (bônus)
;; 7 - faça uma query que liste todos os albuns (bônus 2)



(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))

  (d/create-database client {:db-name "recipes"})

  (d/delete-database client {:db-name "recipes"})

  (def conn (d/connect client {:db-name "recipes"})))

