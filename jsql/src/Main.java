
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;

public class Main {

    private static String URL   = "jdbc:postgresql://reddwarf.cs.rit.edu/p32004k";
    private static String USER  = "p32004k";
    private static String PW    = "pheilieF6shoFu0aifoN";

    private static String URL2  = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER2 = "postgres";
    private static String PW2   = "123";

    private static String API_URL = "https://omgvamp-hearthstone-v1.p.mashape.com/cards";
    private static String HEADER_NAME = "X-Mashape-Key";
    private static String HEADER_VALUE = "0Oz4GQhjDkmshumgfASMU0rz3vfmp1GVn24jsnKTck3CKX3EJn";




    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // write your code here
        System.out.println( "Starting Program..." );
        Font font = new Font();
        //font.welcome_print();

        dbConsole db = new dbConsole();
        Sql_codes sql = new Sql_codes();
        db.ignoreWarnings();
        //db.createDB(URL,USER,PW,sql.create_table_stats());
        //db.createStats(URL,USER,PW);
        //db.createDB(URL,USER,PW,sql.create_table_credit_card());
        //db.insertCredit_card(URL,USER,PW);

        CommandLineInput input = new CommandLineInput(URL,USER,PW);
        input.start();







    }


}
