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

  insert into HealthcareProfessional values (53211, 'Aaron', 'Miller', '', '2685 W Broadway', 'V6Z1Y6', '604-775-3021', '604-882-4038');
  insert into HealthcareProfessional values (58377, 'James', 'Yu', '', '200-2475 Bayswater St', 'V6H3N1', '604-668-4522', '604-565-2111');
  insert into HealthcareProfessional values (52942, 'Alex', 'Chen', '200', '1755 1st Ave W', 'V6K2G2', '604-668-8323', '604-788-6569');
  insert into HealthcareProfessional values (54242, 'Vanessa', 'Burak', 'A110', '3261 5 Ave W', 'V6S0J9', '604-731-2388', '604-788-3076');
  insert into HealthcareProfessional values (54986, 'Sanja', 'Avinashi', '359', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');

  insert into Doctor values (53211, 932111, 'Family Doctor');
  insert into Doctor values (58377, 926433, 'Family Doctor');
  insert into Doctor values (52942, 985744, 'Family Doctor');
  insert into Doctor values (54242, 928355, 'Family Doctor');
  insert into Doctor values (54976, 924274, 'Family Doctor');

  insert into PostalCode values ('V5T3N4', 'Vancouver', 'BC', 'Canada');

  insert into HealthcareProfessional values (58739, 'Catherine', 'Cantarutti', '', '3080 Price Edward St', 'V5T3N4', '604-778-3848', '604-853-5833');
  insert into HealthcareProfessional values (58572, 'Kevin', 'Chan', '210', '3080 Price Edward St', 'V5T3N4', '604-744-2948', '604-593-5844');
  insert into HealthcareProfessional values (56483, 'Divi', 'Chandra', '', '3080 Price Edward St', 'V5T3N4', '604-565-9843', '250-857-3844');
  insert into HealthcareProfessional values (56837, 'Helen', 'Yu', 'B-210', '3080 Price Edward St', 'V5T3N4', '604-847-3883', '250-883-9853');
  insert into HealthcareProfessional values (55243, 'Yi Yueh', 'Cheng', '10', '3080 Price Edward St', 'V5T3N4', '604-722-8535', '250-573-8573');

  insert into Doctor values (58739, 939583, 'Anesthesiologist');
  insert into Doctor values (58572, 919384, 'Dermatologist');
  insert into Doctor values (56483, 995824, 'Gastroenterologist');
  insert into Doctor values (56837, 985723, 'Geriatric Medicine Specialist');
  insert into Doctor values (55243, 985737, 'Gynecologist');

  insert into HealthcareProfessional values (53948, 'Peter', 'Chiu', '', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
  insert into HealthcareProfessional values (53596, 'Bahar', 'Cinarli', '', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
  insert into HealthcareProfessional values (55284, 'Jeff', 'Coleman', '210', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
  insert into HealthcareProfessional values (55934, 'Nishi', 'Dhawan', '15', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');
  insert into HealthcareProfessional values (56868, 'Timothy', 'Doty', '', '2175 Salal Drive', 'V6B3N6', '604-731-9238', '250-778-3045');

  insert into Doctor values (53948, 938572, 'Hematologist');
  insert into Doctor values (53596, 959283, 'Heptologist');
  insert into Doctor values (55284, 969924, 'Neonatologist');
  insert into Doctor values (55934, 956384, 'Psychiatrist');
  insert into Doctor values (56868, 924858, 'Radiologist');

  insert into PostalCode values ('V5G2X6', 'Burnaby', 'BC', 'Canada');

  insert into HealthcareProfessional values (51837, 'Sanja', 'Avinashi', '220', '3935 Kincaid St', 'V5G2X6', '604-634-8573', '778-584-4835');
  insert into HealthcareProfessional values (53592, 'Sanja', 'Avinashi', '215', '3935 Kincaid St', 'V5G2X6', '604-634-8434', '778-573-5838');
  insert into HealthcareProfessional values (59434, 'Sanja', 'Avinashi', '233', '3935 Kincaid St', 'V5G2X6', '604-644-6832', '766-938-9671');
  insert into HealthcareProfessional values (56838, 'Sanja', 'Avinashi', '115', '3935 Kincaid St', 'V5G2X6', '604-644-6824', '855-365-8344');
  insert into HealthcareProfessional values (56992, 'Sanja', 'Avinashi', '101', '3935 Kincaid St', 'V5G2X6', '604-662-4823', '855-365-3843');

  insert into Doctor values (51837, 982743, 'Rheumatologist');
  insert into Doctor values (53592, 992847, 'Sleep Disorder Specialist');
  insert into Doctor values (59434, 973853, 'Surgeon');
  insert into Doctor values (56838, 923485, 'Urologist');
  insert into Doctor values (56992, 913958, 'Otolaryngologist');
