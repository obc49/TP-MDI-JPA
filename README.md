# TP final du module MDI
****Auteurs****

 **Oumar BALLO**
 
 **Roland KOUASSI**
 
 

### TP Backend (JPA) et Frontend (Vue.js)

## Fonctionnement de l'application

Notre application est une application servant à gerer des étudiants et des enseignants.
Elle permet donc d'enregistrer des étudiants ainsi que des enseignants. Lors de l'enregistrement 
d'un enseignant il est possible de selectionner un ou plusieurs étudiants qu'il doit enseigner.

Pour cela nous avons utilisé deux tables : Etudiants et Enseignants ayant une relation ManyToMany.
L'affichage des informations concernant un étudiant ou un enseignant se fait en cliquant sur le nom de
ce dernier. Lorsqu'on affiche les informations d'un enseignant, la liste des étudiants qu'il enseigne devrait 
s'afficher également mais cette partie est en développement.

le shema de notre modèle:


![class_diagram](./class_diagramm.png)





## Lancement de l'application

### Première méthode :

Pour lancer l'application, vous devez taper les commandes ci-dessous dans le répertoire racine du projet
Il faut également s'assurer que votre Docker local est en cours d'exécution.

- docker-compose up
- mvn clean spring-boot:run

Ensuite ouvrez votre navigateur et vous pourrez acceder à l'application par :

- localhost:8000

### Deuxième méthode :

- clonez le depot
- Installez mySql server 5.7
  
- Demarrez le serveur mysql
  systemctl start mysql
- Autorisez l'accès ou donnez les privilèges à l'utilisateur "root" avec le mot de passe "passsword" si ce n'est pas le cas
 GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'password';
 FLUSH PRIVILEGES;
 
- Executez l'application java Spring boot
- Accedez à l'application via le navigateur à l'adresse : localhost:8000



