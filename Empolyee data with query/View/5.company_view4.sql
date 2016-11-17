--A view that has the project name, controlling department name, number of employees, and
--total hours worked per week on the project for each project with more than one employeeworking on it.

CREATE VIEW PROJREPORTM1 (PROJNAME,CTRLDEPTNAME,EMPOLYEENUM,HOURSPERWEEK) AS
(
SELECT p.Pname,d.DNAME,S.ESSNCOUNT,HOURSCOUNT FROM
(SELECT Count(WO.ESSN) AS ESSNCOUNT,SUM(WO.Hours) AS HOURSCOUNT, P.Pnumber as ProjNum FROM
DEPPROJECT P, WORKS_ON WO
Where P.Pnumber = WO.Pno
Group by P.Pnumber)S , department d, depproject p
where S.ProjNum =p.pnumber and p.dnum = d.Dnumber and ESSNCOUNT > 1);
commit;

