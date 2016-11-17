How to run the file:
1.The first three files contains the schema.
1.university_schema_clean.sql
2.university_schema_Constraints.sql
3.university_schema.sql
Run this by logging into mysql with user name password
 ====================================================
 start 1.university_schema_clean.sql
 start 2.university_schema_Constraints.sql
 start 3.university_schema.sql
 ======================================
4.load data by running the bash file.
 =============================
 cd data
 sh load.sh
 ==============================
5.come back to the cd..
inside java\enrollstudent are the javafiles.
./lib has the jar ojdbc.jar
EnrollStudent.java is the program.
run the compiling. sh
javac -cp "./lib/ojdbc7.jar;" EnrollStudent.java
java -cp "./lib/ojdbc7.jar;" EnrollStudent
============================
cd java
cd enrollstudent 
sh compiling.sh
==========================
to run the java program.


Assumption:
1) instead of fall 2006,Spring 2007. i have used fall 2007, Spring 2008.
2) since we dont maintain any data in when the student was enrolled, the student will only be checked based on the 
student id staying within 30.
3) The program only enrolls for Spring 2008.
4)regarding how that data is built is in the notes.txt
 