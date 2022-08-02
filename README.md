# SimpleNotes

**Team members:** Lilai Yang, Peizu Li, Zuocheng Li

## Project Setup Requirements

<li> IDE: Elicpse Eclipse IDE for Enterprise Java and Web Developers: Version 2022-03 (4.23.0) or above (<a target="_blank" rel="noopener noreferrer" href="https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-enterprise-java-and-web-developers">Link</a>)<br>
<img width="783" alt="Screen Shot 2022-08-01 at 7 35 19 PM" src="https://user-images.githubusercontent.com/98489037/182279483-c68d0cfd-fa35-4afb-9278-c279bb8af6a1.png">

<li> Java version: Java 17 <br>
<img width="656" alt="Screen Shot 2022-08-01 at 7 36 54 PM" src="https://user-images.githubusercontent.com/98489037/182279662-9b722eef-cffc-4b16-8f70-d26752551805.png">

<li> JDBC Connector: ojdbc11.jar (<a href="https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html">Link</a>)

<li> Server: Apache Tomcat v10.0 (with ojdbc11.jar included in [tomcat server root file]/lib) (<a href="https://tomcat.apache.org/download-10.cgi">Link</a>) <br>
<img width="679" alt="Screen Shot 2022-08-01 at 7 44 44 PM" src="https://user-images.githubusercontent.com/98489037/182280774-2d949715-7d17-4557-b232-b3effd958bb0.png">

<li> Server: Create an Apache Tomcat server in Elicpse IDE <br>
<img width="585" alt="Screen Shot 2022-08-01 at 7 59 27 PM" src="https://user-images.githubusercontent.com/98489037/182282449-1b9d1ef8-2b5f-4c2c-ac71-3d08f481a934.png">

<li>[OPTIONAL] Server: Add <code>maxHttpHeaderSize="65536"</code> in server.xml (In case for import large file as note)
<img width="945" alt="Screen Shot 2022-08-01 at 8 03 24 PM" src="https://user-images.githubusercontent.com/98489037/182282884-6cd55751-6fe4-4cee-b8a6-1bd0888133b0.png">

<li>Docker: Using oracle 21c for this project (<a href="https://oralytics.com/2021/10/01/oracle-21c-xe-database-and-docker-setup/">Pull oracle 21c image reference</a>)<br>
<code>docker pull gvenzl/oracle-xe:21-full</code> <br>
<code>docker run -d -p 1521:1521 -e ORACLE_PASSWORD=SysPassword1 -v oracle-volume:/opt/oracle/XE21CFULL/oradata gvenzl/oracle-xe:21-full</code>

<li>Docker: Make sure docker is running while using this project <br>
<img width="975" alt="Screen Shot 2022-08-01 at 8 08 16 PM" src="https://user-images.githubusercontent.com/98489037/182283389-ef848063-7c1e-4994-b3dd-2182d00753de.png">

<li> There is issue on running oracle 21c when using Apple M1 chips. Therefore, the computer that is running this project should be using an Intel processor.

## 
