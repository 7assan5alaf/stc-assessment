SELECT u.user_id,p.username, u.training_date,COUNT(u.training_id)AS 'count'
FROM user_training u
JOIN person p ON u.user_id=p.user_id
GROUP BY u.user_id,u.training_id,u.training_date,p.username
HAVING  COUNT(u.training_id)>1
ORDER BY training_date DESC 
