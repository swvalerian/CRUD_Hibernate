create table Developers_Skills (
    Dev_Skill_ID integer NOT NULL,
    Skill_ID integer NOT NULL,
    FOREIGN KEY (Dev_Skill_ID) REFERENCES Developers (id),
    FOREIGN KEY (Skill_ID) REFERENCES Skills (id)
);

--  ON DELETE CASCADE ON UPDATE CASCADE
-- ON DELETE CASCADE
-- ON DELETE CASCADE