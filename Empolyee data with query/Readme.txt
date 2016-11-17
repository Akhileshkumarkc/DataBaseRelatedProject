Instructions to run the program:
1. open commandprompt/terminal open the download folder.
2. open oracle console sqlplus

3. Execute to remove the previous tables .
    "start 7clean_company_schema.sql"
    (ignore errors)

4. create tables:
Execute "start 1company_schema.sql"
    (ignore errors)
5. load data using SQLLDR.
    exit the db.
    Change the dir to /data
    cd data.
    
    Execute loading data.//(change username and password below)
    SQLLDR CONTROL =employee_dataloader.ctl userid=system/abc123
    SQLLDR CONTROL =DepartmentDataloader.ctl userid=system/abc123
    SQLLDR CONTROL =dependentloader.ctl userid=system/abc123
    SQLLDR CONTROL =Project_dataloader.ctl userid=system/abc123
    SQLLDR CONTROL =works_ondataloader.ctl userid=system/abc123
    SQLLDR CONTROL =deptlocation.ctl userid=system/abc123

6. go back a dir 
    "cd.."
    and launch the SQLPlus(to continue)
    Log into SQLPLUS from the user and add constraints by executing.
    "Start 3.Company_constraints.sql"
7.Add empolyee data.
    Start five_new_employees.sql
    
8. Add views. 
    exit sqlplus.
    change dir to /view. cd view
    open sqlplus
    run 
    Start 5.company_view1.sql
    Start 5.company_view2.sql
    Start 5.company_view3.sql
    Start 5.company_view4.sql
    
9. Then run queries for views.
    Start 6.Company_view1_query1.sql
    Start 6.company_view2_query.sql
    Start 6.Company_view3_query.sql
    Start 6.Company_view4_query.sql

10. clear views, table and everything
    exit sqlplus
    change the directory one step back cd..
    login to sqlplus and run 
    "start 7clean_company_schema.sql"
    