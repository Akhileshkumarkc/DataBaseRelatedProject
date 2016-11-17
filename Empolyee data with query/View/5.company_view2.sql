--A view that has the employee name, supervisor name, and employee salary for each
--employee who works in the ‘Research’ department.

CREATE VIEW RESEARCHEMP(EMP_NAME,SUP_NAME,EMP_SALARY) AS(
SELECT E.FNAME||E.LNAME, S.FNAME||S.LNAME, E.SALARY FROM
EMPLOYEE E ,EMPLOYEE S
where E.super_ssn = S.SSN);
commit;
