import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * Name: dbConsole.java
 * Description: dbConsole is a collection of SQL statements
 *
 * @author Aidan Rubenstein
 * Nigel Kokott
 * Bahdah Shin
 */


public class dbConsole {


    public void ignoreWarnings() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }

    }



    /**
     * The following Java program will be used to create a table in previously
     * opened database. Make sure you do not have this table already in your
     * target database.
     **/
    public void createDB(String url, String user, String password, String sql) {
        System.out.println("BAHDAH_DEBUG: creating DB!");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, password);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");


    }


    /**
     * The following Java program shows how we can create records in our
     * hearthstone table created
     * **/
    public void insertDB(String url, String user,String password, String sql){
        System.out.println("BAHDAH_DEBUG: inserting DB!");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");

    }

    /**
     * The following Java program shows how we can fetch and display        -make the table pretty
     * records from our hearthstone table created
     * **/
    public void selectDB(String url, String user,String password, String sql){
        System.out.println("BAHDAH_DEBUG: selecting DB!");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCol = rsmd.getColumnCount();

            //the header
            for(int i = 1; i<= numCol; i++){
                System.out.print("  |  "+rsmd.getColumnName(i));
            }
            System.out.println("  |  ");
            //the body
            while(rs.next()) {
                for (int i = 1; i <= numCol; i++) {
                    if(rsmd.getColumnTypeName(i).equals("bpchar") || rsmd.getColumnTypeName(i).equals("text")){
                        System.out.print("  |  "+rs.getString(rsmd.getColumnName(i)));
                    }else if(rsmd.getColumnTypeName(i).equals("int4")){
                        System.out.print("  |  "+rs.getInt(rsmd.getColumnName(i)));
                    }
                }
                System.out.println("  |  ");
            }


            /**
             while ( rs.next() ) {
             int id = rs.getInt("id");
             String  name = rs.getString("name");
             int age  = rs.getInt("age");
             String  address = rs.getString("address");
             float salary = rs.getFloat("salary");
             System.out.println( "ID = " + id );
             System.out.println( "NAME = " + name );
             System.out.println( "AGE = " + age );
             System.out.println( "ADDRESS = " + address );
             System.out.println( "SALARY = " + salary );
             System.out.println();
             }
             **/
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }


    /**
     * The following Java program shows how we can create records in our
     * hearthstone table created
     **/
    public void createUsers(String url, String user, String password) {
        System.out.println("BAHDAH_DEBUG: inserting DB!");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "DROP ACCOUNT";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO ACCOUNTS (PLAYER_ID,NAME,EMAIL,PASSWORD) "
                    + "VALUES (1, 'Paul', 'pap1534@gmail.com', 'D0gg0' );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO ACCOUNTS (PLAYER_ID,NAME,EMAIL,PASSWORD) "
                    + "VALUES (2, 'Allen', 'lol8008@gmail.com', 'Password123' );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO ACCOUNTS (PLAYER_ID,NAME,EMAIL,PASSWORD) "
                    + "VALUES (3, 'Teddy', 'non3434@gmail.com', 'Jellyfish' );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO ACCOUNTS (PLAYER_ID,NAME,EMAIL,PASSWORD) "
                    + "VALUES (4, 'Mark', 'sos4319@gmail.com', 'California ' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");

    }

    public void createStats(String url, String user, String password) {
        System.out.println("BAHDAH_DEBUG: inserting DB!");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            Sql_codes sql_code = new Sql_codes();

            stmt = c.createStatement();
            String sql = sql_code.insert_row_stats(1,88,22,88.0/22.0,"Druid");
            stmt.executeUpdate(sql);
            sql = sql_code.insert_row_stats(2,500,231,500.0/231.0,"Mage");
            stmt.executeUpdate(sql);
            sql = sql_code.insert_row_stats(3,20,53,20.0/53.0,"Rogue");
            stmt.executeUpdate(sql);
            sql = sql_code.insert_row_stats(4,749,923,746.0/923.0 ,"Warrior");

            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");

    }

    public int selectUser(String url, String user, String pw, String given) {
        Connection c = null;
        PreparedStatement stmt = null;
        int id = 0;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, pw);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement("SELECT PLAYER_ID,NAME,PASSWORD FROM ACCOUNTS WHERE NAME = ? ");
            stmt.setString(1, given);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            id = rs.getInt("player_id");

            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Login Failed");
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return id;
    }

    public String selectPass(String url, String user, String pw, int given) {
        Connection c = null;
        PreparedStatement stmt = null;
        String pass = "";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, pw);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement("SELECT PLAYER_ID,NAME,PASSWORD FROM ACCOUNTS WHERE PLAYER_ID = ? ");
            stmt.setInt(1, given);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            pass = rs.getString("password");
            String name = rs.getString("name");
            int id = rs.getInt("player_id");
            System.out.println("ID = " + id);
            System.out.println("NAME = " + name);
            System.out.println("Password = " + pass);
            System.out.println();

            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return pass;
    }

    public void insertCredit_card(String url, String user, String password){
        System.out.println("BAHDAH_DEBUG: inserting DB!");

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            Sql_codes sql_code = new Sql_codes();

            //"int player_id, float dollar, String card_num, String exp, String s_code"
            String sql = sql_code.insert_row_credit_card(1,100.00,"3847385948573078","12/23","234");
            stmt.executeUpdate(sql);
            sql = sql_code.insert_row_credit_card(2,200.00,"3859486049683960","04/14","748");
            stmt.executeUpdate(sql);
            sql = sql_code.insert_row_credit_card(3,53.23,"3859386904960397","03/07","275");
            stmt.executeUpdate(sql);
            sql = sql_code.insert_row_credit_card(4,102342323.99,"2968493806958497","12/01","390");
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");


    }
}