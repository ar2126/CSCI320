class Sql_codes {

    String create_table_accounts(){
        String sql = "CREATE TABLE ACCOUNTS " +
                    "(PLAYER_ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL UNIQUE, " +
                    " EMAIL        TEXT, " +
                    " PASSWORD         TEXT)";
        return sql;
    }

    String create_table_stats(){
        String sql = "CREATE TABLE STATS " +
                "(PLAYER_ID INT PRIMARY KEY     NOT NULL," +
                " WINS           INT    NOT NULL, " +
                "LOSES        INT,"+
                "WIN_RATIO        DECIMAL,"+
                "COMMON_DECK    TEXT"+
                " )";
        return sql;
    }


    String insert_row_stats(int player_id, int wins, int loses, double win_ratio, String common_deck){
        String sql = "INSERT INTO STATS (PLAYER_ID,WINS,LOSES,WIN_RATIO,COMMON_DECK) "
                +"VALUES ("+player_id+", "+wins+", "+loses+", "+win_ratio+
                ",'"+common_deck.replaceAll("'","''")+"');";
        return sql;
    }

    String create_table_credit_card(){
        String sql = "CREATE TABLE CREDIT_CARD"+
                "(PLAYER_ID INT PRIMARY KEY NOT NULL,"+
                "DOLLAR     DECIMAL,"+
                "CARD_NUM    TEXT,"+
                "EXP        TEXT,"+
                "S_CODE     TEXT"+
                " )";
        return sql;

    }

    String insert_row_credit_card(int player_id, double dollar, String card_num, String exp, String s_code){
        String sql = "INSERT INTO CREDIT_CARD (PLAYER_ID, DOLLAR, CARD_NUM, EXP, S_CODE) "
                +"VALUES ("+player_id+", "+dollar+", '"+card_num+"', '"+exp+"', '"+s_code+"');";
        return sql;
    }

}
