-- 1. suspectInvestigation
CREATE PROCEDURE suspectsInvestigation()
BEGIN
	select id, name, surname from Suspect
    where lower(surname) LIKE 'gre_n'
    and lower(name) LIKE 'B%'
    and height <= 170
    order by id asc;
END
--------------------------------------------------------
-- 2. suspectInvestigation2
CREATE PROCEDURE suspectsInvestigation2()
BEGIN
    select id, name, surname from Suspect where id not in(
        	select id from Suspect
            where lower(surname) LIKE 'gre_n'
            and lower(name) LIKE 'b%'
            and height > 170
            order by id asc
    );
END

opt 2:
    select id, name, surname from Suspect where height <= 170 or left (name,1) not in ('B', 'b') or surname not like 'Gre_n' order by id;
-------------------------------------------------------
-- 3. securityBreach
CREATE PROCEDURE securityBreach()
BEGIN
    select first_name, second_name, attribute
    from users
    where attribute
    REGEXP binary CONCAT('^.{1,}(\%', first_name,'\_',second_name, '\%).{0,}$')
    order by attribute;
END

opt 2:
	select first_name, second_name, attribute from users where SUBSTRING(attribute, 2, CHARACTER_LENGTH(attribute))   like binary
concat("%", "\%", first_name, "\_", second_name, "\%", "%")
like binary - for case sensitive search
substring - from 2nd letter to end - as first character needs to be excluded
-----------------------------------------------------
-- 4. testCheck
CREATE PROCEDURE testCheck()
    SELECT id, IF (given_answer = correct_answer, "correct",
                   (if(given_answer is null, "no answer", "incorrect")))
                       AS checks
    FROM answers
    ORDER BY id;
-----------------------------------------------------
-- 5. expressionsVerification
CREATE PROCEDURE expressionsVerification()
BEGIN
	select * from expressions
    where
        (case
            when operation = '+' then (a + b)
            when operation = '-' then (a - b)
            when operation = '/' then (a / b)
            when operation = '*' then (a * b)
        END) = c;
END
---------------------------------------------------
-- 6. newsSubscribers
CREATE PROCEDURE newsSubscribers()
BEGIN
    select distinct subscriber from full_year
    where newspaper like "%Daily%"
    UNION
    select distinct subscriber from half_year
    where newspaper like "%Daily%"
    order by 1 asc;
END
