-- 1. projectList
CREATE PROCEDURE projectList()
BEGIN
	select project_name, team_lead, income
  from Projects;
END
-------------------------------------------------------
-- 2. countriesSelection
CREATE PROCEDURE countriesSelection()
BEGIN
	select *
  from countries
  where continent = "Africa";
END
------------------------------------------------------
-- 3. monthlyScholarships
CREATE PROCEDURE monthlyScholarships()
BEGIN
	select id,  scholarship / 12 as scholarship
  from scholarships
  order by id;
END
-------------------------------------------------------
-- 4. projectsTeam
CREATE PROCEDURE projectsTeam()
BEGIN
	select distinct name
  from projectLog
  order by name asc;
END
-------------------------------------------------------
-- 5. automaticNotifications
CREATE PROCEDURE automaticNotifications()
    SELECT email
    FROM users
    WHERE role not like "%admin" and role not like "%premium"
    ORDER BY email;
