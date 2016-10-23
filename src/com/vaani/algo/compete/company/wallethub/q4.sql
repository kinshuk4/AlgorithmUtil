CREATE TABLE bugs (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  open_date DATE NOT NULL,
  close_date DATE,
  severity CHAR( 1 ) NOT NULL ); -- L=LOW, M=MEDIUM, H=HIGH
--
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-01', 'L' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-01', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-01', '2012-01-01', 'L' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-01', '2012-01-07', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-01', '2012-01-06', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-02', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-02', '2012-01-02', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-02', '2012-01-03', 'M' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-02', '2012-01-05', 'M' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-03', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-03', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-03', '2012-01-03', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-03', '2012-01-04', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-03', '2012-01-05', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-03', '2012-01-06', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-03', '2012-01-07', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-04', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-04', '2012-01-05', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-05', '2012-01-05', 'L' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-04', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-05', 'H' );
INSERT INTO bugs ( open_date, severity ) VALUES ( '2012-01-06', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-06', '2012-01-14', 'H' );
INSERT INTO bugs ( open_date, close_date, severity ) VALUES ( '2012-01-07', '2012-01-14', 'H' );
--
-- I'm going to consider the range from 2012-01-03 to 2012-01-05
SELECT open_date, COUNT( open_date )
FROM bugs WHERE open_date <= '2012-01-03' AND ( close_date > '2012-01-05' OR close_date IS NULL )
GROUP BY open_date
ORDER BY open_date;
