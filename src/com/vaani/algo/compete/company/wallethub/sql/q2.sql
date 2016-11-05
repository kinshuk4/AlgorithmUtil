-- noinspection SqlDialectInspectionForFile
-- this solution loops through the string, char by char
-- checks if the current char is a space which is a char = 32
-- if so, set a flag to indicate the next letter occurrence as capital one
DROP FUNCTION IF EXISTS initcap;
--
DELIMITER $
CREATE FUNCTION initcap( str VARCHAR( 255 ) ) RETURNS VARCHAR( 255 )
BEGIN
  DECLARE _result VARCHAR( 255 ) DEFAULT '';
  DECLARE _index INT UNSIGNED DEFAULT 0;
  DECLARE _length INT UNSIGNED DEFAULT LENGTH( str );
  DECLARE _capitalize BOOLEAN DEFAULT TRUE;
  DECLARE _letter VARCHAR( 1 );

  IF str IS NULL THEN RETURN NULL;
  END IF;

  SET str = LOWER( str );

  REPEAT
    SET _index = _index + 1;
    SET _letter = SUBSTR( str, _index, 1 );

    IF _capitalize = TRUE THEN
      SET _letter = UPPER( _letter );
      SET _capitalize = FALSE;
    END IF;

    IF _letter = char( 32 ) THEN
	  SET _capitalize = TRUE;
    END IF;

	SET _result = CONCAT( _result, _letter );
  UNTIL _index > _length END REPEAT;

  RETURN _result ;
END $
DELIMITER ;
--
select initcap('UNITED states Of AmERIca') as capitalized;
select initcap('  UNITED   states Of  AmERIca   testing TEST tesTing ') as capitalized;
select initcap('') as capitalized;
select initcap(NULL) as capitalized;
