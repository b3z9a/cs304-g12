/* Drops all tables */
DROP TABLE PostalCode CASCADE constraints;
DROP TABLE Invoice CASCADE constraints;
DROP TABLE HealthcareProfessional CASCADE constraints;
DROP TABLE LabTechnician CASCADE constraints;
DROP TABLE Pharmacist CASCADE constraints;
DROP TABLE Doctor CASCADE constraints;
DROP TABLE Referral CASCADE constraints;
DROP TABLE Medication CASCADE constraints;
DROP TABLE Prescription CASCADE constraints;
DROP TABLE LabTest CASCADE constraints;
DROP TABLE ProvincialHealthPlan CASCADE constraints;
DROP TABLE ExtendedBenefitsPlan CASCADE constraints;
DROP TABLE Patient CASCADE constraints;

/* Create the tables.*/
CREATE TABLE PostalCode (
    postalCode  varchar(6),
    city        varchar(20),
    province    varchar(20),
    country     varchar(20),
    PRIMARY KEY (postalCode)
);

CREATE TABLE HealthcareProfessional (
    HID 		    integer,
    firstName 		varchar2(20),
    lastName 		varchar2(20),
    officeNumber 	varchar2(10),
    street 			varchar2(60),
    postalCode 		varchar2(6),
    homePhone 		varchar2(20),
    mobilePhone 	varchar2(20),
    PRIMARY KEY (HID),
    FOREIGN KEY (postalCode) REFERENCES PostalCode
);

CREATE TABLE LabTechnician (
    HID 				integer,
    certificationNumber integer,
    PRIMARY KEY(HID),
    FOREIGN KEY (HID) REFERENCES HealthCareProfessional
);

CREATE TABLE Pharmacist (
    HID 				integer,
    certificationNumber integer,
    PRIMARY KEY(HID),
    FOREIGN KEY (HID) REFERENCES HealthCareProfessional
);

CREATE TABLE Doctor (
    HID				    integer,
    certificationNumber integer,
    specialization  varchar(30) CHECK (specialization IN ('Anesthesiologist', 'Cardiologist', 'Dermatologist', 'Endocrinologist', 'Gastroenterologist', 'Geriatric Medicine Specialist', 'Gynecologist', 'Hematologist', 'Heptologist', 'Neonatologist', 'Nephrologist', 'Neurologist', 'Obstetrician', 'Oncologist', 'Oral Surgeon', 'Ophthalmologist', 'Orthopedic Surgeon', 'Otolaryngologist', 'Pediatrician', 'Psychiatrist', 'Pulmonologist', 'Radiologist', 'Rheumatologist', 'Sleep Disorder Specialist', 'Surgeon', 'Urologist', 'Family Doctor')),
    PRIMARY KEY(HID),
    FOREIGN KEY (HID) REFERENCES HealthCareProfessional
);

CREATE TABLE Patient (
    patientID 		integer,
    firstName 		varchar2(20),
    lastName 		varchar2(20),
    street 			varchar2(60),
    postalCode 		varchar2(6),
    homePhone 		varchar2(20),
    mobilePhone 	varchar2(20),
    PRIMARY KEY (patientID),
    FOREIGN KEY (postalCode) REFERENCES PostalCode
);

CREATE TABLE Referral (
    patientID			integer,
    referrerHID			integer,
    referreeHID			integer,
    referredDate		date not null,
    PRIMARY KEY (patientID, referrerHID, referreeHID),
    FOREIGN KEY (patientID) REFERENCES Patient ON DELETE CASCADE,
    FOREIGN KEY (referrerHID) REFERENCES Doctor,
    FOREIGN KEY (referreeHID) REFERENCES Doctor
);

CREATE TABLE Medication (
    medication			varchar2(40),
    dosageMeasure		varchar2(10) not null,
    PRIMARY KEY (medication)
);

CREATE TABLE Prescription (
    prescriptionID 	integer,
    medication 		varchar2(40),
    dosage 			decimal(10,2) not null,
    quantity 		integer not null,
    patientID		integer not null,
    drHID			integer not null,
    pharmHID		integer,
    prescribedDate	date not null,
    filledDate		date,
    PRIMARY KEY (prescriptionID),
    FOREIGN KEY (medication) REFERENCES Medication ON DELETE CASCADE,
    FOREIGN KEY (patientID) REFERENCES Patient ON DELETE CASCADE,
    FOREIGN KEY (drHID) REFERENCES Doctor ON DELETE SET NULL,
    FOREIGN KEY (pharmHID) REFERENCES Pharmacist ON DELETE SET NULL
);

CREATE TABLE LabTest (
    testID 			integer,
    cholesterol 	decimal(10,3),
    HDLcholesterol 	decimal(10,3),
    LDLcholesterol 	decimal(10,3),
    triglycerides 	decimal(10,3),
    whiteBloodCellCount 	decimal(10,3),
    redBloodCellCount 		decimal(10,3),
    hematocrit 		decimal(10,3),
    plateletCount 	decimal(10,3),
    NRBCpercent 	decimal(10,3),
    NRBCabsolute 	decimal(10,3),
    sodium 			decimal(10,3),
    phosphorus 		decimal(10,3),
    glucose 		decimal(10,3),
    patientID		integer not null,
    drHID			integer not null,
    labTechHID		integer,
    orderedDate		date not null,
    performedDate	date,
    PRIMARY KEY(testID),
    FOREIGN KEY (patientID) REFERENCES Patient ON DELETE CASCADE,
    FOREIGN KEY (drHID) REFERENCES Doctor ON DELETE SET NULL,
    FOREIGN KEY (labTechHID) REFERENCES LabTechnician ON DELETE SET NULL
);

CREATE TABLE ProvincialHealthPlan (
    planID 		integer,
    policyType  VARCHAR(40) CHECK (policyType IN ('BC Resident - MSP', 'BC Resident - Premium MSP', 'BC Resident - Income Assistance', 'BC Resident - Convention Refugees', 'Out-of-Province')),
    startDate	date not null,
    endDate		date not null,
    patientID	integer not null,
    PRIMARY KEY(planID),
    FOREIGN KEY (patientID) REFERENCES Patient ON DELETE CASCADE
);

CREATE TABLE ExtendedBenefitsPlan (
    EBPID 		    integer,
    planID		    integer not null,
    startDate 	    date not null,
    endDate 	    date not null,
    chiropractic 	varchar(1) CHECK (chiropractic IN ('Y', 'N')),
    physiotherapy 	varchar(1) CHECK (physiotherapy IN ('Y', 'N')),
    nonSurgicalPodiatry 	varchar(1) CHECK (nonSurgicalPodiatry IN ('Y', 'N')),
    massageTherapy			varchar(1) CHECK (massageTherapy IN ('Y', 'N')),
    acupuncture 	varchar(1) CHECK (acupuncture IN ('Y', 'N')),
    medication 		varchar(1) CHECK (medication IN ('Y', 'N')),
    physiotherapyAnnualLimit 	decimal(15,2),
    physiotherapyYTD	 	decimal(15,2),
    nonSurgicalPodiatryAnnualLimit 	decimal(15,2),
    nonSurgicalPodiatryYTD	 	decimal(15,2),
    chiropracticAnnualLimit		decimal(15,2),
    chiropracticYTD			decimal(15,2),
    massageTherapyAnnualLimit	decimal(15,2),
    massageTherapyYTD		decimal(15,2),
    acupunctureAnnualLimit 	decimal(15,2),
    acupunctureYTD			decimal(15,2),
    medicationAnnualLimit 	decimal(15,2),
    medicationYTD			decimal(15,2),
    PRIMARY KEY(EBPID),
    FOREIGN KEY (planID) REFERENCES ProvincialHealthPlan ON DELETE CASCADE
);

CREATE TABLE Invoice (
    invoiceID 			integer,
    patientID 		integer,
    invoiceItem 		varchar2(60),
    creationDate 		date not null,
    dueDate 			date not null,
    paymentStatus   VARCHAR(10) CHECK (paymentStatus IN ('Paid', 'Unpaid')) not null,
    paymentDate 		date,
    paymentMethod       VARCHAR(20) CHECK (paymentMethod IN ('Credit\Debit', 'Cash', 'Cheque')),
    amountOwing 		decimal(15,2) not null,
    paymentID			integer,
    planID				integer not null,
    PRIMARY KEY(invoiceID),
    FOREIGN KEY (patientID) REFERENCES Patient ON DELETE CASCADE,
    FOREIGN KEY (planID) REFERENCES ProvincialHealthPlan
);

/* Populate the database. */

insert into PostalCode values ('V6Z1Y6', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6H3N1', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6K2G2', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6S0J9', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6B3N6', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6K1C7', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6G2E5', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6J2J6', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V5T3N4', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6Y2V7', 'Richmond', 'BC', 'Canada');
insert into PostalCode values ('V6X4J7', 'Richmond', 'BC', 'Canada');
insert into PostalCode values ('V5H4T6', 'Burnaby', 'BC', 'Canada');
insert into PostalCode values ('V5C0B5', 'Burnaby', 'BC', 'Canada');
insert into PostalCode values ('V3N3N4', 'Burnaby', 'BC', 'Canada');
insert into PostalCode values ('V5B0A7', 'Burnaby', 'BC', 'Canada');
insert into PostalCode values ('V5J0B6', 'Burnaby', 'BC', 'Canada');
insert into PostalCode values ('V5B1S2', 'Burnaby', 'BC', 'Canada');
insert into PostalCode values ('V5G2X6', 'Burnaby', 'BC', 'Canada');
insert into PostalCode values ('V3V6H2', 'Surrey', 'BC', 'Canada');
insert into PostalCode values ('V3V6A8', 'Surrey', 'BC', 'Canada');
insert into PostalCode values ('V3R7C1', 'Surrey', 'BC', 'Canada');
insert into PostalCode values ('V3K4X9', 'Coquitlam', 'BC', 'Canada');
insert into PostalCode values ('T5A0A1', 'Edmonton', 'AB', 'Canada');
insert into PostalCode values ('98226', 'Seattle', 'WA', 'USA');

insert into HealthcareProfessional values (54337, 'Joel', 'Lewis', '306', '1081 Burrard St', 'V6Z1Y6', '604-713-2291', '604-682-2344 x306');
insert into HealthcareProfessional values (52731, 'Melissa', 'Clark', '211', '1081 Burrard St', 'V6Z1Y6', '604-779-3101', '604-682-2344 x211');
insert into HealthcareProfessional values (54781, 'Alice', 'Liu', 'E-117', '4480 Oak St', 'V6H3N1', '604-785-4001', '604-875-2345 x33115');
insert into HealthcareProfessional values (53833, 'Aarav', 'Patel', 'A-220', '4480 Oak St', 'V6H3N1', '778-885-3411', '604-682-2344 x33219');
insert into HealthcareProfessional values (52918, 'David', 'Chen', '', '2685 W Broadway', 'V6K2G2', '604-668-4228', '604-731-9187');
insert into HealthcareProfessional values (53211, 'Aaron', 'Miller', '', '2685 W Broadway', 'V6Z1Y6', '604-775-3021', '604-882-4038');
insert into HealthcareProfessional values (58377, 'James', 'Yu', '', '200-2475 Bayswater St', 'V6H3N1', '604-668-4522', '604-565-2111');
insert into HealthcareProfessional values (52942, 'Alex', 'Chen', '200', '1755 1st Ave W', 'V6K2G2', '604-668-8323', '604-788-6569');
insert into HealthcareProfessional values (54242, 'Vanessa', 'Burak', 'A110', '3261 5 Ave W', 'V6S0J9', '604-731-2388', '604-788-3076');
insert into HealthcareProfessional values (54986, 'Sanja', 'Avinashi', '359', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
insert into HealthcareProfessional values (58739, 'Catherine', 'Cantarutti', '', '3080 Price Edward St', 'V5T3N4', '604-778-3848', '604-853-5833');
insert into HealthcareProfessional values (58572, 'Kevin', 'Chan', '210', '3080 Price Edward St', 'V5T3N4', '604-744-2948', '604-593-5844');
insert into HealthcareProfessional values (56483, 'Divi', 'Chandra', '', '3080 Price Edward St', 'V5T3N4', '604-565-9843', '250-857-3844');
insert into HealthcareProfessional values (56837, 'Helen', 'Yu', 'B-210', '3080 Price Edward St', 'V5T3N4', '604-847-3883', '250-883-9853');
insert into HealthcareProfessional values (55243, 'Yi Yueh', 'Cheng', '10', '3080 Price Edward St', 'V5T3N4', '604-722-8535', '250-573-8573');
insert into HealthcareProfessional values (53948, 'Peter', 'Chiu', '', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
insert into HealthcareProfessional values (53596, 'Bahar', 'Cinarli', '', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
insert into HealthcareProfessional values (55284, 'Jeff', 'Coleman', '210', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
insert into HealthcareProfessional values (55934, 'Nishi', 'Dhawan', '15', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
insert into HealthcareProfessional values (56868, 'Timothy', 'Doty', '', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
insert into HealthcareProfessional values (51837, 'Sanja', 'Avinashi', '220', '3935 Kincaid St', 'V5G2X6', '604-634-8573', '778-584-4835');
insert into HealthcareProfessional values (53592, 'Sanja', 'Avinashi', '215', '3935 Kincaid St', 'V5G2X6', '604-634-8434', '778-573-5838');
insert into HealthcareProfessional values (59434, 'Sanja', 'Avinashi', '233', '3935 Kincaid St', 'V5G2X6', '604-644-6832', '766-938-9671');
insert into HealthcareProfessional values (56838, 'Sanja', 'Avinashi', '115', '3935 Kincaid St', 'V5G2X6', '604-644-6824', '855-365-8344');
insert into HealthcareProfessional values (56992, 'Sanja', 'Avinashi', '101', '3935 Kincaid St', 'V5G2X6', '604-662-4823', '855-365-3843');


insert into Doctor values (54337, 922789, 'Cardiologist');
insert into Doctor values (52731, 992833, 'Neurologist');
insert into Doctor values (54781, 946721, 'Pediatrician');
insert into Doctor values (53833, 996482, 'Surgeon');
insert into Doctor values (52918, 956432, 'Family Doctor');
insert into Doctor values (51837, 982743, 'Rheumatologist');
insert into Doctor values (53592, 992847, 'Sleep Disorder Specialist');
insert into Doctor values (59434, 973853, 'Surgeon');
insert into Doctor values (56838, 923485, 'Urologist');
insert into Doctor values (56992, 913958, 'Otolaryngologist');
insert into Doctor values (53948, 938572, 'Hematologist');
insert into Doctor values (53596, 959283, 'Heptologist');
insert into Doctor values (55284, 969924, 'Neonatologist');
insert into Doctor values (55934, 956384, 'Psychiatrist');
insert into Doctor values (56868, 924858, 'Radiologist');
insert into Doctor values (53211, 932111, 'Family Doctor');
insert into Doctor values (58377, 926433, 'Family Doctor');
insert into Doctor values (52942, 985744, 'Family Doctor');
insert into Doctor values (54242, 928355, 'Family Doctor');
insert into Doctor values (58739, 939583, 'Anesthesiologist');
insert into Doctor values (58572, 919384, 'Dermatologist');
insert into Doctor values (56483, 995824, 'Gastroenterologist');
insert into Doctor values (56837, 985723, 'Geriatric Medicine Specialist');
insert into Doctor values (55243, 985737, 'Gynecologist');

insert into HealthcareProfessional values (52127, 'Grace', 'Wong', NULL, '6060 Minoru Blvd', 'V6Y2V7', '604-668-2187', '604-273-6187');
insert into HealthcareProfessional values (53573, 'Anna', 'Brown', NULL, '6060 Minoru Blvd', 'V6Y2V7', '604-668-2944', '604-273-6187');
insert into HealthcareProfessional values (51853, 'Micheal', 'Smith', NULL, '1295 Seymour St', 'V6B3N6', '604-785-8322', '604-801-5708');
insert into HealthcareProfessional values (55246, 'Richard', 'Zhang', NULL, '1295 Seymour St', 'V6B3N6', '604-913-2911 ', '604-801-5708');
insert into HealthcareProfessional values (53763, 'Tanvi', 'Chabra', NULL, '5968 Webber Ln', 'V6S0J9', '604-785-8763', '604-224-3086');
insert into HealthcareProfessional values (58571, 'Ali', 'Farahani', '', '3713 Kensington Ave', 'V5B0A7', '604-722-4983', '604-323-2843');
insert into HealthcareProfessional values (58573, 'Joseph', 'Harris', '', '3713 Kensington Ave', 'V5B0A7', '604-722-4983', '604-985-4928');
insert into HealthcareProfessional values (58574, 'Gideon', 'Fay', 'Unit 120', 'Glenlyon Pkwy', 'V5J0B6', '604-663-3841', '775-398-3958');
insert into HealthcareProfessional values (58575, 'Sumathi', 'Gudapati', 'Unit 120', 'Glenlyon Pkwy', 'V5J0B6', '604-984-3843', '775-294-8243');
insert into HealthcareProfessional values (58576, 'Garry', 'Henderson', '#203', '6542 Hastings St.', 'V5B1S2', '604-945-2943', '250-839-9284');

insert into Pharmacist values (52127, 767332);
insert into Pharmacist values (53573, 768329);
insert into Pharmacist values (51853, 745264);
insert into Pharmacist values (55246, 746261);
insert into Pharmacist values (53763, 725154);
insert into Pharmacist values (58571, 758375);
insert into Pharmacist values (58573, 724853);
insert into Pharmacist values (58574, 768345);
insert into Pharmacist values (58575, 795735);
insert into Pharmacist values (58576, 738576);

insert into HealthcareProfessional values (51833, 'Aaron', 'Daniels', '#1150','4151 Hazelbridge Way', 'V6X4J7', '604-731-4522', '604-507-5070');
insert into HealthcareProfessional values (54328, 'May', 'Chu', '#1150','4151 Hazelbridge Way', 'V6X4J7', '604-775-8811', '604-507-5070');
insert into HealthcareProfessional values (52856, 'Angela', 'Summers', '112','3540 W 41st Ave', 'V6X4J7', '778-456-3282', '1-800-431-7206');
insert into HealthcareProfessional values (53647, 'Ying Yue', 'Wong', '112','3540 W 41st Ave', 'V6X4J7', '778-456-5285', '1-800-431-7206');
insert into HealthcareProfessional values (53176, 'Vanya', 'Preet', '112','3540 W 41st Ave', 'V6X4J7', '604-731-2411', '1-800-431-7206');
insert into HealthcareProfessional values (57564, 'Lynn', 'Nelson', '306', '4827 Kingsway', 'V5H4T6', '604-774-3943', '604-927-3858');
insert into HealthcareProfessional values (53565, 'Mandy', 'Karim', '306', '4827 Kingsway', 'V5H4T6', '604-765-3958', '604-927-3743');
insert into HealthcareProfessional values (54566, 'Colleen', 'Kirkham', '306', '4500 Still Creek Dr', 'V5C0B5', '604-713-5837', '250-385-3958');
insert into HealthcareProfessional values (56328, 'Kim Liat', 'Liew', '306', '4500 Still Creek Dr', 'V5C0B5', '604-713-5837', '604-838-4838');
insert into HealthcareProfessional values (57831, 'Sohan', 'Mansingh', '306', '7885 6th St', 'V3N3N4', '604-735-4839', '604-795-3944');

insert into LabTechnician values (51833, 836275);
insert into LabTechnician values (54328, 864271);
insert into LabTechnician values (52856, 812674);
insert into LabTechnician values (53647, 864527);
insert into LabTechnician values (53176, 896264);
insert into LabTechnician values (57564, 837444);
insert into LabTechnician values (53565, 829485);
insert into LabTechnician values (54566, 828475);
insert into LabTechnician values (56328, 848673);
insert into LabTechnician values (57831, 849582);

insert into Patient values (1, 'Tony', 'Liu', '15388 24th Ave', 'V3V6H2', '778-348-5834', '250-865-3853');
insert into Patient values (2, 'Simon', 'Gaur', '10777 University Dr.', 'V3V6H2', NULL, NULL);
insert into Patient values (3, 'Barry', 'Lam', NULL, NULL, '778-485-2834', '250-865-3853');
insert into Patient values (4, 'Vince', 'Lao', '7155 Hall Rd', 'V3V6A8', NULL, '250-865-3853');
insert into Patient values (12345678, 'Chris', 'Ellis', 'E-104-N, Pinehurst Apartments, 422 Maple St', 'V6B3N6', NULL, NULL);
insert into Patient values (32118954, 'Jonathan', 'Banks', '2144 Dunbar St.', 'V6B3N6', '604-731-2499', '778-456-2382');
insert into Patient values (29562774, 'David', 'Chen', '1114 Lameys Mill Rd.', 'V6S0J9', '604-731-8753', '604-731-9187');
insert into Patient values (31857372, 'Winny', 'Zhu', '4491 W 4th Ave', 'V6S0J9', '778-291-3753', '778-981-3722');
insert into Patient values (31274656, 'Antar', 'Atwal', 'Apt. 117, 8833 Hazelbridge Way', 'V6Y2V7', '604-233-4713', '604-233-8172');
insert into Patient values (32485737, 'Alex', 'Grossman', '271 Pike St.', '98226', '206-775-9312', '206-744-2713');
insert into Patient values (31572742, 'Mary', 'Berjohn', '4532 Lakeview Way', 'T5A0A1', '', '780-555-4031');
insert into Patient values (38577324, 'Malek', 'Banu Moosa', '5772 177b St', 'V3V6A8', '855-294-2844', '250-865-3853');
insert into Patient values (34873524, 'Emma', 'Johnson', '14880 108 Ave', 'V3V6A8', '673-495-3445', '250-865-3853');
insert into Patient values (38475018, 'Janice', 'Roberts', '', 'V3R7C1', '673-574-3421', '250-865-3853');
insert into Patient values (37663542, 'John', 'Ross', '4343 Wallace Crescent', NULL, NULL, NULL);
insert into Patient values (31836411, 'John', 'Newmann', '4623 Knight St.', 'V3R7C1', '566-394-2943', '250-865-3853');
insert into Patient values (33545243, 'Ellis', 'Rouhana', '2955 Schoolhouse St', 'V3K4X9', '566-294-2943', '250-865-3853');
insert into Patient values (32938452, 'Matthew', 'Berube', '4623 Knight St.', 'V6J2J6', '604-775-3958', '250-865-3853');
insert into Patient values (39485732, 'Nassir', 'Adami', 'Unit 210, 2732 Broadway', 'V6G2E5', '604-883-4953', '250-938-5834');
insert into Patient values (38573757, 'Antonio', 'Gomez', '3211 Fraser St.', 'V6G2E5', '604-993-4935', '250-854-3945');
insert into Patient values (39485722, 'Miguel', 'Benitez-Lazo', '255, 1755 W 14th Ave', 'V6K1C7', '604-884-5924', '818-283-8429');
insert into Patient values (34838582, 'Ravdeep', 'Birdi', NULL, NULL, NULL, NULL);
insert into Patient values (35835723, 'Marie', 'Fransen', '414 939 Beatty St.', 'V6B3N6', '604-753-3858', '771-883-2843');
insert into Patient values (34958324, 'Julie', 'Fujiwara', '7B, 5555 Dunbar St.', 'V6B3N6', NULL, '771-294-2844');
insert into Patient values (33847244, 'Charlotte', 'Gill', '550 W 12th Ave', 'V6G2E5', '775-394-2844', NULL);
insert into Patient values (32838321, 'Nanveet', 'Singh', '211, 1454 Pendrell St. #31', 'V6K1C7', '775-395-2843', '665-294-9433');
insert into Patient values (35384852, 'Hillary', 'Emmerson', '2145 York Ave', 'V6G2E5', '775-244-3949', '675-938-4834');
insert into Patient values (38583233, 'Pierre', 'Lalonde', '488 W 41st', 'V6B3N6', '250-394-2949', '250-384-2939');
insert into Patient values (35294858, 'Diana', 'Khan', '4988 W 49th Ave', 'V6K1C7', '250-294-2949', '250-385-3923');
insert into Patient values (35728495, 'Daniel', 'King', '2475 W Broadway', 'V6B3N6', '665-778-4935', '778-298-4823');

insert into Referral values (32118954, 52918, 54337, TO_DATE('2019-02-21', 'YYYY-MM-DD'));
insert into Referral values (12345678, 52918, 52731, TO_DATE('2018-05-01', 'YYYY-MM-DD'));
insert into Referral values (32485737, 52918, 52731, TO_DATE('2019-02-18', 'YYYY-MM-DD'));
insert into Referral values (31857372, 52918, 53833, TO_DATE('2018-12-11', 'YYYY-MM-DD'));
insert into Referral values (31274656, 52918, 53833, TO_DATE('2018-10-06', 'YYYY-MM-DD'));
insert into Referral values (12345678, 52918, 53833, TO_DATE('2018-12-11', 'YYYY-MM-DD'));
insert into Referral values (12345678, 52918, 54337, TO_DATE('2019-02-21', 'YYYY-MM-DD'));
insert into Referral values (1, 53211, 54337, TO_DATE('2019-01-15', 'YYYY-MM-DD'));
insert into Referral values (1, 53211, 56483, TO_DATE('2017-11-17', 'YYYY-MM-DD'));
insert into Referral values (1, 53211, 58572, TO_DATE('2018-08-15', 'YYYY-MM-DD'));
insert into Referral values (2, 58377, 53596, TO_DATE('2018-06-13', 'YYYY-MM-DD'));
insert into Referral values (2, 58377, 56868, TO_DATE('2018-02-22', 'YYYY-MM-DD'));
insert into Referral values (2, 58377, 56992, TO_DATE('2019-03-11', 'YYYY-MM-DD'));
insert into Referral values (4, 52942, 54337, TO_DATE('2019-03-11', 'YYYY-MM-DD'));
insert into Referral values (4, 52942, 51837, TO_DATE('2018-10-09', 'YYYY-MM-DD'));

insert into Medication values ('Albuterol', 'mg');
insert into Medication values ('Esomeprazole', 'mg');
insert into Medication values ('Fluticasone', 'mcg');
insert into Medication values ('Fluoxetine', 'mg');
insert into Medication values ('Pregabalin', 'mL');
insert into Medication values ('Simvastatin', 'mg');
insert into Medication values ('Lisinopril', 'mg');
insert into Medication values ('Levothyroxine', 'mg');
insert into Medication values ('Metformin', 'mg');
insert into Medication values ('Amoxicillin', 'mg');
insert into Medication values ('Lipitor', 'mg');
insert into Medication values ('Amlodipine', 'mg');

insert into Prescription values (452855, 'Albuterol', 25, 100, 32118954, 54337, 55246, TO_DATE('2018-09-11','YYYY-MM-DD'), TO_DATE('2018-09-13','YYYY-MM-DD'));
insert into Prescription values (485274, 'Fluoxetine', 20, 300, 12345678, 52918, 53763, TO_DATE('2019-01-20','YYYY-MM-DD'), TO_DATE('2019-01-20','YYYY-MM-DD'));
insert into Prescription values (438245, 'Esomeprazole', 10, 40, 12345678, 52918, NULL, TO_DATE('2018-08-04','YYYY-MM-DD'), NULL);
insert into Prescription values (472596, 'Pregabalin', 85, 1, 12345678, 52918, 52127, TO_DATE('2018-03-15','YYYY-MM-DD'), TO_DATE('2018-03-21','YYYY-MM-DD'));
insert into Prescription values (492845, 'Fluticasone', 15, 100, 12345678, 52918, 52127, TO_DATE('2018-04-26','YYYY-MM-DD'), TO_DATE('2018-05-01','YYYY-MM-DD'));
insert into Prescription values (472947, 'Pregabalin', 85, 1, 31857372, 52918, 52127, TO_DATE('2018-03-15','YYYY-MM-DD'), TO_DATE('2018-03-21','YYYY-MM-DD'));
insert into Prescription values (427437, 'Fluticasone', 15, 100, 31274656, 52918, 52127, TO_DATE('2018-04-26','YYYY-MM-DD'), TO_DATE('2018-05-01','YYYY-MM-DD'));
insert into Prescription values (438477, 'Amoxicillin', 25, 100, 4, 53211, 58573, TO_DATE('2019-01-05','YYYY-MM-DD'), TO_DATE('2019-01-06','YYYY-MM-DD'));
insert into Prescription values (445873, 'Fluoxetine', 20, 200, 4, 53211, 58573, TO_DATE('2018-10-03','YYYY-MM-DD'), TO_DATE('2018-10-03','YYYY-MM-DD'));
insert into Prescription values (427483, 'Levothyroxine', 15, 150, 4, 53211, 58574, TO_DATE('2018-06-19','YYYY-MM-DD'), TO_DATE('2018-06-19','YYYY-MM-DD'));
insert into Prescription values (495872, 'Fluticasone', 35, 75, 4, 53211, NULL, TO_DATE('2017-08-16','YYYY-MM-DD'), TO_DATE('2017-08-16','YYYY-MM-DD'));
insert into Prescription values (431734, 'Metformin', 25, 30, 2, 58377, NULL, TO_DATE('2019-03-25','YYYY-MM-DD'), NULL);
insert into Prescription values (459382, 'Levothyroxine', 15, 100, 2, 58377, 58575, TO_DATE('2019-01-26','YYYY-MM-DD'), TO_DATE('2019-01-31','YYYY-MM-DD'));
insert into Prescription values (448582, 'Lisinopril', 20, 200, 2, 58377, 58575, TO_DATE('2018-10-01','YYYY-MM-DD'), TO_DATE('2018-10-06','YYYY-MM-DD'));
insert into Prescription values (433853, 'Pregabalin', 20, 300, 3, 52942, NULL, TO_DATE('2018-04-15','YYYY-MM-DD'), NULL);
insert into Prescription values (429384, 'Simvastatin', 30, 50, 3, 52942, NULL, TO_DATE('2019-03-12','YYYY-MM-DD'), NULL);
insert into Prescription values (448273, 'Lipitor', 5, 100, 3, 52942, NULL, TO_DATE('2018-03-11','YYYY-MM-DD'), NULL);


insert into LabTest values (827432, 175.4, 70.8, 95.2, 104.6, 2.27, 3.51, 45.1, 195, 25.7, 0.75, 140, 3.8, 133.2, 12345678, 52918, 51833, TO_DATE('2019-01-29', 'YYYY-MM-DD'), NULL);
insert into LabTest values (882475, 132.0, 70.2, 61.8, 85.7, 2.33, 3.46, 40.2, 206, 17.2, 0.66, 140, 3.6, 121.0, 12345678, 52918, 52856, TO_DATE('2019-02-23', 'YYYY-MM-DD'), NULL);
insert into LabTest values (830485, 155.7, 85.7, 70.0, 113.2, 2.36, 3.58, 39.7, 173, 23.3, 0.70, 140, 3.7, 140.6, 12345678, 52918, 53176, TO_DATE('2018-11-05', 'YYYY-MM-DD'), TO_DATE('2018-11-06', 'YYYY-MM-DD'));
insert into LabTest values (865282, 200.8, 103.9, 96.9, 92.7, 2.21, 3.44, 48.3, 180, 20.1, 0.68, 140, 4.1, 135.9, 12345678, 52918, 53647, TO_DATE('2018-10-21', 'YYYY-MM-DD'), TO_DATE('2018-10-23', 'YYYY-MM-DD'));
insert into LabTest values (818673, 125.0, 63.5, 61.5, 99.6, 2.42, 3.20, 42.9, 215, 21.5, 0.72, 140, 4.0, 144.6, 12345678, 52918, 54328, TO_DATE('2018-12-13', 'YYYY-MM-DD'), TO_DATE('2018-12-17', 'YYYY-MM-DD'));
insert into LabTest values (883747, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 54337, NULL, TO_DATE('2019-03-21', 'YYYY-MM-DD'), NULL);
insert into LabTest values (837563, 179.8, 67.5, 93.0, 102.5, 2.16, 3.49, 43.8, 190, 24.7, 0.81, 133, 3.7, 138.2, 1, 53211, 53176, TO_DATE('2018-12-21', 'YYYY-MM-DD'), TO_DATE('2019-01-03', 'YYYY-MM-DD'));
insert into LabTest values (848281, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 58377, NULL, TO_DATE('2019-03-11', 'YYYY-MM-DD'), NULL);
insert into LabTest values (847850, 183.3, 81.1, 97.8, 103.6, 2.15, 3.44, 42.1, 192, 23.6, 0.76, 138, 3.7, 133.6, 2, 58377, 53565, TO_DATE('2018-04-16', 'YYYY-MM-DD'), TO_DATE('2018-04-17', 'YYYY-MM-DD'));
insert into LabTest values (847370, 184.1, 81.4, 100.2, 100.0, 2.17, 3.46, 42.7, 192, 23.9, 0.78, 132, 3.7, 133.6, 2, 58377, 54566, TO_DATE('2018-11-03', 'YYYY-MM-DD'), TO_DATE('2018-11-10', 'YYYY-MM-DD'));
insert into LabTest values (827475, 165.0, 70.8, 93.0, 96.7, 2.15, 3.75, 42.0, 186, 22.3, 0.66, 138, 3.6, 121.2, 4, 52942, 57831, TO_DATE('2018-08-14', 'YYYY-MM-DD'), TO_DATE('2018-08-20', 'YYYY-MM-DD'));

insert into ProvincialHealthPlan values (128473, 'BC Resident - MSP', TO_DATE('2018-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 32118954);
insert into ProvincialHealthPlan values (125724, 'BC Resident - MSP', TO_DATE('2018-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 29562774);
insert into ProvincialHealthPlan values (127828, 'BC Resident - Income Assistance', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 12345678);
insert into ProvincialHealthPlan values (121738, 'Out-of-Province', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 31274656);
insert into ProvincialHealthPlan values (126724, 'Out-of-Province', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 31274656);
insert into ProvincialHealthPlan values (128471, 'BC Resident - MSP', TO_DATE('2018-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 1);
insert into ProvincialHealthPlan values (124772, 'BC Resident - MSP', TO_DATE('2018-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 2);
insert into ProvincialHealthPlan values (124324, 'BC Resident - Income Assistance', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 3);
insert into ProvincialHealthPlan values (128472, 'BC Resident - Premium MSP', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 4);

insert into ExtendedBenefitsPlan values (238948, 128473, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'N', 'N', 'N', 'N', 'N', 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2500.00, 463.00);
insert into ExtendedBenefitsPlan values (273284, 125724, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'Y', 'N', 'Y', 'Y', 'Y', 'Y', 2500.0, 0.0, 2500.00, 400.00, NULL, NULL, 2500.00, 0.0, 2500.0, 0.0, 2500.00, 463.00);
insert into ExtendedBenefitsPlan values (294742, 127828, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2021-01-01', 'YYYY-MM-DD'), 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 1000.0, 0.0, 1000.0, 225.50, 1000.0, 0.0, 1000.0, 125.25, 1000.0, 100.0, 2500.00, 225.00);
insert into ExtendedBenefitsPlan values (274633, 127828, TO_DATE('2018-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'Y', 'N', 'N', 'N', 'N', 'Y', 1000.0, 125.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1500.00, 500.00);
insert into ExtendedBenefitsPlan values (258291, 121738, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'N', 'N', 'N', 'N', 'N', 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2500.00, 0.00);
insert into ExtendedBenefitsPlan values (283735, 128471, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'Y', 'N', 'N', 'N', 'N', 'Y', 1000.0, 200.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2000.00, 103.00);
insert into ExtendedBenefitsPlan values (238580, 124324, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'N', 'N', 'N', 'N', 'Y', 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 500.0, 0.0, 1500.00, 369.00);
insert into ExtendedBenefitsPlan values (285739, 124324, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'Y', 'Y', 'Y', 'N', 'N', 'N', 500.0, 0.0, 500.0, 0.0, 500.0, 150.21, NULL, NULL, NULL, NULL, NULL, NULL);
insert into ExtendedBenefitsPlan values (205930, 128472, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'N', 'N', 'Y', 'N', 'N', 'Y', NULL, NULL, NULL, NULL, 1000.00, 1000.00, NULL, NULL, NULL, NULL, 1250.00, 0.0);

insert into Invoice values (119274, 32118954, 'Annual exam', TO_DATE('2018-11-02','YYYY-MM-DD'), TO_DATE('2018-12-02','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-27','YYYY-MM-DD'), 'Credit\Debit', 0.0, 628462, 128473);
insert into Invoice values (183746, 12345678, 'Annual exam', TO_DATE('2018-11-02','YYYY-MM-DD'), TO_DATE('2018-12-02','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-27','YYYY-MM-DD'), 'Credit\Debit', 0.0, 628462, 128473);
insert into Invoice values (148924, 32485737, 'Clinic visit', TO_DATE('2018-10-14','YYYY-MM-DD'), TO_DATE('2018-11-14','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 200.0, NULL, 121738);
insert into Invoice values (182747, 12345678, 'Clinic visit', TO_DATE('2018-10-14','YYYY-MM-DD'), TO_DATE('2018-11-14','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 200.0, NULL, 121738);
insert into Invoice values (152748, 31572742, 'Clinic visit', TO_DATE('2018-05-11','YYYY-MM-DD'), TO_DATE('2018-05-11','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-27','YYYY-MM-DD'), 'Cheque', 0.0, 628462, 126724);
insert into Invoice values (119396, 31857372, 'Orthodic insoles', TO_DATE('2019-01-13','YYYY-MM-DD'), TO_DATE('2019-03-13','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 265.50, NULL, 128473);
insert into Invoice values (137472, 12345678, 'Orthodic insoles', TO_DATE('2019-01-13','YYYY-MM-DD'), TO_DATE('2019-03-13','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 265.50, NULL, 128473);
insert into Invoice values (192745, 29562774, 'Massage re: back injury', TO_DATE('2019-01-04','YYYY-MM-DD'), TO_DATE('2019-02-04','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 145.0, NULL, 127828);
insert into Invoice values (138824, 1, 'Clinic visit', TO_DATE('2019-02-14','YYYY-MM-DD'), TO_DATE('2019-03-14','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 100.0, NULL, 128471);
insert into Invoice values (128473, 1, 'Knee brace', TO_DATE('2019-02-12','YYYY-MM-DD'), TO_DATE('2019-03-12','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 100.0, NULL, 128471);
insert into Invoice values (159837, 1, 'Prescription - Albuterol', TO_DATE('2019-02-15','YYYY-MM-DD'), TO_DATE('2018-03-15','YYYY-MM-DD'), 'Paid', TO_DATE('2019-03-12','YYYY-MM-DD'), 'Credit\Debit', 0.0, 628462, 128471);
insert into Invoice values (129842, 1, 'Prescription - Fluoxetine', TO_DATE('2019-03-01','YYYY-MM-DD'), TO_DATE('2019-04-01','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 100.0, NULL, 128471);
insert into Invoice values (185734, 3, 'Clinic visit', TO_DATE('2019-02-12','YYYY-MM-DD'), TO_DATE('2019-02-12','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 150.0, NULL, 124324);
insert into Invoice values (158382, 3, 'Back brace', TO_DATE('2019-02-11','YYYY-MM-DD'), TO_DATE('2018-03-11','YYYY-MM-DD'), 'Paid', TO_DATE('2019-03-05','YYYY-MM-DD'), 'Cash', 20.0, 674823, 124324);
insert into Invoice values (124759, 3, 'Clinic visit', TO_DATE('2019-02-25','YYYY-MM-DD'), TO_DATE('2018-03-25','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 200.0, NULL, 124324);
insert into Invoice values (184854, 3, 'Prescription - Pregbalin', TO_DATE('2018-10-14','YYYY-MM-DD'), TO_DATE('2018-11-14','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-15','YYYY-MM-DD'), 'Credit\Debit', 0.0, 682742, 124324);
insert into Invoice values (152745, 4, 'Clinic visit', TO_DATE('2019-01-05','YYYY-MM-DD'), TO_DATE('2018-05-11','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-27','YYYY-MM-DD'), 'Cheque', 0.0, 683743, 128472);
insert into Invoice values (123845, 4, 'Prescription - Amoxicillin', TO_DATE('2018-10-11','YYYY-MM-DD'), TO_DATE('2018-11-11','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 37.21, NULL, 128472);
insert into Invoice values (145883, 4, 'Chest X-Ray', TO_DATE('2019-03-25','YYYY-MM-DD'), TO_DATE('2019-04-25','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 200.0, NULL, 128472);
insert into Invoice values (123984, 4, 'Orthopedic massage', TO_DATE('2019-01-05','YYYY-MM-DD'), TO_DATE('2019-02-05','YYYY-MM-DD'), 'Paid', TO_DATE('2019-01-07','YYYY-MM-DD'), 'Credit\Debit', 0.0, 684732, 128472);
