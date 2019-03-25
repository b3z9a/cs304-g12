import java.sql.*;
import java.util.*;

public class HealthDBTest{

  static HealthDBTest test;
  static HealthDB hdb;

  public static void main(String args[]) {
      test = new HealthDBTest();

      hdb = new HealthDB();
      hdb.connectToDB("ora_k1j8", "a30442115");

      test.findPatient();

      System.out.println("Test findInvoice");
      System.out.println("  Expected: 32118954");
      System.out.println("  Actual: " + hdb.findPrescription("452855") + "\n");

      System.out.println("Test findTest");
      System.out.println("  Expected: 12345678");
      System.out.println("  Actual: " + hdb.findTest("830485") + "\n");

      System.out.println("Test findInvoice");
      System.out.println("  Expected: 12345678");
      System.out.println("  Actual: " + hdb.findInvoice("183746") + "\n");

  }

  private void printTuples(ArrayList<ArrayList<String>> tuples){
      StringBuilder sb = new StringBuilder();
      for (ArrayList<String> list : tuples){
        for (String s : list){
          sb.append(s);
          sb.append(", ");
        }
        sb.append("\n");
      }
      System.out.println(sb.toString());
  }

  private void findPatient(){
    ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    ArrayList<String> tuple = hdb.findPatient("12345678");
    list.add(tuple);
    test.printTuples(list);
  }
}
