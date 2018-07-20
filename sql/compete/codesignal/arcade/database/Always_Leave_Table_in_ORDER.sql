-- 1. volleyballResults
CREATE PROCEDURE volleyballResults()
BEGIN
    select * from results order by wins asc;
END
--------------------------------------------------
-- 2. mostExpensive
CREATE PROCEDURE mostExpensive()
BEGIN
    select min(name) as name
    from Products
    where (select max(quantity * price) from Products) = quantity * price;
END
---------------------------------------------------
-- 3. contestLeaderboard
CREATE PROCEDURE contestLeaderboard()
BEGIN
    set @counter = (select count(*) from
                   (select leaderboard.name
                    from leaderboard
                    order by leaderboard.score
                    desc
                    limit 5 offset 3
                   ) as dddd
                   );

   IF @counter <= 8 then
      select name
      from leaderboard
      order by score desc
      limit 5 offset 3;

    ELSE
        select name from leaderboard order by score desc;

end if;
END
----------------------------------------------------
-- 4. gradeDistribution
CREATE PROCEDURE gradeDistribution()
BEGIN
	select Name, ID from Grades
    where Final > (Midterm1 * 0.5) + (Midterm2 * 0.5)
    Order by Left(Name, 3), ID;
END
---------------------------------------------------------
-- 5. mischievousNephews
CREATE PROCEDURE mischievousNephews()
BEGIN
	select WEEKDAY(mischief_date) as weekday, mischief_date, author, title
    FROM mischief
    order by weekday asc,
             field(author, 'Huey','Dewey','Louie'),
             mischief_date,
             title asc;
END
