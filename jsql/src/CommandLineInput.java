///the command line input

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class CommandLineInput {

    private Sql_codes sql;
    private dbConsole console;
    private String url;
    private String user;
    private String pw;

    public CommandLineInput(String url,String user,String pw){
        this.sql = new Sql_codes();
        this.console = new dbConsole();
        this.url = url;
        this.user = user;
        this.pw = pw;
        console.ignoreWarnings();
        //console.createDB(this.url,this.user,this.pw,this.sql.create_table_accounts());
        //console.createDB(this.url,this.user,this.pw,this.sql.create_table_stats());
        //console.createUsers(this.url,this.user,this.pw);
        //console.createStats(this.url,this.user,this.pw);
    }

    public void start() throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement stmt = null;
        int id;
        ArrayList<String> common = new ArrayList<String>();
        ArrayList<String> rare = new ArrayList<String>();
        ArrayList<String> epic = new ArrayList<String>();
        ArrayList<String> legendary = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        Hearthstone h = new Hearthstone();
        System.out.println("type 'help' for a list of commands.");
        System.out.println("INPUT: ");
        String input = sc.nextLine();
        while (!input.equals("exit")) {
            System.out.println("Your Input: " + input);

            if (input.equals("help")) {
                System.out.println("This is the list of command lines.");
                System.out.println("exit        | exit the program");
                System.out.println("login       | login to the game");

            } else if (input.equals("login")) {

                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection(this.url, this.user, this.pw);
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");


                stmt = c.prepareStatement("SELECT PLAYER_ID,NAME,PASSWORD FROM ACCOUNTS WHERE NAME = ? ");
                System.out.println("Please type in your username:");
                stmt.setString(1, sc.nextLine());
                ResultSet rs = stmt.executeQuery();
                rs.next();
                id = rs.getInt("player_id");
                stmt = c.prepareStatement("SELECT PLAYER_ID,NAME,PASSWORD FROM ACCOUNTS WHERE PASSWORD = ? ");
                System.out.println("Please enter your password:");
                stmt.setString(1, sc.nextLine());
                ResultSet rt = stmt.executeQuery();
                rt.next();
                int id2 = rt.getInt("player_id");
                if (id == id2) {
                    //if it matches, pass
                    System.out.println("You have logged in!");
                }
                /*NIGEL PLZ HELP*/
                stmt = c.prepareStatement("SELECT DOLLAR FROM CREDIT_CARD WHERE ID = 3 ");
                stmt.setString(1, sc.nextLine());
                ResultSet dr = stmt.executeQuery();
                double dollar = dr.getDouble("dollar");
                stmt = c.prepareStatement("SELECT CARD_NAME FROM CARDS WHERE NAME LIKE 'Common' ");
                stmt.setString(1, sc.nextLine());
                ResultSet cm = stmt.executeQuery();
                while(cm.next())
                    common.add(cm.getString("card_name"));
                System.out.println(Arrays.toString(common.toArray()));
                stmt = c.prepareStatement("SELECT CARD_NAME FROM CARDS WHERE NAME LIKE 'Rare' ");
                stmt.setString(1, sc.nextLine());
                ResultSet rr = stmt.executeQuery();
                while(rr.next())
                    rare.add(rr.getString("card_name"));
                System.out.println(Arrays.toString(common.toArray()));
                stmt = c.prepareStatement("SELECT CARD_NAME FROM CARDS WHERE NAME LIKE 'Epic' ");
                stmt.setString(1, sc.nextLine());
                ResultSet ep = stmt.executeQuery();
                while(ep.next())
                    epic.add(rs.getString("card_name"));
                System.out.println(Arrays.toString(common.toArray()));
                stmt = c.prepareStatement("SELECT CARD_NAME FROM CARDS WHERE NAME LIKE 'Legendary' ");
                stmt.setString(1, sc.nextLine());
                ResultSet lg = stmt.executeQuery();
                while(lg.next())
                    legendary.add(rs.getString("card_name"));
                System.out.println(Arrays.toString(common.toArray()));
                rs.close();
                stmt.close();
                cm.close();
                rr.close();
                ep.close();
                lg.close();
                dr.close();
                c.close();

                while (true) {
                    //remember, when the exit is typed, it ends the game
                    input = sc.nextLine();
                    System.out.println("Your Input: "+input);
                    int pack = 0;
                    if(input.equals("help")){
                        System.out.println("This is the list of command lines.");
                        System.out.println("exit        | exit the program");
                        System.out.println("logout      | logout of the game");
                        System.out.println("buy packs   | purchase packs for opening");
                        System.out.println("open packs  | open purchased packs");

                        //System.out.println("cards       | show list of cards");
                    }
                    else if(input.equals("buy packs")){
                        System.out.println("How many packs would you like to buy? (1 pack = $1");
                        int packs = sc.nextInt();
                        if(packs < dollar){
                            pack++;
                        }
                        else
                            System.out.println("Error: Insufficient Funds");
                    }
                    else if(input.equals("open packs")){
                        while(pack > 0){
                            System.out.println(Arrays.toString(h.openPack(common, rare, epic, legendary).toArray()));
                        }
                    }
                    //else if(input.equals("cards")){console.selectDB(this.url,this.user,this.pw,"SELECT * FROM CARDS;");}
                    else if (input.equals("exit")) {
                        System.out.println("Program finished!");
                        System.exit(0);
                    }
                    else if(input.equals("logout")){break;}
                    else{System.out.println("WRONG INPUT!");}


                }
            }else{System.out.println("WRONG INPUT!");}
            input = sc.nextLine();

        }
        System.out.println("Program finished!");
    }


}
