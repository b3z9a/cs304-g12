/* TODO: Test inserts. */

insert into PostalCode values ('V6Z 1Y6', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6H 3N1', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6K 2G2', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6S 0J9', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6B 3N6', 'Vancouver', 'BC', 'Canada');
insert into PostalCode values ('V6Y 2V7', 'Richmond', 'BC', 'Canada');
insert into PostalCode values ('V6X 4J7', 'Richmond', 'BC', 'Canada');
insert into PostalCode values ('T5A 0A1', 'Edmonton', 'AB', 'Canada');
insert into PostalCode values ('98226', 'Seattle', 'WA', 'USA');

insert into HealthcareProfessional values (54337, 'Joel', 'Lewis', '306', '1081 Burrard St', 'V6Z 1Y6', '604-713-2291', '604-682-2344 x306');
insert into HealthcareProfessional values (52731, 'Melissa', 'Clark', '211', '1081 Burrard St', 'V6Z 1Y6', '604-779-3101', '604-682-2344 x211');
insert into HealthcareProfessional values (54781, 'Alice', 'Liu', 'E-117', '4480 Oak St', 'V6H 3N1', '604-785-4001', '604-875-2345 x33115');
insert into HealthcareProfessional values (53833, 'Aarav', 'Patel', 'A-220', '4480 Oak St', 'V6H 3N1', '778-885-3411', '604-682-2344 x33219');
insert into HealthcareProfessional values (52918, 'David', 'Chen', '', '2685 W Broadway', 'V6K 2G2', '604-668-4228', '604-731-9187');

insert into Doctor values (54337, 922789, 'Cardiologist');
insert into Doctor values (52731, 992833, 'Neurologist');
insert into Doctor values (54781, 946721, 'Pediatrician');
insert into Doctor values (53833, 996482, 'Surgeon');
insert into Doctor values (52918, 956432, 'Family Doctor');

insert into HealthcareProfessional values (52127, 'Grace', 'Wong', , '6060 Minoru Blvd', 'V6Y 2V7', '604-668-2187', '604-273-6187');
insert into HealthcareProfessional values (53573, 'Anna', 'Brown', , '6060 Minoru Blvd', 'V6Y 2V7', '604-668-2944', '604-273-6187');
insert into HealthcareProfessional values (51853, 'Micheal', 'Smith', , '1295 Seymour St', 'V6B 3N6', '604-785-8322', '604-801-5708');
insert into HealthcareProfessional values (55246, 'Richard', 'Zhang', , '1295 Seymour St', 'V6B 3N6', '604-913-2911 ', '604-801-5708');
insert into HealthcareProfessional values (53763, 'Tanvi', 'Chabra', , '5968 Webber Ln', 'V6S 0J9', '604-785-8763', '604-224-3086');

insert into Pharmacist values (52127, 767332);
insert into Pharmacist values (53573, 768329);
insert into Pharmacist values (51853, 745264);
insert into Pharmacist values (55246, 746261);
insert into Pharmacist values (53763, 725154);

insert into HealthcareProfessional values (51833, 'Aaron', 'Daniels', '#1150','4151 Hazelbridge Way', 'V6X 4J7', '604-731-4522', '604-507-5070');
insert into HealthcareProfessional values (54328, 'May', 'Chu', '#1150','4151 Hazelbridge Way', 'V6X 4J7', '604-775-8811', '604-507-5070');
insert into HealthcareProfessional values (52856, 'Angela', 'Summers', '112','3540 W 41st Ave', 'V6X 4J7', '778-456-3282', '1-800-431-7206');
insert into HealthcareProfessional values (53647, 'Ying Yue', 'Wong', '112','3540 W 41st Ave', 'V6X 4J7', '778-456-5285', '1-800-431-7206');
insert into HealthcareProfessional values (53176, 'Vanya', 'Preet', '112','3540 W 41st Ave', 'V6X 4J7', '604-731-2411', '1-800-431-7206');

insert into LabTechnician values (51833, 836275);
insert into LabTechnician values (54328, 864271);
insert into LabTechnician values (52856, 812674);
insert into LabTechnician values (53647, 864527);
insert into LabTechnician values (53176, 896264);

insert into Patient values (32118954, 'Jonathan', 'Banks', '2144 Dunbar St.', 'V6B 3N6', '604-731-2499', '778-456-2382');
insert into Patient values (31294771, 'Christopher', 'Ellis', 'E-104-N, Pinehurst Apartments, 422 Maple St', 'V6B 3N6', , ); 
insert into Patient values (29562774, 'David', 'Chen', '1114 Lameys Mill Rd.', 'V6S 0J9', '604-731-8753', '604-731-9187');
insert into Patient values (31857372, 'Winny', 'Zhu', '4491 W 4th Ave', 'V6S 0J9', '778-291-3753', '778-981-3722');
insert into Patient values (31274656, 'Antar', 'Atwal', 'Apt. 117, 8833 Hazelbridge Way', 'V6Y 2V7', '604-233-4713', '604-233-8172');
insert into Patient values (31274656, 'Alex', 'Grossman', '271 Pike St.', '98226', '206-775-9312', '206-744-2713');
insert into Patient values (31274656, 'Mary', 'Cornwall', '4532 Lakeview Way', 'T5A 0A', '', '780-555-4031');

insert into Referral values (32118954, 52918, 54337, '2019-02-21');
insert into Referral values (31294771, 52918, 52731, '2018-05-01');
insert into Referral values (31294771, 52918, 52731, '2019-02-18');
insert into Referral values (31857372, 52918, 53833, '2018-12-11');
insert into Referral values (31274656, 52918, 53833, '2018-10-06');

insert into Medication values ('Albuterol', 'mg');
insert into Medication values ('Esomeprazole', 'mg');
insert into Medication values ('Fluticasone', 'mcg');
insert into Medication values ('Fluoxetine', 'mg');
insert into Medication values ('Pregabalin', 'mL');

insert into Prescription values (452855, 'Albuterol', 25, 100, 32118954, 54337, 55246, '2018-09-11', '2018-09-13');
insert into Prescription values (485274, 'Fluoxetine', 20, 300, 31294771, 52918, 53763, '2019-01-20', '2019-01-20');
insert into Prescription values (427437, 'Esomeprazole', 10, 40, 31294771, 52918, 53763, '2018-08-04', );
insert into Prescription values (427437, 'Pregabalin', 85, 1, 31857372, 52918, 52127, '2018-03-15', '2018-03-21');
insert into Prescription values (427437, 'Fluticasone', 15, 100, 31274656, 52918, 52127, '2018-04-26', '2018-05-01');

insert into LabTest values (827432, 175.4, 70.8, 95.2, 104.6, 2.27, 3.51, 45.1, 195, 25.7, 0.75, 140, 3.8, 133.2, 31294771, 52918, 51833);
insert into LabTest values (882475, 132.0, 70.2, 61.8, 85.7, 2.33, 3.46, 40.2, 206, 17.2, 0.66, 140, 3.6, 121.0, 31294771, 52918, 52856);
insert into LabTest values (830485, 155.7, 85.7, 70.0, 113.2, 2.36, 3.58, 39.7, 173, 23.3, 0.70, 140, 3.7, 140.6, 31294771, 52918, 53176);
insert into LabTest values (865282, 200.8, 103.9, 96.9, 92.7, 2.21, 3.44, 48.3, 180, 20.1, 0.68, 140, 4.1, 135.9, 31294771, 52918, 53647);
insert into LabTest values (818673, 125.0, 63.5, 61.5, 99.6, 2.42, 3.20, 42.9, 215, 21.5, 0.72, 140, 4.0, 144.6, 31294771, 52918, 54328);

insert into ProvincialHealthPlan (128473, 'BC Resident - MSP', '2018-01-01', '2019-12-31', 32118954);
insert into ProvincialHealthPlan (125724, 'BC Resident - MSP', '2018-01-01', '2019-12-31', 29562774);
insert into ProvincialHealthPlan (127828, 'BC Resident - Income Assistance', '2019-01-01', '2019-12-31', 31294771);
insert into ProvincialHealthPlan (121738, 'Out-of-Province', '2019-01-01', '2019-12-31', 31274656);
insert into ProvincialHealthPlan (126724, 'Out-of-Province', '2019-01-01', '2019-12-31', 31274656);

insert into ExtendedBenefitsPlan (238948, '2019-01-01', '2020-01-01', 'N', 'N', 'N', 'N', 'N', 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2500.00, 463.00, 128473);
insert into ExtendedBenefitsPlan (273284, '2019-01-01', '2020-01-01', 'Y', 'N', 'Y', 'Y', 'Y', 'Y', 2500.0, 0.0, 2500.00, 400.00, NULL, NULL, 2500.00, 0.0, 2500.0, 0.0, 2500.00, 463.00, 125724);
insert into ExtendedBenefitsPlan (294742, '2019-01-01', '2021-01-01', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 1000.0, 0.0, 1000.0, 225.50, 1000.0, 0.0, 1000.0, 125.25, 1000.0, 100.0, 2500.00, 225.00, 127828);
insert into ExtendedBenefitsPlan (274633, '2018-01-01', '2020-01-01', 'Y', 'N', 'N', 'N', 'N', 'Y', 1000.0, 125.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1500.00, 500.00, 121738);
insert into ExtendedBenefitsPlan (258291, '2019-01-01', '2020-01-01', 'N', 'N', 'N', 'N', 'N', 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2500.00, 0.00, 126724);

insert into Invoice (119274, 'Annual exam', '2018-11-02', '2018-12-02', 'Paid', '2018-11-27', 0.0, 382742, 128473);
insert into Invoice (148924, 'Clinic visit', '2018-10-14', '2018-11-14', 'Unpaid', , 200.0, , 121738);
insert into Invoice (152748, 'Clinic visit', '2018-05-11', '2018-05-11', 'Paid', '2018-11-27', 0.0, 382742, 126724);
insert into Invoice (119396, 'Orthodic insoles', '2019-01-13', '2019-03-13', 'Unpaid', , 265.50, , 128473);
insert into Invoice (192745, 'Massage re: back injury', '2019-01-04', '2019-02-04', 'Unpaid', , 145.0, , 127828);
