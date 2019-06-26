-- Author
-- Paul Yu

drop table if exists store;
drop table if exists coffee;
drop table if exists promotion;
drop table if exists memberLevel;
drop table if exists customer;
drop table if exists purchase;
drop table if exists offerCoffee;
drop table if exists hasPromotions;
drop table if exists promoteFor;
drop table if exists buyCoffee;

create table if not exists store
(
  Store_ID int,
  Name varchar(20),
  Address varchar(20),
  Store_Type varchar(20),
  GPS_Long float,
  GPS_Lat float,
  primary key (Store_ID)
);

create table if not exists coffee
(
  Coffee_ID int,
  Name varchar(20),
  Description varchar(20),
  Intensity int,
  Price float,
  Reward_Points float,
  Redeem_Points float,
  primary key (Coffee_ID)
);


create table if not exists promotion
(
  Promotion_ID int,
  Name varchar(20),
  Start_Date date,
  End_Date date,
  primary key (Promotion_ID)
);

create table if not exists memberLevel
(
  MemberLevel_ID int,
  Name varchar(20),
  Booster_Factor float,
  primary key (MemberLevel_ID)
);

create table if not exists customer
(
  Customer_ID int,
  First_Name varchar(20),
  Last_Name varchar(20),
  Email varchar(20),
  MemberLevel_ID int,
  Total_Points float,
  primary key (Customer_ID),
  foreign key (MemberLevel_ID) references memberlevel(MemberLevel_ID)
);

create table if not exists purchase
(
  Purchase_ID int,
  Customer_ID int,
  Store_ID int,
  Purchase_Time date,
  primary key (Purchase_ID),
  foreign key (Customer_ID) references customer(Customer_ID),
  foreign key (Store_ID) references store(Store_ID)
);

create table if not exists offerCoffee
(
  Store_ID int,
  Coffee_ID int,
  primary key (Store_ID, Coffee_ID),
  foreign key (Store_ID) references store(Store_ID),
  foreign key (Coffee_ID) references coffee(Coffee_ID)
);

create table if not exists hasPromotions
(
  Store_ID int,
  Promotion_ID int,
  primary key (Store_ID, Promotion_ID),
  foreign key (Store_ID) references store(Store_ID),
  foreign key (Promotion_ID) references promotion(Promotion_ID)
);

create table if not exists promoteFor
(
  Promotion_ID int,
  Coffee_ID int,
  primary key (Promotion_ID, Coffee_ID),
  foreign key (Promotion_ID) references promotion(Promotion_ID),
  foreign key (Coffee_ID) references coffee(Coffee_ID)
);

create table if not exists buyCoffee
(
  Purchase_ID int,
  Coffee_ID int,
  Purchase_Quantity int,
  Redeem_Quality int,
  primary key (Purchase_ID, Coffee_ID),
  foreign key (Purchase_ID) references purchase(Purchase_ID),
  foreign key (Coffee_ID) references coffee(Coffee_ID)
);