-- 1. interestClubs
CREATE PROCEDURE interestClub()
    SELECT name
    FROM people_interests
    WHERE interests & 1 AND interests & 8
    ORDER BY name


-- 2. personalHobbies
CREATE PROCEDURE personalHobbies()
BEGIN
	SELECT name
    FROM people_hobbies
    WHERE hobbies & 1 AND hobbies & 2
    ORDER BY name;
END


-- 3. booksCatalogs
CREATE PROCEDURE booksCatalogs()
BEGIN
	SELECT ExtractValue(xml_doc, '/descendant-or-self::author[1]') AS author
    FROM catalogs
    ORDER BY author;
END

-- 4. habitatArea
CREATE PROCEDURE habitatArea()
BEGIN
	SET @g = (SELECT CONCAT('MULTIPOINT(', GROUP_CONCAT( CONCAT(x, ' ', y) SEPARATOR ','),')') FROM places);
    SELECT ST_AREA(ST_ConvexHull(ST_GeomFromText(@g))) AS area;
END
