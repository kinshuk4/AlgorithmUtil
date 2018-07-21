-- 1. countriesInfo
CREATE PROCEDURE countriesInfo()
BEGIN
	select count(name) as number, avg(population) as average,
    sum(population) as total
    from countries;
END
-------------------------------------------------------------
-- 2. itemCounts
CREATE PROCEDURE itemCounts()
BEGIN
	select item_name, item_type, count(item_name) as item_count
    from availableItems
    group by item_name, item_type
    order by item_type, item_name;
END
--------------------------------------------------------------
-- 3. usersByContinent
CREATE PROCEDURE usersByContinent()
BEGIN
	select continent, sum(users) as users
  from countries
  group by continent
  order by users desc;
END
--------------------------------------------------------------
-- 4. movieDirectors
CREATE PROCEDURE movieDirectors()
BEGIN
    select director
    from moviesInfo
    where year > 2000
    group by director
    having sum(oscars) > 2
    order by director;
END
-------------------------------------------------------------
-- 5. travelDiary
CREATE PROCEDURE travelDiary()
BEGIN
    select group_concat(distinct country
                       order by country asc separator ';') as countries
    from diary;
END
---------------------------------------------------------------
-- 6. soccerPlayers
CREATE PROCEDURE soccerPlayers()
BEGIN
	select group_concat(
                        CONCAT(first_name, ' ', surname, ' #', player_number)
                        order by player_number SEPARATOR '; '
                        ) as players
    from soccer_team;
END
----------------------------------------------------------------
-- 7. marketReport
CREATE PROCEDURE marketReport()
BEGIN
	select IF(country is not null, country, 'Total:') as country, sum(1) as competitors
  from foreignCompetitors
  group by country with rollup;

/* ANOTHER SOLUTION USING CASE
select (case
        when country is not null then country
        else 'Total:' 
        end) as country, sum(1) as competitors
from foreignCompetitors
group by country with rollup;
*/
END
