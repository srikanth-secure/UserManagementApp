INSERT INTO 
	COUNTRY_MASTER(COUNTRY_ID, COUNTRY_CODE, COUNTRY_NAME) 
VALUES 
	(1,'+91', 'India'),
	(2,'+61', 'Australia'),
	(3,'+1', 'USA');

INSERT INTO 
	STATES_MASTER(STATE_ID, STATE_NAME, COUNTRY_ID) 
VALUES 
	(1, 'Andhra Pradesh', 1),
	(2, 'Telangana', 1),
	(3, 'Victoria', 2),
	(4, 'Tasmania', 2);
	
INSERT INTO 
	CITIES_MASTER(CITY_ID, CITY_NAME, STATE_ID) 
VALUES 
	(1, 'Tirupati', 1),
	(2, 'Vizag', 1),
	(3, 'Hyderabad', 2),
	(4, 'Karimnagar', 2),
	(5, 'Melnourne', 3),
	(6, 'Geelong', 3),
	(7, 'Hobat', 4),
	(8, 'Launceston', 4);