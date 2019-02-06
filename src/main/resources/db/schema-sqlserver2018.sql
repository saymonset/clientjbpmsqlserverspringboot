    create table SINGER (
        ID bigint identity not null,
        FIRST_NAME varchar(255),
        LAST_NAME varchar(255),
	    BIRTH_DATE DATE,
		VERSION INT NOT NULL DEFAULT 0,
        primary key (ID)
    );