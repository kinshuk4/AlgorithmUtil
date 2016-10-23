SELECT
  ( @rank := @rank + 1 ) AS rank,
  v.name,
  v.votes
FROM votes v, ( SELECT @rank := 0 ) r_alias
ORDER BY v.votes DESC;