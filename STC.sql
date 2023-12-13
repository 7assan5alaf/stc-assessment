SELECT t.user_id,u.username,t.training_id,t.training_date,COUNT(t.training_id)AS 'count'
FROM user_training t
JOIN user u ON t.user_id=u.user_id
GROUP BY t.user_id,t.training_id,t.training_date,u.username
HAVING  COUNT(t.training_id)>1
ORDER BY t.training_date DESC 
