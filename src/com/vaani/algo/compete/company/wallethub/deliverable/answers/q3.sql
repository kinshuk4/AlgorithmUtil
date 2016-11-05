-- noinspection SqlDialectInspectionForFile
DROP TABLE IF EXISTS sometbl;
--
CREATE TABLE sometbl ( ID INT, NAME VARCHAR( 50 ) );
INSERT INTO sometbl VALUES
  (1, 'Smith'),
  (2, 'Julio|Jones|Falcons'),
  (3, 'White|Snow'),
  (4, 'Paint|It|Red'),
  (5, 'Green|Lantern'),
  (6, 'Brown|bag');
--
DROP PROCEDURE IF EXISTS column_to_row;
--
DELIMITER $
CREATE PROCEDURE column_to_row()
BEGIN
  DECLARE concluded BOOLEAN DEFAULT FALSE;
  DECLARE _id, _position, endAt INT;
  DECLARE _name VARCHAR( 50 );
  DECLARE _delim BOOLEAN DEFAULT FALSE;
  DECLARE sometbl_cursor CURSOR FOR SELECT ID, NAME FROM sometbl;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET concluded = TRUE;

--create a temp table
  DROP TEMPORARY TABLE IF EXISTS sometbl_tmp;
  CREATE TEMPORARY TABLE IF NOT EXISTS sometbl_tmp ( ID INT, NAME VARCHAR( 50 ) ) ENGINE=MEMORY;

--open the cursor
  OPEN sometbl_cursor;

  read_loop : LOOP
    FETCH sometbl_cursor INTO _id, _name;

    IF concluded THEN
      LEAVE read_loop;
    END IF;

	SET _position = 1;
    REPEAT
      SET endAt = LOCATE( char( 124 ), _name, _position );

      IF endAt = 0 THEN
        SET endAt = LENGTH( _name ) + 1;
        SET _delim = FALSE;
	  ELSE
        SET _delim = TRUE;
	  END IF;

      INSERT INTO sometbl_tmp( ID, NAME ) VALUES ( _id, SUBSTR( _name, _position, endAt - _position ) );

      SET _position = endAt + 1;
    UNTIL not _delim END REPEAT;
  END LOOP;

  CLOSE sometbl_cursor;

-- the answer we want
  SELECT ID, NAME from sometbl_tmp;
END $
DELIMITER ;
--
call column_to_row();
