create table Team_Developers (
    Team_ID integer NOT NULL,
    Dev_ID integer NOT NULL,
    FOREIGN KEY (Team_ID) REFERENCES Team (Id),
    FOREIGN KEY (Dev_ID) REFERENCES Developers (Id)
);

--FOREIGN KEY (Team_ID) REFERENCES Team (Id),
--FOREIGN KEY (Dev_ID) REFERENCES Developers (Id)