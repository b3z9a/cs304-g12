/* ----- IMPLEMENTED -----*/
/* SQL query for patient info for all views. */
select p.firstName, p.lastName, p.patientID, p.street, pc.city, pc.province,
pc.postalcode, pc.country, p.homePhone, p.mobilePhone
from patient p, postalcode pc
where p.postalcode = pc.postalcode and p.patientID = 'PASSED PARAMETER';

/* ----- IMPLEMENTED -----*/
/* SQL query for prescription info for doctor and pharmacist views. */
select pr.prescriptionID, pr.prescribedDate, m.medication, pr.dosage, m.dosageMeasure,
pr.quantity, pr.status
from prescription pr, medication m
where pr.patientID = 'PASSED PARAMETER' and pr.medication = m.medication;

/* ----- IMPLEMENTED -----*/
/* SQL query for test results for doctor, lab tech, and admin views. */
select testID, orderedDate, performedDate
from LabTest
where patientID = 'PASSED PARAMETER';

/* ----- IMPLEMENTED -----*/
/* SQL query for referrals for doctor and admin views. */
select h.firstName, h.lastName, d.specialization, r.referredDate
from Referral r, HealthcareProfessional h, Doctor d
where r.patientID = 'PASSED PARAMETER' and r.referreeHID = h.HID and d.HID = h.hid;

/* ----- IMPLEMENTED -----*/
/* SQL query for provincial plan info for admin view. */
select planID, planType, startDate, endDate
from ProvincialHealthPlan
where patientID = 'PASSED PARAMETER';

/* SQL query for extended benefits plan info for admin view. */
select chiropractic, chiroracticAnnualLimit, chiropracticYTD,
       physiotherapy, physiotherapyAnnualLimit, physiotherapyYTD,
       nonSurgicalPodiatry, nonSurgicalPodiatryAnnualLimit, nonSurgicalPodiatryYTD,
       acupuncture, acupunctureAnnualLimit, acupunctureYTD,
       medication, medicationAnnualLimit, medicationYTD
from ExtendedBenefitsPlan
where patientID = 'PASSED PARAMETER';

/* SQL query for patient invoices info for admin view. */
/* First find total unpaid and total overdue*/
select sum(amountOwing)
from Invoice
where patientID = 'PASSED PARAMETER' and paymentStatus = 'Unpaid';

declare today = GETDATE();

select sum(amountOwing)
from Invoice
where patientID = 'PASSED PARAMETER' and paymentStatus = 'Unpaid' and dueDate < today;

select invoiceID, invoiceItem, creationDate, dueDate, paymentStatus, amountOwing
from Invoice
where patientID = 'PASSED PARAMETER';

/* SQL query for doctors info for doctor find doctor view. */
select h.firstName, h.lastName, h.street, pc.city, pc.postalcode, pc.province
from Doctor d, HealthcareProfessional h, PostalCode pc
where d.hid = h.hid and pc.postalcode = h.postalcode and d.specialization = 'PASSED SPECIALIZATION' and p.city = 'PASSED CITY';
