package totapi.test;

public class TestDriver {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
