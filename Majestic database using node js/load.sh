start=`date +%s`
time sqlldr CONTROL =majestic_data_loader.ctl userid=axk167131/axk167131
end=`date +%s`
runtime=$((end-start))
echo runtime
echo $runtime
echo start
echo $start
echo end
echo $end

