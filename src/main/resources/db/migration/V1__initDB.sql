CREATE TABLE USERS (id bigint NOT null, uid UUID NOT null, username varchar(255), firstname varchar(255),fathername varchar(255),nationalid varchar(10),birthdate bigint, password Text, lastname varchar(255),email varchar(255),score integer,avatarhashcode varchar(255) ,isonline boolean,isactive boolean, creationdate bigint, modificationdate bigint, mobile_number varchar(255), PRIMARY KEY (id));
ALTER TABLE USERS ADD CONSTRAINT UK_46kvy05nqwkhpefsagvg5samv  UNIQUE (username);
ALTER TABLE USERS ADD CONSTRAINT UK_k3gm4drnorissx5s1hmnk99cj  UNIQUE (uid);
ALTER TABLE USERS ADD CONSTRAINT UK_68tlf4oult9ywhvk6dfmvf1r2  UNIQUE (mobile_number);

CREATE TABLE ROLES (id bigint NOT null, uid UUID NOT null, name varchar(255),userdefined boolean, description varchar(255), PRIMARY KEY (id));
ALTER TABLE ROLES ADD CONSTRAINT UK_hffrgdy49ff56f4w6t6pmj288  UNIQUE (uid);
ALTER TABLE ROLES ADD CONSTRAINT UK_1s6p3xpt8owdb603jky0mo815  UNIQUE (name);

CREATE TABLE Wallets(id bigint NOT null, uid UUID NOT null,value integer,bankAccountName varchar(255),bankAccountNumber varchar(255),bankAccountSheba  varchar(255));

CREATE TABLE Settings(id bigint NOT null, uid UUID NOT null,description varchar(255),userdefined boolean);

CREATE TABLE AccessEntries(id bigint NOT null, uid UUID NOT null,type integer ,name varchar(255),description varchar(255),creationdate bigint,modificationdate bigint,expirydate bigint  );

CREATE TABLE InvitedUsers(id bigint NOT null, uid UUID NOT null,invitedusername varchar(255),creationdate bigint);

CREATE TABLE AssignDiscounts(id bigint NOT null, uid UUID NOT null,isactive boolean,creationdate bigint,modificationdate bigint,expirydate bigint) ;

CREATE TABLE Devices(id bigint NOT null, uid UUID NOT null,ipaddress varchar(15),model varchar(255),imei varchar(20));

CREATE TABLE Reminders(id bigint NOT null, uid UUID NOT null,read varchar(255));

CREATE TABLE SharingPerspectives(id bigint NOT null, uid UUID NOT null, membershiptype varchar(255),creationdate bigint,modificationdate bigint,expirydate bigint);

CREATE TABLE Lawyers(id bigint NOT null, uid UUID NOT null,isavailable boolean,level integer);

CREATE TABLE Clients(id bigint NOT null, uid UUID NOT null,jobtitle varchar(255),address varchar(255),postalcode varchar(255),fieldofstudy varchar(255),tel bigint);

CREATE TABLE SettingTypes(id bigint NOT null, uid UUID NOT null,name varchar(255),description varchar(255),type integer);

CREATE TABLE DiscountPacks(id bigint NOT null, uid UUID NOT null,name varchar(255),value bigint,unit integer);

CREATE TABLE Notifications(id bigint NOT null, uid UUID NOT null,content varchar(255),targetUid varchar(255),type integer ,pushdate bigint,notifparentUid varchar(255) ,deleted boolean);

CREATE TABLE Bills(id bigint NOT null, uid UUID NOT null,transactionnumber varchar(255),trackingnumber varchar(255),transactiondate bigint,status varchar(255),value bigint,orgUid varchar(255) );

-- CREATE TABLE Features(id bigint NOT null, uid UUID NOT null,name varchar(255),description varchar(255),groupkey varchar(255) );

CREATE TABLE Files(id bigint NOT null, uid UUID NOT null,fileNumber varchar(255),title varchar(255),description text,creationdate bigint,modificationdate bigint);

CREATE TABLE OfficesAddress(id bigint NOT null, uid UUID NOT null,title varchar(255),address varchar(255),tel bigint,description varchar(255) );

CREATE TABLE PresenceSchedules(id bigint NOT null, uid UUID NOT null,description varchar(255),specialdate bigint,time bigint,weekday integer);

CREATE TABLE Expertises(id bigint NOT null, uid UUID NOT null,name varchar(255),description varchar(255) );

CREATE TABLE Docs(id bigint NOT null, uid UUID NOT null, checksum text,hashCode varchar(255),docType varchar(255),creationdate bigint);

CREATE TABLE Calls(id bigint NOT null, uid UUID NOT null,failedretriescount integer ,status varchar(255),calltype integer ,starttime bigint,endtime bigint,creationdate bigint);

CREATE TABLE Activities(id bigint NOT null, uid UUID NOT null,type integer ,title varchar(255),description varchar(255),creationdate bigint);

CREATE TABLE AcceptRequests(id bigint NOT null, uid UUID NOT null,acceptdate bigint,verified boolean,finished boolean);

CREATE TABLE Organizations(id bigint NOT null, uid UUID NOT null,name varchar(255),description varchar(255),address varchar(255),address varchar(255),terminalid bigint,username varchar(255),userPassword varchar(255),creationdate bigint,expirydate bigint,orgstock bigint,appstock bigint,verified boolean );

CREATE TABLE FailRequests(id bigint NOT null, uid UUID NOT null,creationdate bigint,description varchar(255));

CREATE TABLE Requests(id bigint NOT null, uid UUID NOT null,requestnumber varchar(255),description varchar(255),deleted boolean );

CREATE TABLE AdviceTypes(id bigint NOT null, uid UUID NOT null,parent bigint ,name varchar(255),description varchar(255),type varchar(255));

CREATE TABLE Packs(id bigint NOT null, uid UUID NOT null,name varchar(255),description varchar(255),value bigint ,isactive boolean);

CREATE TABLE ConsumptionPacks(id bigint NOT null, uid UUID NOT null,consumptiontime bigint,value bigint ,type integer ,firstinstallmentdate bigint,lastinstallmentdate bigint);

CREATE TABLE Installments(id bigint NOT null, uid UUID NOT null,installmentnumber integer, installmenttotalnumber integerm, creationdate bigint,value bigint);

CREATE TABLE Factors(id bigint NOT null, uid UUID NOT null,servicedescription varchar(255), clientname varchar(255),clientcode varchar(255),address varchar(255),tel bigint,postalcode varchar(255),factornumber varchar(255),creationdate bigint,value bigint,deleted boolean );

CREATE TABLE Questions(id bigint NOT null, uid UUID NOT null,title varchar(255),description varchar(255),type integer,creationdate bigint, edited boolean,modificationdate bigint);

CREATE TABLE Answers(id bigint NOT null, uid UUID NOT null,description varchar(255),creationdate bigint,edited boolean,modificationdate bigint);

CREATE TABLE Feedbacks(id bigint NOT null, uid UUID NOT null,creationdate bigint, description varchar(255),read boolean);

CREATE TABLE AdminConfirmations(id bigint NOT null, uid UUID NOT null,title varchar(255),description varchar(255),targetuid varchar(255), targettype integer ,verified boolean,deleted boolean,creationDate bigint);

CREATE TABLE Constants(id bigint NOT null, uid UUID NOT null,key varchar(255),value varchar(255),type varchar(255),description varchar(255));

CREATE TABLE RightMessages(id bigint NOT null, uid UUID NOT null,title varchar(255),description varchar (255),creationdate bigint,expirydate bigint,isactive boolean);

CREATE TABLE UserFeedbacks(id bigint NOT null, uid UUID NOT null,type integer ,title varchar(255),description varchar(255),creationdate bigint,read boolean );

CREATE TABLE Complains(id bigint NOT null, uid UUID NOT null,title varchar(255),description varchar(255),creationdate bigint,read boolean );

CREATE TABLE BlackLists(id bigint NOT null, uid UUID NOT null,creationdate bigint,sourceuid varchar(255),expirydate bigint,description varchar(255));

CREATE TABLE Logs(id bigint NOT null, uid UUID NOT null,creationdate bigint,message varchar(255),details varchar(255),sourceuid varchar(255),sourcetype integer ,targetuid varchar(255),targettype integer);

CREATE TABLE FEATURES (id bigint NOT null, uid UUID NOT null, name varchar(255), description varchar(255), created_at bigint, updated_at bigint, PRIMARY KEY (id));
ALTER TABLE FEATURES ADD CONSTRAINT UK_c8pkbrgxfl8ghr7b75hqr8eqv  UNIQUE (uid);
ALTER TABLE FEATURES ADD CONSTRAINT UK_qx1jf10esv72lwsb4d8xmbyh8  UNIQUE (name);

CREATE TABLE ROLE_FEATURES (role_id bigint NOT null, feature_id bigint NOT null, PRIMARY KEY (role_id,feature_id));
ALTER TABLE ROLE_FEATURES ADD CONSTRAINT FK_23kfmjd6lt9jgjcxsgp8nmtbp FOREIGN KEY (feature_id) REFERENCES FEATURES;
ALTER TABLE ROLE_FEATURES ADD CONSTRAINT FK_iyd1dkloxejnurxgx72c9pwvg FOREIGN KEY (role_id) REFERENCES ROLES;

CREATE TABLE USER_ROLES (role_id bigint NOT null, user_id bigint NOT null, PRIMARY KEY (user_id, role_id));
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_ixvn2eap6pna97ymmcm11narh FOREIGN KEY (user_id) REFERENCES USERS;
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_4vocb5yb32s4kl5a9hax6h4sa FOREIGN KEY (role_id) REFERENCES ROLES;