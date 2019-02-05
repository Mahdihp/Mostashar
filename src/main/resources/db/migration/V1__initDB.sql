-- --------------------------CREATE SEQUENCE----------------------------
CREATE SEQUENCE USERS_ID_SEQ START 1;
CREATE SEQUENCE USERPOPULARITY_ID_SEQ START 1;
CREATE SEQUENCE ROLES_ID_SEQ START 1;
CREATE SEQUENCE FEATURES_ID_SEQ START 1;
CREATE SEQUENCE INVITEDUSERS_ID_SEQ START 1;
CREATE SEQUENCE DEVICES_ID_SEQ START 1;
CREATE SEQUENCE ACCESSENTRIES_ID_SEQ START 1;
CREATE SEQUENCE DISCOUNTPACKS_ID_SEQ START 1;
CREATE SEQUENCE ASSIGNDISCOUNTS_ID_SEQ START 1;
CREATE SEQUENCE COMPLAINS_ID_SEQ START 1;
CREATE SEQUENCE SETTINGTYPES_ID_SEQ START 1;
CREATE SEQUENCE SETTINGS_ID_SEQ START 1;
CREATE SEQUENCE NOTIFICATIONS_ID_SEQ START 1;
CREATE SEQUENCE REMINDERS_ID_SEQ START 1;
CREATE SEQUENCE WALLETS_ID_SEQ START 1;
CREATE SEQUENCE FACTORS_ID_SEQ START 1;
CREATE SEQUENCE BILLS_ID_SEQ START 1;
CREATE SEQUENCE CLIENTS_ID_SEQ START 1;
CREATE SEQUENCE LAWYERS_ID_SEQ START 1;
CREATE SEQUENCE SHARINGPERSPECTIVES_ID_SEQ START 1;
CREATE SEQUENCE CALLS_ID_SEQ START 1;
CREATE SEQUENCE FILES_ID_SEQ START 1;
CREATE SEQUENCE REQUESTS_ID_SEQ START 1;
CREATE SEQUENCE DOCS_ID_SEQ START 1;
CREATE SEQUENCE ACTIVITIES_ID_SEQ START 1;
CREATE SEQUENCE INSTALLMENTS_ID_SEQ START 1;
CREATE SEQUENCE CONSUMPTIONPACKS_ID_SEQ START 1;
CREATE SEQUENCE PACKS_ID_SEQ START 1;
CREATE SEQUENCE ADVICETYPES_ID_SEQ START 1;
CREATE SEQUENCE FAILREQUESTS_ID_SEQ START 1;
CREATE SEQUENCE ACCEPTREQUESTS_ID_SEQ START 1;
CREATE SEQUENCE EXPERTISES_ID_SEQ START 1;
CREATE SEQUENCE OFFICESADDRESS_ID_SEQ START 1;
CREATE SEQUENCE PRESENCESCHEDULES_ID_SEQ START 1;
CREATE SEQUENCE ORGANIZATIONS_ID_SEQ START 1;
CREATE SEQUENCE FEEDBACKS_ID_SEQ START 1;
CREATE SEQUENCE QUESTIONS_ID_SEQ START 1;
CREATE SEQUENCE ANSWERS_ID_SEQ START 1;
CREATE SEQUENCE ADMINCONFIRMATIONS_ID_SEQ START 1;
CREATE SEQUENCE CONSTANTS_ID_SEQ START 1;
CREATE SEQUENCE RIGHTMESSAGES_ID_SEQ START 1;
CREATE SEQUENCE USERFEEDBACKS_ID_SEQ START 1;
CREATE SEQUENCE BLACKLISTS_ID_SEQ START 1;
CREATE SEQUENCE LOGS_ID_SEQ START 1;

CREATE TABLE Users(id bigint NOT NULL DEFAULT nextval('USERS_ID_SEQ'::regclass), uid UUID NOT NULL, WalletId bigint, username varchar(255), firstname varchar(255), fathername varchar(255), nationalid varchar(10), birthdate bigint, password Text, lastname varchar(255), email varchar(255), score integer, avatarhashcode varchar(255), isonline boolean, isactive boolean, creationdate bigint, modificationdate bigint, mobilenumber bigint NOT NULL,  verificationcode varchar(5), UNIQUE(username), UNIQUE(uid), UNIQUE(mobileNumber), PRIMARY KEY(id));
CREATE TABLE Clients(id bigint NOT NULL, jobtitle varchar(255), address varchar(255), postalcode varchar(255), fieldofstudy varchar(255), tel bigint, UNIQUE(id));
CREATE TABLE Lawyers(id bigint NOT NULL, OrganizationId bigint, AdviceTypeId bigint, FailRequestId bigint, isavailable boolean, level integer, UNIQUE(id));
CREATE TABLE UserPopularity(id bigint NOT NULL DEFAULT nextval('USERPOPULARITY_ID_SEQ'::regclass),UserId bigint NOT NULL,UserPopularId bigint NOT NULL);

CREATE TABLE Roles(id bigint NOT NULL DEFAULT nextval('ROLES_ID_SEQ'::regclass), uid UUID NOT NULL, name varchar(255), userdefined boolean, description varchar(255), UNIQUE(uid), UNIQUE(name), PRIMARY KEY(id));
CREATE TABLE Features(id bigint NOT NULL DEFAULT nextval('FEATURES_ID_SEQ'::regclass), uid UUID NOT NULL, name varchar(255), description varchar(255), groupkey varchar(255), PRIMARY KEY(id));
CREATE TABLE InvitedUsers(id bigint NOT NULL DEFAULT nextval('INVITEDUSERS_ID_SEQ'::regclass), UserId bigint, uid UUID NOT NULL, invitedusername varchar(255), creationdate bigint, PRIMARY KEY(id));
CREATE TABLE Devices(id bigint NOT NULL DEFAULT nextval('DEVICES_ID_SEQ'::regclass), uid UUID NOT NULL, UserId bigint, ipaddress varchar(15), model varchar(255), fireBaseRegId varchar(255), imei varchar(20), PRIMARY KEY(id));
CREATE TABLE AccessEntries(id bigint NOT NULL DEFAULT nextval('ACCESSENTRIES_ID_SEQ'::regclass), uid UUID NOT NULL, UserId bigint, type integer, name varchar(255), description varchar(255), creationdate bigint, modificationdate bigint, expirydate bigint, PRIMARY KEY(id));
CREATE TABLE DiscountPacks(id bigint NOT NULL DEFAULT nextval('DISCOUNTPACKS_ID_SEQ'::regclass), uid UUID NOT NULL, name varchar(255), value bigint, unit integer, PRIMARY KEY(id));
CREATE TABLE AssignDiscounts(id bigint NOT NULL DEFAULT nextval('ASSIGNDISCOUNTS_ID_SEQ'::regclass), uid UUID NOT NULL, UserId bigint, DiscountPackId bigint, isactive boolean, creationdate bigint, modificationdate bigint, expirydate bigint, PRIMARY KEY(id));
CREATE TABLE Complains(id bigint NOT NULL DEFAULT nextval('COMPLAINS_ID_SEQ'::regclass), uid UUID NOT NULL, UserId bigint, title varchar(255), description varchar(255), creationdate bigint, read boolean, PRIMARY KEY(id));
CREATE TABLE SettingTypes(id bigint NOT NULL DEFAULT nextval('SETTINGTYPES_ID_SEQ'::regclass), uid UUID NOT NULL, name varchar(255), description varchar(255), type integer, PRIMARY KEY(id));
CREATE TABLE Settings(id bigint NOT NULL DEFAULT nextval('SETTINGS_ID_SEQ'::regclass), uid UUID NOT NULL, UserId bigint, SettingTypeId bigint, description varchar(255), userdefined boolean, PRIMARY KEY(id));
CREATE TABLE Notifications(id bigint NOT NULL DEFAULT nextval('NOTIFICATIONS_ID_SEQ'::regclass), uid UUID NOT NULL, content varchar(255), targetUid varchar(255), type integer, pushdate bigint, notifparentUid varchar(255), deleted boolean, PRIMARY KEY(id));
CREATE TABLE Reminders(id bigint NOT NULL DEFAULT nextval('REMINDERS_ID_SEQ'::regclass), uid UUID NOT NULL, UserId bigint, NotificationId bigint, read varchar(255), PRIMARY KEY(id));
CREATE TABLE Wallets(id bigint NOT NULL DEFAULT nextval('WALLETS_ID_SEQ'::regclass), uid UUID NOT NULL, value integer, bankAccountName varchar(255), bankAccountNumber varchar(255), bankAccountSheba  varchar(255), PRIMARY KEY(id));
CREATE TABLE Factors(id bigint NOT NULL DEFAULT nextval('FACTORS_ID_SEQ'::regclass), uid UUID NOT NULL, BillId bigint, servicedescription varchar(255), clientname varchar(255), clientcode varchar(255), address varchar(255), tel bigint, postalcode varchar(255), factornumber varchar(255), creationdate bigint, value bigint, deleted boolean, PRIMARY KEY(id));
CREATE TABLE Bills(id bigint NOT NULL DEFAULT nextval('BILLS_ID_SEQ'::regclass), uid UUID NOT NULL, WalletId bigint, transactionnumber varchar(255), trackingnumber varchar(255), transactiondate bigint, status varchar(255), value bigint, orguid varchar(255), PRIMARY KEY(id));
CREATE TABLE SharingPerspectives(id bigint NOT NULL DEFAULT nextval('SHARINGPERSPECTIVES_ID_SEQ'::regclass), uid UUID NOT NULL, UserId bigint, FileId bigint, membershiptype varchar(255), creationdate bigint, modificationdate bigint, expirydate bigint, PRIMARY KEY(id));
CREATE TABLE Calls(id bigint NOT NULL DEFAULT nextval('CALLS_ID_SEQ'::regclass), uid UUID NOT NULL, RequestId bigint, LawyerId bigint, ClientId bigint, DocId bigint, failedretriescount integer, status varchar(255), calltype integer, starttime bigint, endtime bigint, creationdate bigint, PRIMARY KEY(id));
CREATE TABLE Files(id bigint NOT NULL DEFAULT nextval('FILES_ID_SEQ'::regclass), uid UUID NOT NULL, ClientId bigint, fileNumber varchar(255), title varchar(255), description text, creationdate bigint, modificationdate bigint, PRIMARY KEY(id));
CREATE TABLE Requests(id bigint NOT NULL DEFAULT nextval('REQUESTS_ID_SEQ'::regclass), uid UUID NOT NULL, AdviceTypeId bigint, ClientId bigint, FileId bigint, requestnumber varchar(255), description varchar(255), deleted boolean, PRIMARY KEY(id));
CREATE TABLE Docs(id bigint NOT NULL DEFAULT nextval('DOCS_ID_SEQ'::regclass), uid UUID NOT NULL, LawyerId bigint, FileId bigint, checksum text, hashCode varchar(255), docType varchar(255), creationdate bigint, PRIMARY KEY(id));
CREATE TABLE Activities(id bigint NOT NULL DEFAULT nextval('ACTIVITIES_ID_SEQ'::regclass), uid UUID NOT NULL, LawyerId bigint, DocId bigint, FileId bigint, type integer, title varchar(255), description varchar(255), creationdate bigint, PRIMARY KEY(id));
CREATE TABLE Installments(id bigint NOT NULL DEFAULT nextval('INSTALLMENTS_ID_SEQ'::regclass), uid UUID NOT NULL, FactorId bigint, ConsumptionPackId bigint, installmentnumber integer, installmenttotalnumber integer, creationdate bigint, value bigint, PRIMARY KEY(id));
CREATE TABLE ConsumptionPacks(id bigint NOT NULL DEFAULT nextval('CONSUMPTIONPACKS_ID_SEQ'::regclass), uid UUID NOT NULL, PackId bigint, RequestId bigint, consumptiontime bigint, value bigint, type integer, firstinstallmentdate bigint, lastinstallmentdate bigint, PRIMARY KEY(id));
CREATE TABLE Packs(id bigint NOT NULL DEFAULT nextval('PACKS_ID_SEQ'::regclass), uid UUID NOT NULL, AdviceTypeId bigint, name varchar(255), description varchar(255), value bigint, isactive boolean, PRIMARY KEY(id));
CREATE TABLE AdviceTypes(id bigint NOT NULL DEFAULT nextval('ADVICETYPES_ID_SEQ'::regclass), uid UUID NOT NULL, AdviceTypeId bigint, name varchar(255), description varchar(255), type varchar(255), PRIMARY KEY(id));
CREATE TABLE FailRequests(id bigint NOT NULL DEFAULT nextval('FAILREQUESTS_ID_SEQ'::regclass), uid UUID NOT NULL, RequestId bigint, creationdate bigint, description varchar(255), PRIMARY KEY(id));
CREATE TABLE AcceptRequests(id bigint NOT NULL DEFAULT nextval('ACCEPTREQUESTS_ID_SEQ'::regclass), uid UUID NOT NULL, LawyerId bigint, RequestId bigint, acceptdate bigint, verified boolean, finished boolean, PRIMARY KEY(id));
CREATE TABLE Expertises(id bigint NOT NULL DEFAULT nextval('EXPERTISES_ID_SEQ'::regclass), uid UUID NOT NULL, name varchar(255), description varchar(255), PRIMARY KEY(id));
CREATE TABLE OfficesAddress(id bigint NOT NULL DEFAULT nextval('OFFICESADDRESS_ID_SEQ'::regclass), uid UUID NOT NULL, LawyerId bigint, title varchar(255), address varchar(255), tel bigint, description varchar(255), PRIMARY KEY(id));
CREATE TABLE PresenceSchedules(id bigint NOT NULL DEFAULT nextval('PRESENCESCHEDULES_ID_SEQ'::regclass), uid UUID NOT NULL, OfficesAddressId bigint, LawyerId bigint, description varchar(255), specialdate bigint, time bigint, weekday integer, PRIMARY KEY(id));
CREATE TABLE Organizations(id bigint NOT NULL DEFAULT nextval('ORGANIZATIONS_ID_SEQ'::regclass), uid UUID NOT NULL, WalletId bigint, name varchar(255), description varchar(255), address varchar(255), tel bigint, terminalid bigint, username varchar(255), userPassword varchar(255), creationdate bigint, expirydate bigint, orgstock bigint, appstock bigint, verified boolean, PRIMARY KEY(id));
CREATE TABLE Feedbacks(id bigint NOT NULL DEFAULT nextval('FEEDBACKS_ID_SEQ'::regclass), uid UUID NOT NULL, ClientId bigint, RequestId bigint, creationdate bigint, description varchar(255), read boolean, PRIMARY KEY(id));
CREATE TABLE Questions(id bigint NOT NULL DEFAULT nextval('QUESTIONS_ID_SEQ'::regclass), uid UUID NOT NULL, ClientId bigint, title varchar(255), description varchar(255), type integer, creationdate bigint, edited boolean, modificationdate bigint, PRIMARY KEY(id));
CREATE TABLE Answers(id bigint NOT NULL DEFAULT nextval('ANSWERS_ID_SEQ'::regclass), uid UUID NOT NULL, QuestionId bigint, LawyerId bigint, description varchar(255), creationdate bigint, edited boolean, modificationdate bigint, PRIMARY KEY(id));

-- ------------------------------------Other Table--------------------------------
CREATE TABLE AdminConfirmations(id bigint NOT NULL DEFAULT nextval('ADMINCONFIRMATIONS_ID_SEQ'::regclass), uid UUID NOT NULL, title varchar(255), description varchar(255), targetuid varchar(255), targettype integer, verified boolean, deleted boolean, creationDate bigint, PRIMARY KEY(id));
CREATE TABLE Constants(id bigint NOT NULL DEFAULT nextval('CONSTANTS_ID_SEQ'::regclass), uid UUID NOT NULL, key varchar(255), value varchar(255), type varchar(255), description varchar(255), PRIMARY KEY(id));
CREATE TABLE RightMessages(id bigint NOT NULL DEFAULT nextval('RIGHTMESSAGES_ID_SEQ'::regclass), uid UUID NOT NULL, title varchar(255), description varchar(255), creationdate bigint, expirydate bigint, isactive boolean, PRIMARY KEY(id));
CREATE TABLE UserFeedbacks(id bigint NOT NULL DEFAULT nextval('USERFEEDBACKS_ID_SEQ'::regclass), uid UUID NOT NULL, type integer, title varchar(255), description varchar(255), creationdate bigint, read boolean, PRIMARY KEY(id));
CREATE TABLE BlackLists(id bigint NOT NULL DEFAULT nextval('BLACKLISTS_ID_SEQ'::regclass), uid UUID NOT NULL, creationdate bigint, sourceuid varchar(255), expirydate bigint, description varchar(255), PRIMARY KEY(id));
CREATE TABLE Logs(id bigint NOT NULL DEFAULT nextval('LOGS_ID_SEQ'::regclass), uid UUID NOT NULL, creationdate bigint, message varchar(255), details varchar(255), sourceuid varchar(255), sourcetype integer, targetuid varchar(255), targettype integer, PRIMARY KEY(id));

-- -----------------------------Relation Table--------------------------------
CREATE TABLE User_Role(UserId bigint, RoleId bigint, UNIQUE(UserId), UNIQUE(RoleId));
CREATE TABLE Role_Feature(RoleId bigint, FeatureId bigint, UNIQUE(RoleId), UNIQUE(FeatureId));
CREATE TABLE Lawyer_Expertise(LawyerId bigint,ExpertiseId bigint, UNIQUE(ExpertiseId), UNIQUE(LawyerId));

-- --------------------ALTER SEQUENCE-------------------------------------
ALTER SEQUENCE USERS_ID_SEQ OWNED BY Users.id;
ALTER SEQUENCE USERPOPULARITY_ID_SEQ OWNED BY UserPopularity.id;
ALTER SEQUENCE ROLES_ID_SEQ OWNED BY Roles.id;
ALTER SEQUENCE FEATURES_ID_SEQ OWNED BY Features.id;
ALTER SEQUENCE INVITEDUSERS_ID_SEQ OWNED BY InvitedUsers.id;
ALTER SEQUENCE DEVICES_ID_SEQ OWNED BY Devices.id;
ALTER SEQUENCE ACCESSENTRIES_ID_SEQ OWNED BY AccessEntries.id;
ALTER SEQUENCE DISCOUNTPACKS_ID_SEQ OWNED BY DiscountPacks.id;
ALTER SEQUENCE ASSIGNDISCOUNTS_ID_SEQ OWNED BY AssignDiscounts.id;
ALTER SEQUENCE COMPLAINS_ID_SEQ OWNED BY Complains.id;
ALTER SEQUENCE SETTINGTYPES_ID_SEQ OWNED BY SettingTypes.id;
ALTER SEQUENCE SETTINGS_ID_SEQ OWNED BY Settings.id;
ALTER SEQUENCE NOTIFICATIONS_ID_SEQ OWNED BY Notifications.id;
ALTER SEQUENCE REMINDERS_ID_SEQ OWNED BY Reminders.id;
ALTER SEQUENCE WALLETS_ID_SEQ OWNED BY Wallets.id;
ALTER SEQUENCE FACTORS_ID_SEQ OWNED BY Factors.id;
ALTER SEQUENCE BILLS_ID_SEQ OWNED BY Bills.id;
ALTER SEQUENCE SHARINGPERSPECTIVES_ID_SEQ OWNED BY SharingPerspectives.id;
ALTER SEQUENCE CALLS_ID_SEQ OWNED BY Calls.id;
ALTER SEQUENCE FILES_ID_SEQ OWNED BY Files.id;
ALTER SEQUENCE REQUESTS_ID_SEQ OWNED BY Requests.id;
ALTER SEQUENCE DOCS_ID_SEQ OWNED BY Docs.id;
ALTER SEQUENCE ACTIVITIES_ID_SEQ OWNED BY Activities.id;
ALTER SEQUENCE INSTALLMENTS_ID_SEQ OWNED BY Installments.id;
ALTER SEQUENCE CONSUMPTIONPACKS_ID_SEQ OWNED BY ConsumptionPacks.id;
ALTER SEQUENCE PACKS_ID_SEQ OWNED BY Packs.id;
ALTER SEQUENCE ADVICETYPES_ID_SEQ OWNED BY AdviceTypes.id;
ALTER SEQUENCE FAILREQUESTS_ID_SEQ OWNED BY FailRequests.id;
ALTER SEQUENCE ACCEPTREQUESTS_ID_SEQ OWNED BY AcceptRequests.id;
ALTER SEQUENCE EXPERTISES_ID_SEQ OWNED BY Expertises.id;
ALTER SEQUENCE OFFICESADDRESS_ID_SEQ OWNED BY OfficesAddress.id;
ALTER SEQUENCE PRESENCESCHEDULES_ID_SEQ OWNED BY PresenceSchedules.id;
ALTER SEQUENCE ORGANIZATIONS_ID_SEQ OWNED BY Organizations.id;
ALTER SEQUENCE FEEDBACKS_ID_SEQ OWNED BY Feedbacks.id;
ALTER SEQUENCE QUESTIONS_ID_SEQ OWNED BY Questions.id;
ALTER SEQUENCE ANSWERS_ID_SEQ OWNED BY Answers.id;
ALTER SEQUENCE ADMINCONFIRMATIONS_ID_SEQ OWNED BY AdminConfirmations.id;
ALTER SEQUENCE CONSTANTS_ID_SEQ OWNED BY Constants.id;
ALTER SEQUENCE RIGHTMESSAGES_ID_SEQ OWNED BY RightMessages.id;
ALTER SEQUENCE USERFEEDBACKS_ID_SEQ OWNED BY UserFeedbacks.id;
ALTER SEQUENCE BLACKLISTS_ID_SEQ OWNED BY BlackLists.id;
ALTER SEQUENCE LOGS_ID_SEQ OWNED BY Logs.id;

-- ---------------------Alter table foreign key ----------------------
ALTER TABLE User_Role ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE User_Role ADD FOREIGN KEY(RoleId) REFERENCES Roles(id);

ALTER TABLE Role_Feature ADD FOREIGN KEY(RoleId) REFERENCES Roles(id);
ALTER TABLE Role_Feature ADD FOREIGN KEY(FeatureId) REFERENCES Features(id);

ALTER TABLE UserPopularity ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE UserPopularity ADD FOREIGN KEY(UserPopularId) REFERENCES Users(id);

ALTER TABLE Lawyer_Expertise ADD FOREIGN KEY(ExpertiseId) REFERENCES Expertises(id);
ALTER TABLE Lawyer_Expertise ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);

-- -----------------Alter table add foreign key------------------
ALTER TABLE Users ADD FOREIGN KEY(WalletId) REFERENCES Wallets(id);
ALTER TABLE Clients ADD FOREIGN KEY(id) REFERENCES Users(id);

ALTER TABLE InvitedUsers ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE Devices ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE AccessEntries ADD FOREIGN KEY(UserId) REFERENCES Users(id);

ALTER TABLE AssignDiscounts ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE AssignDiscounts ADD FOREIGN KEY(DiscountPackId) REFERENCES DiscountPacks(id);

ALTER TABLE Complains ADD FOREIGN KEY(UserId) REFERENCES Users(id);

ALTER TABLE Settings ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE Settings ADD FOREIGN KEY(SettingTypeId) REFERENCES SettingTypes(id);

ALTER TABLE Reminders ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE Reminders ADD FOREIGN KEY(NotificationId) REFERENCES Notifications(id);

ALTER TABLE Factors ADD FOREIGN KEY(BillId) REFERENCES Bills(id);
ALTER TABLE Bills ADD FOREIGN KEY(WalletId) REFERENCES Wallets(id);

ALTER TABLE Lawyers ADD FOREIGN KEY(id) REFERENCES Users(id);
ALTER TABLE Lawyers ADD FOREIGN KEY(OrganizationId) REFERENCES Organizations(id);
ALTER TABLE Lawyers ADD FOREIGN KEY(AdviceTypeId) REFERENCES AdviceTypes(id);
ALTER TABLE Lawyers ADD FOREIGN KEY(FailRequestId) REFERENCES FailRequests(id);

ALTER TABLE SharingPerspectives ADD FOREIGN KEY(UserId) REFERENCES Users(id);
ALTER TABLE SharingPerspectives ADD FOREIGN KEY(FileId) REFERENCES Files(id);

ALTER TABLE Calls ADD FOREIGN KEY(RequestId) REFERENCES Requests(id);
ALTER TABLE Calls ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);
ALTER TABLE Calls ADD FOREIGN KEY(ClientId) REFERENCES Clients(id);
ALTER TABLE Calls ADD FOREIGN KEY(DocId) REFERENCES Docs(id);

ALTER TABLE Requests ADD FOREIGN KEY(AdviceTypeId) REFERENCES AdviceTypes(id);
ALTER TABLE Requests ADD FOREIGN KEY(ClientId) REFERENCES Clients(id);
ALTER TABLE Requests ADD FOREIGN KEY(FileId) REFERENCES Files(id);

ALTER TABLE Docs ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);
ALTER TABLE Docs ADD FOREIGN KEY(FileId) REFERENCES Files(id);

ALTER TABLE Activities ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);
ALTER TABLE Activities ADD FOREIGN KEY(DocId) REFERENCES Docs(id);
ALTER TABLE Activities ADD FOREIGN KEY(FileId) REFERENCES Files(id);

ALTER TABLE Installments ADD FOREIGN KEY(FactorId) REFERENCES Factors(id);
ALTER TABLE Installments ADD FOREIGN KEY(ConsumptionPackId) REFERENCES ConsumptionPacks(id);

ALTER TABLE ConsumptionPacks ADD FOREIGN KEY(PackId) REFERENCES Packs(id);
ALTER TABLE ConsumptionPacks ADD FOREIGN KEY(RequestId) REFERENCES Requests(id);

ALTER TABLE Packs ADD FOREIGN KEY(AdviceTypeId) REFERENCES AdviceTypes(id);
ALTER TABLE AdviceTypes ADD FOREIGN KEY(AdviceTypeId) REFERENCES AdviceTypes(id);
ALTER TABLE FailRequests ADD FOREIGN KEY(RequestId) REFERENCES Requests(id);

ALTER TABLE AcceptRequests ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);
ALTER TABLE AcceptRequests ADD FOREIGN KEY(RequestId) REFERENCES Requests(id);

ALTER TABLE OfficesAddress ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);

ALTER TABLE PresenceSchedules ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);
ALTER TABLE PresenceSchedules ADD FOREIGN KEY(OfficesAddressId) REFERENCES OfficesAddress(id);

ALTER TABLE Organizations ADD FOREIGN KEY(WalletId) REFERENCES Wallets(id);

ALTER TABLE Feedbacks ADD FOREIGN KEY(ClientId) REFERENCES Clients(id);
ALTER TABLE Feedbacks ADD FOREIGN KEY(RequestId) REFERENCES Requests(id);

ALTER TABLE Questions ADD FOREIGN KEY(ClientId) REFERENCES Clients(id);

ALTER TABLE Answers ADD FOREIGN KEY(QuestionId) REFERENCES Questions(id);
ALTER TABLE Answers ADD FOREIGN KEY(LawyerId) REFERENCES Lawyers(id);