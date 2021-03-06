CREATE TABLE EMPLOYEE (
	 Fname VARCHAR2(20)
	,minit VARCHAR2(20)
	,Lname VARCHAR2(20)
	,SSN NUMBER(10)
	,BDATE DATE 
	,ADDRESS VARCHAR2(200)
	,SEX CHAR(1)
	,SALARY FLOAT(10) 
	,SUPER_SSN NUMBER(10)
	,Dno NUMBER(10)
	,PRIMARY KEY (SSN)
	) ;
 CREATE TABLE DEPARTMENT (
 Dname VarChar2(20)
 ,Dnumber NUMBER(10)
 ,Mgr_ssn NUMBER(10)
 ,Mgr_start_date DATE
 ,PRIMARY KEY(Dnumber)
 );
CREATE TABLE DEPT_LOCATIONS (
Dnumber NUMBER(10)
,Dlocation VARCHAR2(20)
,PRIMARY KEY (Dnumber,Dlocation)
);
CREATE TABLE DEPPROJECT (
pname VARCHAR2(20)
,pnumber NUMBER(10)
,plocation VARCHAR2(20)
,Dnum NUMBER(10)
,PRIMARY KEY (pnumber)
);
CREATE TABLE WORKS_ON (
ESSN NUMBER(10)
,Pno NUMBER(10)
,Hours NUMBER(10)
,PRIMARY KEY (ESSN,Pno)
);
CREATE TABLE DEPENDENT (
ESSN NUMBER(10)
,DEPENDENT_NAME VARCHAR2(20)
,SEX CHAR(1)
,BDATE DATE
,RELATIONSHIP VARCHAR(20) 
,PRIMARY KEY(ESSN,DEPENDENT_NAME)
);  

commit;
/** TODO: ON DELETE AND ON UPDATE CASCADES**/