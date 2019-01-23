CREATE SCHEMA `weather_app_db` ;

insert into state(state,country) 
values
('Maharashtra','India'),('Andhra Pradesh','India'),('Assam','India'),('Gujarat','India'),('Haryana','India');

insert into city(city,state_id) values
('Mumbai',1),('Pune',1),('Nagpur',1),('Surat',4),('Ahmedabad',4),('Vadodara',4);

insert into role(role_name) values ('USER');

insert into weather_data(date,description,tempreture,city_id)
values('2019-01-23','Partly Cloudy',23.3,1),
('2019-01-24','Partly Cloudy',26,1),
('2019-01-25','Breezy',20,1);