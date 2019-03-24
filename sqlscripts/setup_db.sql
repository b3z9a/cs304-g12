/* Drops all tables */

DROP TABLE PostalCode CASCADE constraints;

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

DROP TABLE Invoice CASCADE constraints;

DROP TABLE Patient CASCADE constraints;

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
    FOREIGN KEY (patientID) REFERENCES Patient,
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
    dosage 			decimal not null,
    quantity 		integer not null,
    patientID		integer not null,
    drHID			integer not null,
    pharmHID		integer not null,
    prescribedDate	date not null,
    filledDate		date,
    PRIMARY KEY (prescriptionID),
    FOREIGN KEY (medication) REFERENCES Medication,
    FOREIGN KEY (patientID) REFERENCES Patient,
    FOREIGN KEY (drHID) REFERENCES Doctor,
    FOREIGN KEY (pharmHID) REFERENCES Pharmacist
);

CREATE TABLE LabTest (
    testID 			integer,
    cholesterol 	decimal,
    HDLcholesterol 	decimal,
    LDLcholesterol 	decimal,
    triglycerides 	decimal,
    whiteBloodCellCount 	decimal,
    redBloodCellCount 		decimal,
    hematocrit 		decimal,
    plateletCount 	decimal,
    NRBCpercent 	decimal,
    NRBCabsolute 	decimal,
    sodium 			decimal,
    phosphorus 		decimal,
    glucose 		decimal,
    patientID		integer not null,
    drHID			integer not null,
    labTechHID		integer,
    orderedDate		date not null,
    performedDate	date,
    PRIMARY KEY(testID),
    FOREIGN KEY (patientID) REFERENCES Patient,
    FOREIGN KEY (drHID) REFERENCES Doctor,
    FOREIGN KEY (labTechHID) REFERENCES LabTechnician
);

CREATE TABLE ProvincialHealthPlan (
    planID 		integer,
    policyType  VARCHAR(40) CHECK (policyType IN ('BC Resident - MSP', 'BC Resident - Premium MSP', 'BC Resident - Income Assistance', 'BC Resident - Convention Refugees', 'Out-of-Province')),
    startDate	date not null,
    endDate		date not null,
    patientID	integer not null,
    PRIMARY KEY(planID),
    FOREIGN KEY (patientID) REFERENCES Patient
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
    physiotherapyAnnualLimit 	decimal,
    physiotherapyYTD	 	decimal,
    nonSurgicalPodiatryAnnualLimit 	decimal,
    nonSurgicalPodiatryYTD	 	decimal,
    chiropracticAnnualLimit		decimal,
    chiropracticYTD			decimal,
    massageTherapyAnnualLimit	decimal,
    massageTherapyYTD		decimal,
    acupunctureAnnualLimit 	decimal,
    acupunctureYTD			decimal,
    medicationAnnualLimit 	decimal,
    medicationYTD			decimal,
    PRIMARY KEY(EBPID),
    FOREIGN KEY (planID) REFERENCES ProvincialHealthPlan
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
    amountOwing 		decimal not null,
    paymentID			integer,
    planID				integer not null,
    PRIMARY KEY(invoiceID),
    FOREIGN KEY (patientID) REFERENCES Patient,
    FOREIGN KEY (planID) REFERENCES ProvincialHealthPlan
);

/* Inserts 5+ tuples into each table. */

insert into PostalCode values ('V6Z1Y6', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6H3N1', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6K2G2', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6S0J9', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6B3N6', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6Y2V7', 'Richmond', 'BC', 'Canada');
insert into PostalCode values ('V6X4J7', 'Richmond', 'BC', 'Canada');
insert into PostalCode values ('T5A0A1', 'Edmonton', 'AB', 'Canada');
insert into PostalCode values ('98226', 'Seattle', 'WA', 'USA');

insert into HealthcareProfessional values (54337, 'Joel', 'Lewis', '306', '1081 Burrard St', 'V6Z1Y6', '604-713-2291', '604-682-2344 x306');
insert into HealthcareProfessional values (52731, 'Melissa', 'Clark', '211', '1081 Burrard St', 'V6Z1Y6', '604-779-3101', '604-682-2344 x211');
insert into HealthcareProfessional values (54781, 'Alice', 'Liu', 'E-117', '4480 Oak St', 'V6H3N1', '604-785-4001', '604-875-2345 x33115');
insert into HealthcareProfessional values (53833, 'Aarav', 'Patel', 'A-220', '4480 Oak St', 'V6H3N1', '778-885-3411', '604-682-2344 x33219');
insert into HealthcareProfessional values (52918, 'David', 'Chen', '', '2685 W Broadway', 'V6K2G2', '604-668-4228', '604-731-9187');

insert into Doctor values (54337, 922789, 'Cardiologist');
insert into Doctor values (52731, 992833, 'Neurologist');
insert into Doctor values (54781, 946721, 'Pediatrician');
insert into Doctor values (53833, 996482, 'Surgeon');
insert into Doctor values (52918, 956432, 'Family Doctor');

insert into HealthcareProfessional values (52127, 'Grace', 'Wong', NULL, '6060 Minoru Blvd', 'V6Y2V7', '604-668-2187', '604-273-6187');
insert into HealthcareProfessional values (53573, 'Anna', 'Brown', NULL, '6060 Minoru Blvd', 'V6Y2V7', '604-668-2944', '604-273-6187');
insert into HealthcareProfessional values (51853, 'Micheal', 'Smith', NULL, '1295 Seymour St', 'V6B3N6', '604-785-8322', '604-801-5708');
insert into HealthcareProfessional values (55246, 'Richard', 'Zhang', NULL, '1295 Seymour St', 'V6B3N6', '604-913-2911 ', '604-801-5708');
insert into HealthcareProfessional values (53763, 'Tanvi', 'Chabra', NULL, '5968 Webber Ln', 'V6S0J9', '604-785-8763', '604-224-3086');

insert into Pharmacist values (52127, 767332);
insert into Pharmacist values (53573, 768329);
insert into Pharmacist values (51853, 745264);
insert into Pharmacist values (55246, 746261);
insert into Pharmacist values (53763, 725154);

insert into HealthcareProfessional values (51833, 'Aaron', 'Daniels', '#1150','4151 Hazelbridge Way', 'V6X4J7', '604-731-4522', '604-507-5070');
insert into HealthcareProfessional values (54328, 'May', 'Chu', '#1150','4151 Hazelbridge Way', 'V6X4J7', '604-775-8811', '604-507-5070');
insert into HealthcareProfessional values (52856, 'Angela', 'Summers', '112','3540 W 41st Ave', 'V6X4J7', '778-456-3282', '1-800-431-7206');
insert into HealthcareProfessional values (53647, 'Ying Yue', 'Wong', '112','3540 W 41st Ave', 'V6X4J7', '778-456-5285', '1-800-431-7206');
insert into HealthcareProfessional values (53176, 'Vanya', 'Preet', '112','3540 W 41st Ave', 'V6X4J7', '604-731-2411', '1-800-431-7206');

insert into LabTechnician values (51833, 836275);
insert into LabTechnician values (54328, 864271);
insert into LabTechnician values (52856, 812674);
insert into LabTechnician values (53647, 864527);
insert into LabTechnician values (53176, 896264);

insert into Patient values (32118954, 'Jonathan', 'Banks', '2144 Dunbar St.', 'V6B3N6', '604-731-2499', '778-456-2382');
insert into Patient values (12345678, 'Chris', 'Ellis', 'E-104-N, Pinehurst Apartments, 422 Maple St', 'V6B3N6', NULL, NULL);
insert into Patient values (29562774, 'David', 'Chen', '1114 Lameys Mill Rd.', 'V6S0J9', '604-731-8753', '604-731-9187');
insert into Patient values (31857372, 'Winny', 'Zhu', '4491 W 4th Ave', 'V6S0J9', '778-291-3753', '778-981-3722');
insert into Patient values (31274656, 'Antar', 'Atwal', 'Apt. 117, 8833 Hazelbridge Way', 'V6Y2V7', '604-233-4713', '604-233-8172');
insert into Patient values (32485737, 'Alex', 'Grossman', '271 Pike St.', '98226', '206-775-9312', '206-744-2713');
insert into Patient values (31572742, 'Mary', 'Cornwall', '4532 Lakeview Way', 'T5A0A1', '', '780-555-4031');

insert into Referral values (32118954, 52918, 54337, TO_DATE('2019-02-21', 'YYYY-MM-DD'));
insert into Referral values (12345678, 52918, 52731, TO_DATE('2018-05-01', 'YYYY-MM-DD'));
insert into Referral values (32485737, 52918, 52731, TO_DATE('2019-02-18', 'YYYY-MM-DD'));
insert into Referral values (31857372, 52918, 53833, TO_DATE('2018-12-11', 'YYYY-MM-DD'));
insert into Referral values (31274656, 52918, 53833, TO_DATE('2018-10-06', 'YYYY-MM-DD'));
insert into Referral values (12345678, 52918, 53833, TO_DATE('2018-12-11', 'YYYY-MM-DD'));
insert into Referral values (12345678, 52918, 54337, TO_DATE('2019-02-21', 'YYYY-MM-DD'));

insert into Medication values ('Albuterol', 'mg');
insert into Medication values ('Esomeprazole', 'mg');
insert into Medication values ('Fluticasone', 'mcg');
insert into Medication values ('Fluoxetine', 'mg');
insert into Medication values ('Pregabalin', 'mL');

insert into Prescription values (452855, 'Albuterol', 25, 100, 32118954, 54337, 55246, TO_DATE('2018-09-11','YYYY-MM-DD'), TO_DATE('2018-09-13','YYYY-MM-DD'));
insert into Prescription values (485274, 'Fluoxetine', 20, 300, 12345678, 52918, 53763, TO_DATE('2019-01-20','YYYY-MM-DD'), TO_DATE('2019-01-20','YYYY-MM-DD'));
insert into Prescription values (438245, 'Esomeprazole', 10, 40, 12345678, 52918, 53763, TO_DATE('2018-08-04','YYYY-MM-DD'), NULL);
insert into Prescription values (472596, 'Pregabalin', 85, 1, 12345678, 52918, 52127, TO_DATE('2018-03-15','YYYY-MM-DD'), TO_DATE('2018-03-21','YYYY-MM-DD'));
insert into Prescription values (492845, 'Fluticasone', 15, 100, 12345678, 52918, 52127, TO_DATE('2018-04-26','YYYY-MM-DD'), TO_DATE('2018-05-01','YYYY-MM-DD'));
insert into Prescription values (472947, 'Pregabalin', 85, 1, 31857372, 52918, 52127, TO_DATE('2018-03-15','YYYY-MM-DD'), TO_DATE('2018-03-21','YYYY-MM-DD'));
insert into Prescription values (427437, 'Fluticasone', 15, 100, 31274656, 52918, 52127, TO_DATE('2018-04-26','YYYY-MM-DD'), TO_DATE('2018-05-01','YYYY-MM-DD'));

insert into LabTest values (827432, 175.4, 70.8, 95.2, 104.6, 2.27, 3.51, 45.1, 195, 25.7, 0.75, 140, 3.8, 133.2, 12345678, 52918, 51833, TO_DATE('2019-01-29', 'YYYY-MM-DD'), NULL);
insert into LabTest values (882475, 132.0, 70.2, 61.8, 85.7, 2.33, 3.46, 40.2, 206, 17.2, 0.66, 140, 3.6, 121.0, 12345678, 52918, 52856, TO_DATE('2019-02-23', 'YYYY-MM-DD'), NULL);
insert into LabTest values (830485, 155.7, 85.7, 70.0, 113.2, 2.36, 3.58, 39.7, 173, 23.3, 0.70, 140, 3.7, 140.6, 12345678, 52918, 53176, TO_DATE('2018-11-05', 'YYYY-MM-DD'), TO_DATE('2018-11-06', 'YYYY-MM-DD'));
insert into LabTest values (865282, 200.8, 103.9, 96.9, 92.7, 2.21, 3.44, 48.3, 180, 20.1, 0.68, 140, 4.1, 135.9, 12345678, 52918, 53647, TO_DATE('2018-10-21', 'YYYY-MM-DD'), TO_DATE('2018-10-23', 'YYYY-MM-DD'));
insert into LabTest values (818673, 125.0, 63.5, 61.5, 99.6, 2.42, 3.20, 42.9, 215, 21.5, 0.72, 140, 4.0, 144.6, 12345678, 52918, 54328, TO_DATE('2018-12-13', 'YYYY-MM-DD'), TO_DATE('2018-12-17', 'YYYY-MM-DD'));

insert into ProvincialHealthPlan values (128473, 'BC Resident - MSP', TO_DATE('2018-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 32118954);
insert into ProvincialHealthPlan values (125724, 'BC Resident - MSP', TO_DATE('2018-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 29562774);
insert into ProvincialHealthPlan values (127828, 'BC Resident - Income Assistance', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 12345678);
insert into ProvincialHealthPlan values (121738, 'Out-of-Province', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 31274656);
insert into ProvincialHealthPlan values (126724, 'Out-of-Province', TO_DATE('2019-01-01','YYYY-MM-DD'), TO_DATE('2019-12-31','YYYY-MM-DD'), 31274656);

insert into ExtendedBenefitsPlan values (238948, 128473, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'N', 'N', 'N', 'N', 'N', 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2500.00, 463.00);
insert into ExtendedBenefitsPlan values (273284, 125724, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'Y', 'N', 'Y', 'Y', 'Y', 'Y', 2500.0, 0.0, 2500.00, 400.00, NULL, NULL, 2500.00, 0.0, 2500.0, 0.0, 2500.00, 463.00);
insert into ExtendedBenefitsPlan values (294742, 127828, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2021-01-01', 'YYYY-MM-DD'), 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 1000.0, 0.0, 1000.0, 225.50, 1000.0, 0.0, 1000.0, 125.25, 1000.0, 100.0, 2500.00, 225.00);
insert into ExtendedBenefitsPlan values (274633, 127828, TO_DATE('2018-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'Y', 'N', 'N', 'N', 'N', 'Y', 1000.0, 125.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1500.00, 500.00);
insert into ExtendedBenefitsPlan values (258291, 121738, TO_DATE('2019-01-01', 'YYYY-MM-DD'), TO_DATE('2020-01-01', 'YYYY-MM-DD'), 'N', 'N', 'N', 'N', 'N', 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2500.00, 0.00);

insert into Invoice values (119274, 32118954, 'Annual exam', TO_DATE('2018-11-02','YYYY-MM-DD'), TO_DATE('2018-12-02','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-27','YYYY-MM-DD'), 'Credit\Debit', 0.0, 628462, 128473);
insert into Invoice values (183746, 12345678, 'Annual exam', TO_DATE('2018-11-02','YYYY-MM-DD'), TO_DATE('2018-12-02','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-27','YYYY-MM-DD'), 'Credit\Debit', 0.0, 628462, 128473);
insert into Invoice values (148924, 32485737, 'Clinic visit', TO_DATE('2018-10-14','YYYY-MM-DD'), TO_DATE('2018-11-14','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 200.0, NULL, 121738);
insert into Invoice values (182747, 12345678, 'Clinic visit', TO_DATE('2018-10-14','YYYY-MM-DD'), TO_DATE('2018-11-14','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 200.0, NULL, 121738);
insert into Invoice values (152748, 31572742, 'Clinic visit', TO_DATE('2018-05-11','YYYY-MM-DD'), TO_DATE('2018-05-11','YYYY-MM-DD'), 'Paid', TO_DATE('2018-11-27','YYYY-MM-DD'), 'Cheque', 0.0, 628462, 126724);
insert into Invoice values (119396, 31857372, 'Orthodic insoles', TO_DATE('2019-01-13','YYYY-MM-DD'), TO_DATE('2019-03-13','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 265.50, NULL, 128473);
insert into Invoice values (137472, 12345678, 'Orthodic insoles', TO_DATE('2019-01-13','YYYY-MM-DD'), TO_DATE('2019-03-13','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 265.50, NULL, 128473);
insert into Invoice values (192745, 29562774, 'Massage re: back injury', TO_DATE('2019-01-04','YYYY-MM-DD'), TO_DATE('2019-02-04','YYYY-MM-DD'), 'Unpaid', NULL, NULL, 145.0, NULL, 127828);
