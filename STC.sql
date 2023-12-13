use User_Training
Select u.user_id,p.username, u.training_date,COUNT(u.training_id)as 'count'
from user_training u
join person p on u.user_id=p.user_id
Group By u.user_id,u.training_id,u.training_date,p.username
having  COUNT(u.training_id)>1
order by training_date desc 
