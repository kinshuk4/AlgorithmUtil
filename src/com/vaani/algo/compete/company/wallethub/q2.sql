-- this solution loops through the entire string, char by char
-- checks if the current char is a space which is a char( 32 )
-- if so, set a flag to indicate the next letter occurrence as capital one
DROP FUNCTION IF EXISTS initcap;
--
DELIMITER $
CREATE FUNCTION initcap( str VARCHAR( 255 ) ) RETURNS VARCHAR( 255 )
BEGIN
  DECLARE _return VARCHAR( 255 ) DEFAULT '';
  DECLARE _position INT UNSIGNED DEFAULT 0;
  DECLARE _length INT UNSIGNED DEFAULT LENGTH( str );
  DECLARE capitalize BOOLEAN DEFAULT TRUE;
  DECLARE letter VARCHAR( 1 );

  IF str IS NULL THEN RETURN NULL;
  END IF;

  SET str = LOWER( str );

  REPEAT
    SET _position = _position + 1;
    SET letter = SUBSTR( str, _position, 1 );

    IF capitalize = TRUE THEN
      SET letter = UPPER( letter );
      SET capitalize = FALSE;
    END IF;

    IF letter = char( 32 ) THEN
	  SET capitalize = TRUE;
    END IF;

	SET _return = CONCAT( _return, letter );
  UNTIL _position > _length END REPEAT;

  RETURN _return ;
END $
DELIMITER ;
--
select initcap('UNITED states Of AmERIca') as capitalized;
select initcap('  UNITED   states Of  AmERIca   testing TEST tesTing ') as capitalized;
select initcap('') as capitalized;
select initcap(NULL) as capitalized;
