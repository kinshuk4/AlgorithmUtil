DROP TABLE IF EXISTS bugs;
--
CREATE TABLE bugs (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  open_date DATE NOT NULL,
  close_date DATE,
  severity CHAR( 1 ) NOT NULL ); -- L=LOW, M=MEDIUM, H=HIGH
--
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-23', 'L' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-23', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-23', 'L' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-23', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-23', '2016-10-23', 'L' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-23', '2016-10-29', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-23', '2016-10-28', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-24', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-24', '2016-10-24', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-24', '2016-10-25', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-24', '2016-10-27', 'M' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-25', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-25', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-25', '2016-10-25', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-25', '2016-10-26', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-25', '2016-10-27', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-25', '2016-10-28', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-25', '2016-10-29', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-26', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-26', '2016-10-27', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-27', '2016-10-27', 'L' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-26', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-27', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2016-10-28', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-28', '2016-11-05', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2016-10-29', '2016-11-05', 'H' );
--
-- Consider date range : 2016-10-25 to 2016-10-27. Can be converted to stored proc as well.
--set @date_start = '2016-10-25';
-- set @date_end = '2016-10-27';

SELECT open_date, COUNT( open_date )
FROM bugs WHERE open_date <= '2016-10-25' AND ( close_date > '2016-10-27' OR close_date IS NULL )
GROUP BY open_date
ORDER BY open_date;
--Output
-- +------------+--------------------+
-- | open_date  | COUNT( open_date ) |
-- +------------+--------------------+
-- | 2016-10-23 |                  8 |
-- | 2016-10-24 |                  2 |
-- | 2016-10-25 |                  8 |
-- +------------+--------------------+

