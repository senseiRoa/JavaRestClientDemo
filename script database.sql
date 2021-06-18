create database ItemTest
go
use ItemTest
go

create table Item
(
	id int identity primary key,
	title nvarchar(100),
	extra nvarchar(100),
	datestr nvarchar(20)
)

--insert into item values ('Sabado Sant','Religioso','2016-03-26')

select * from item