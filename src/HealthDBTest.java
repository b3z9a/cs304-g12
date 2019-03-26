import java.sql.*;
import java.util.*;

public class HealthDBTest{

  static HealthDBTest test;
  static HealthDB hdb;

  public static void main(String args[]) {
      test = new HealthDBTest();

      hdb = new HealthDB();
      hdb.connectToDB("ora_k1j8", "a30442115");

      // Test find methods. //
      /*
      System.out.println("Test findPatient");
      System.out.println(hdb.findPatient("2"));
      System.out.println(hdb.findPatient("3"));
      System.out.println("\n");

      System.out.println("Test findInvoice");
      System.out.println("  Expected: 32118954");
      System.out.println("  Actual: " + hdb.findPrescription("452855") + "\n");
      System.out.println("  Expected: ");
      System.out.println("  Actual: " + hdb.findPrescription("1234") + "\n");

      System.out.println("Test findTest");
      System.out.println("  Expected: 12345678");
      System.out.println("  Actual: " + hdb.findTest("830485") + "\n");
      System.out.println("  Expected: ");
      System.out.println("  Actual: " + hdb.findPrescription("1234") + "\n");

      System.out.println("Test findInvoice");
      System.out.println("  Expected: 12345678");
      System.out.println("  Actual: " + hdb.findInvoice("183746") + "\n");
      System.out.println("  Expected: ");
      System.out.println("  Actual: " + hdb.findPrescription("1234") + "\n");
      */

      // Test get methods //
      /*System.out.println("Test getPatients");
      test.printTuples(hdb.getPatients("John"));
      test.printTuples(hdb.getPatients("Mary"));
      test.printTuples(hdb.getPatients("john"));

      System.out.println("Test getPrescriptions");
      test.printTuples(hdb.getPrescriptions("1"));
      test.printTuples(hdb.getPrescriptions("2"));
      test.printTuples(hdb.getPrescriptions("3"));
      test.printTuples(hdb.getPrescriptions("4"));
      test.printTuples(hdb.getPrescriptions("pineapple"));

      System.out.println("Test getTests");
      test.printTuples(hdb.getTests("1"));
      test.printTuples(hdb.getTests("2"));
      test.printTuples(hdb.getTests("3"));
      test.printTuples(hdb.getTests("4"));
      test.printTuples(hdb.getTests("John"));

      System.out.println("Test getInvoices");
      test.printTuples(hdb.getInvoices("1"));
      test.printTuples(hdb.getInvoices("2"));
      test.printTuples(hdb.getInvoices("3"));
      test.printTuples(hdb.getInvoices("4"));
      test.printTuples(hdb.getInvoices("banana"));

      System.out.println("Test getReferrals");
      test.printTuples(hdb.getReferrals("1"));
      test.printTuples(hdb.getReferrals("2"));
      test.printTuples(hdb.getReferrals("3"));
      test.printTuples(hdb.getReferrals("4"));
      test.printTuples(hdb.getReferrals("peach"));

      System.out.println("Test getPlan");
      test.printTuple(hdb.getPlan("1"));
      test.printTuple(hdb.getPlan("2"));
      test.printTuple(hdb.getPlan("3"));
      test.printTuple(hdb.getPlan("4"));
      test.printTuple(hdb.getPlan("38583233"));
      test.printTuple(hdb.getPlan("kiwi"));

      System.out.println("Test getExtendedBenefits");
      test.printTuples(hdb.getExtendedBenefits("1"));
      test.printTuples(hdb.getExtendedBenefits("2"));
      test.printTuples(hdb.getExtendedBenefits("3"));
      test.printTuples(hdb.getExtendedBenefits("4"));
      test.printTuples(hdb.getExtendedBenefits("lychee"));

      System.out.println("Test getAmountOwing");
      System.out.println("PID=1 Expected: 300, Actual: " + hdb.getAmountOwing("1"));
      System.out.println("PID=2 Expected: 0, Actual: " + hdb.getAmountOwing("2"));
      System.out.println("PID=3 Expected: 350, Actual: " + hdb.getAmountOwing("3"));
      System.out.println("PID=4 Expected: 237.31, Actual: " + hdb.getAmountOwing("4"));

      System.out.println("Test getAmountOverdue");
      System.out.println("PID=1 Expected: 200, Actual: " + hdb.getOverdueAmountOwing("1"));
      System.out.println("PID=2 Expected: 0, Actual: " + hdb.getOverdueAmountOwing("2"));
      System.out.println("PID=3 Expected: 150, Actual: " + hdb.getOverdueAmountOwing("3"));
      System.out.println("PID=4 Expected: 37.31, Actual: " + hdb.getOverdueAmountOwing("4"));
      */
  }

  private void printTuples(ArrayList<ArrayList<String>> tuples){
      StringBuilder sb = new StringBuilder();
      for (ArrayList<String> list : tuples){
        for (String s : list){
          sb.append("'");
          sb.append(s);
          sb.append("', ");
        }
        sb.append("\n");
      }
      System.out.println(sb.toString());
  }

  private void printTuple(ArrayList<String> tuple){
      StringBuilder sb = new StringBuilder();
        for (String s : tuple){
          sb.append(" '");
          sb.append(s);
          sb.append("', ");
        }
      System.out.println(sb.toString());
  }

}
