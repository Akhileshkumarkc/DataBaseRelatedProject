--A view that has the department name, manager name, and manager salary for every
--department.

CREATE VIEW DepMngView(DepartmentName,MGR_name,MGR_SALARY) AS
(Select D.Dname ,E.FNAME||E.LNAME,E.salary FROM 
department D, employee E
Where D.Mgr_SSN = E.SSN);
commit;

