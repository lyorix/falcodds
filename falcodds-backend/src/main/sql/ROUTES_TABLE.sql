CREATE TABLE [ROUTES] ( 
	[ID] INTEGER PRIMARY KEY AUTOINCREMENT,
	[ORIGIN] TEXT NOT NULL,
	[DESTINATION] TEXT NOT NULL,
	[TRAVEL_TIME] INTEGER NOT NULL CHECK(TRAVEL_TIME>0),
	UNIQUE(ORIGIN,DESTINATION)
); 
