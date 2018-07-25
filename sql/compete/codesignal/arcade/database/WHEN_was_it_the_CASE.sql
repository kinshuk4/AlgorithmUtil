-- 1. orderOfSuccession
CREATE PROCEDURE orderOfSuccession()
BEGIN
	select concat(
                    (case
                        when gender = 'F' then 'Queen'
                        when gender = 'M' then 'King'
                     END),
                     ' ',
                     name) as name
    from Successors
    order by birthday asc;

END
---------------------------------------------------------------
-- 2. orderingEmails
CREATE PROCEDURE orderingEmails()
BEGIN
	select id, email_title,
        (
         case
           when size >= 1048576 then concat(FLOOR(size / 1048576), ' Mb')
           when size >= 1024      then concat(FLOOR(size / 1024), ' Kb')
           else '0 Kb'
         END
        ) as short_size
 from emails
 order by size desc;
END
-----------------------------------------------------------------
-- 3. placesOfInterest
CREATE PROCEDURE placesOfInterest()
BEGIN

   select t.country, sum(t.adventure_park) as adventure_park,
                     sum(t.golf) as golf,
                     sum(t.river_cruise) as river_cruise,
                     sum(t.kart_racing) as kart_racing
   from
    (select country,
        CASE
           when leisure_activity_type = 'Adventure park' then number_of_places else 0
        END as adventure_park,

        CASE
           when leisure_activity_type = 'Golf' then number_of_places else 0
        END as golf,

        CASE
           when leisure_activity_type = 'River cruise' then number_of_places else 0
        END as river_cruise,

       CASE
         when leisure_activity_type = 'Kart racing' then number_of_places else 0
       END as kart_racing
     from countryActivities
    ) t

   group by country;
END
-------------------------------------------------------------------------------
-- 4. soccerGameSeries
