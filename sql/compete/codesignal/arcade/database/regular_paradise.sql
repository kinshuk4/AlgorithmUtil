-- 1. correctIPs
CREATE PROCEDURE correctIPs()
BEGIN
    SELECT id, ip
    FROM ips

    where IS_IPV4(ip)
    and (
         ip regexp '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{2,3}\.[0-9]{1,3}'  or
         ip regexp '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{2,3}'
        )

    order by id;
END
-----------------------------------------------------------------------
-- 2. validPhoneNumbers
CREATE PROCEDURE validPhoneNumbers()
BEGIN
	select *
    from phone_numbers
    where phone_number REGEXP '^((1-)|\\(1\\))[0-9]{3}-[0-9]{3}-[0-9]{4}$'
    order by surname;
END
