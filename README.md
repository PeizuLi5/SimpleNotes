# SimpleNotes

**Team members:** Lilai Yang, Peizu Li, Zuocheng Li

## Project Setup Requirements

<li> IDE: Elicpse Eclipse IDE for Enterprise Java and Web Developers: Version 2022-03 (4.23.0) or above (<a href="https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-enterprise-java-and-web-developers">Link</a>)<br>
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

<li> After downloading the zip file and compressed it, remember to change the root file name to "SimpleNotes" if the display name is different. <br>

<li> Import project into the selected workspace in Eclipse <br>
<img width="558" alt="Screen Shot 2022-08-01 at 8 25 41 PM" src="https://user-images.githubusercontent.com/98489037/182285293-fb436382-6e40-472c-9fa5-fd27375d4217.png">
<img width="881" alt="Screen Shot 2022-08-01 at 8 27 06 PM" src="https://user-images.githubusercontent.com/98489037/182285409-f3a6a600-7d8b-445c-bd76-2412246cca72.png">

<li> Go to project property (SimpleNotes -> Properties)
<ol> 
  <li> Java Build Path: Modulepath -> Add JARs -> find ojdbc11.jar <br>
  <img width="1021" alt="Screen Shot 2022-08-01 at 8 46 02 PM" src="https://user-images.githubusercontent.com/98489037/182287427-371200f4-9c76-4f01-b67c-743341d4f959.png">
  <li> Java Build Path: JRE System Library -> Edit: Change JRE to JavaSE-17 if the current one is not <br>
  <img width="293" alt="Screen Shot 2022-08-01 at 8 47 15 PM" src="https://user-images.githubusercontent.com/98489037/182287542-655a2d35-6a13-405b-aad2-189b277f759b.png"> <br>
  <img width="879" alt="Screen Shot 2022-08-01 at 8 49 16 PM" src="https://user-images.githubusercontent.com/98489037/182287769-665f2599-a372-40bf-a858-39a034cd5d94.png">
 <li> Java Build Path: Modulepath -> Add Library: Add the Apache Tomcat Server Runtime if it does not exists in Build Path <br>
 <img width="1368" alt="Screen Shot 2022-08-01 at 8 52 10 PM" src="https://user-images.githubusercontent.com/98489037/182288090-975b0069-9de2-4b8d-a9f8-aca13782625b.png">
 <li> Project Facets: Making sure the Project Facets looks like this. (Dynamic Web Project Version 5.0)<br>
<img width="431" alt="Screen Shot 2022-08-01 at 8 54 38 PM" src="https://user-images.githubusercontent.com/98489037/182288364-cefb83d6-4f0d-450e-8f79-fb256108c2e0.png">
</ol>

## Division of Works
<li> <b>Proporsal</b>
<ol>
  <li><b>Project Idea Comfirmation</b>: Lilai Yang
  <li><b>Problem Statement</b>: Peizu Li
  <li><b>Application Requirments</b>: Lilai Yang
  <li><b>Functional Requirements & Database Requirements</b>: Zuocheng Li
</ol>
<li> <b>UI</b>
<ol>
  <li><b>Home Page</b>: Peizu Li
  <li><b>Login Page</b>: Lilai Yang
  <li><b>Register Page</b>: Peizu Li
  <li><b>Forget Password Pages</b>: Zuocheng Li
  <li><b>Main Page</b>: Lilai Yang, Peizu Li
  <li><b>Note Pages</b>: Peizu Li
  <li><b>Create Pages</b>: Peizu Li
  <li><b>Share Page</b>: Zuocheng Li
  <li><b>Modify Page</b>: Lilai Yang, Zuocheng Li
  <li><b>Setting Page</b>: Zuocheng Li
</ol>
<li> <b>Database</b>
<ol>
  <li><b>Home Page</b>: Peizu Li
  <li><b>Login Page</b>: Zuocheng Li
  <li><b>Register Page</b>: Peizu Li
  <li><b>Forget Password Pages</b>: Zuocheng Li, Peizu Li
  <li><b>Main Page</b>: Lilai Yang, Peizu Li
  <li><b>Note Pages</b>: Peizu Li
  <li><b>Create Pages</b>: Peizu Li
  <li><b>Share Page</b>: Lilai Yang
  <li><b>Modify Page</b>: Peizu Li
  <li><b>Setting Page</b>: Zuocheng Li
</ol>
<li> <b>Readme/Division of Works</b>: Peizu Li
<li> <b>Presentation</b>: Lilai Yang
<li> <b>Final Report</b>: Lilai Yang, Peizu Li, Zuocheng Li
