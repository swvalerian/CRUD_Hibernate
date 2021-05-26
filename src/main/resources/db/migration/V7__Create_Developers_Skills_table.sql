create table Developers_Skills (
    Dev_Skill_ID integer REFERENCES Developers (ID),
    Skill_ID integer REFERENCES Skills (ID)
);