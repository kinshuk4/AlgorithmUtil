-- 1. websiteHacking
CREATE PROCEDURE websiteHacking()
    SELECT id,login,name
    FROM users
    WHERE type='user'
    or type in (select distinct type from users)
    ORDER BY id
---------------------------------------------------
-- 2. nullIntern
CREATE PROCEDURE nullIntern()
BEGIN
    select count(*) as number_of_nulls
    from departments
    where description is null
    or trim(description) in ('null', 'nil', '-');
END

-- 3. legsCount
DROP PROCEDURE IF EXISTS legsCount;
CREATE PROCEDURE legsCount()
    SELECT sum(case
                when type = 'human' then 2
                when type = 'cat'   then 4
                when type = 'dog'   then 4
                else 0
               end
              )
    as summary_legs
    FROM creatures
    ORDER BY id;
-----------------------------------------------------
-- 4. combinationLock
CREATE PROCEDURE combinationLock()
BEGIN
    /**
    set @comb = 1;
    set @iter = 1;

    while @iter <= ((select max(id) from discs)) DO

        set @comb = @comb * (select length(characters)
                    from discs where id = @iter);

        set @iter = @iter + 1;
    END WHILE;

    select @comb as combinations;
    */
    select @c := @c * length(characters) as combinations
    from discs, (select @c := 1) m
    order by combinations desc
    limit 1; 
END
