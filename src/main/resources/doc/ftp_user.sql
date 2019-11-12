create table ftp_user (
    userid			VARCHAR(64) Primary key,
    userpassword	VARCHAR(64),
    homedirectory	VARCHAR(128),
    enableflag		boolean	default TRUE,
    writepermission	boolean	default FALSE,
    idletime		INT	default 0,
    uploadrate		INT	default 0,
    downloadrate	INT	default 0,
    maxloginnumber	INT	default 0,
    maxloginperip	INT	default 0
);