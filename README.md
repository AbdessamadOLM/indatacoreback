## Application pour peupler une base de donné (MySql) à partir d'un fichier excel. et traiter des apis.

Java 11
package war

#Prérequis:
#=> configuration de votre serveur MySql dans le fichier Properties. (
#spring.datasource.url=jdbc:mysql://localhost:<port>/indatacore (default 3306)
#spring.datasource.username=votre_username (default root)
#spring.datasource.password=votre_password 


#Tournez l'application
#****************************************** Api1 ***********************************************
#=> Api1: API "GET" retournant une liste des objets en format JSON
#--> http://localhost:8081/api/user/all
#******************************************* Api2 **********************************************
#=> Api2: API "POST" offrant la possibilité d'ajouter une 1 objet à la base de données en le passant en paramètre.
#--> http://localhost:8081/api/user/add-user
#body: 
#    {
 #   "userName": "Ahmed_Joe",
#    "email": "Ahmed@example.com",
 #   "firstName": "Ahmed",
 #   "lastName": "Joe"
 # }
#****************************************** Api3 ***********************************************
#=> Api3: API "POST" offrant la possibilité d'ajouter une 1 objet à la base de données en générant des valeurs aléatoires
#--> http://localhost:8081/api/user/generate-user

#***************************************** Swagger *********************************************
#http://localhost:8081/sawgger-ui/index.html
