#User Guidance
___
To setup "Balda" application need to follow steps below.

###Necessary components
For work of application need to have installed [JDK](https://www.oracle.com/java/technologies/javase-downloads.html#javasejdk) (11 or above version), [PostgreSQL](https://www.postgresql.org/download/) (12 or above) and [IntellijIDEA](https://www.jetbrains.com/ru-ru/idea/) (Doesn't matter).  
When **JDK** installed, go to __File__ &#8594; __Progect Structure__ &#8594; __Platform Settings__ &#8594; __JDKs__, click __+__ button, select JDK and choose path of installed JDK, then go to 
__Project Settings__ &#8594; __Project__ in that window and choose created JDK in __Project SDK__ field and press "**Ok**".

###Installation
To install application on computer choose a folder,
open command window there and enter a command `git clone <repo_link>`.
  
###Open in *IntellijIDEA*
After successful downloading, open that project in *IntellijIDEA*.
As soon as project will be open, enable auto-import of *Maven* dependencies and they will be downloading. Some of them:
Spring Boot, Spring JPA, PostgreSQl JDBC Driver, SockJS, STOMP WebSockets and e.t.c.
This process can take some time.  
#####Edit Configurations
Press __Edit Configurations__ &#8594; __+__ button &#8594; __Application__ and select **Main class**,
if JDK is installed the "*Application*" appears in __Main class__ field, 
specify it and then press __OK__.

###DataBase configuration
Now need to configure DB connection. For this purpose *PostgreSQL* 
and it's JDBC driver must be downloaded. JDBC driver have already downloaded as *Maven* 
dependency.
For establishing connection to DB go to __View__ &#8594; __Tool Windows__ &#8594; 
__Database__ or __DB Browser__. In appeared window click __+__ button and choose __Data Source__ &#8594; __PostgreSQL__. 
Specify proper data in  appropriate fields and click __Test Connection__. In successful case 
connection will be done and corresponding text will shown, after that click __Ok__.   
  
DB is almost configured but need to edit _application.properties_ file:  
In file "application.properties" specify data:  
`sever.port = 8080`  
`spring.datasource.url=jdbc:postgresql://localhost:5432/BaldaDB`  
`spring.datasource.username=<name_of_user>`  
`spring.datasource.password=<password_of_that_user>`  
`spring.jpa.show-sql=true`  
`spring.jpa.hibernate.ddl-auto=create`  
And add line (I forgot add it in this file): `spring.session.jdbc.initialize-schema: always` - 
this command is responsible for creating _spring_session_ and 
_spring_session_attributes_ tables in DB for properly working of Spring Application.
Instead of `<name_of_user>` and `<password_of_that_user>` paste name of your DB user and it's password


___

###Bad situations
If error occurred with DB, try to create empty DataBase by hands in _pgAdmin_ or _PSQL Shell_ of _PostgreSQL_ with name "BaldaDB"