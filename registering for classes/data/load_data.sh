#!/bin/bash
# My first scripts
sqlldr CONTROL =student_ldr.ctl userid=axk167131/axk167131
sqlldr CONTROL =course_ldr.ctl userid=axk167131/axk167131
sqlldr CONTROL =section_ldr.ctl userid=axk167131/axk167131
sqlldr CONTROL =grade_report_ldr.ctl userid=axk167131/axk167131
sqlldr CONTROL =prerequisite_ldr.ctl userid=axk167131/axk167131