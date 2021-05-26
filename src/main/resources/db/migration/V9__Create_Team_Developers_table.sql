create table Team_Developers (
    Team_ID integer REFERENCES Team (ID),
    Dev_ID integer REFERENCES Developers (ID)
);