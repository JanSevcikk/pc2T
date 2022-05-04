import java.sql.*;


public class DbSql {
    private Connection conn;
    //pripojení sql
    public boolean Connect() {
        conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:students.db");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
   
    public void Disconnect() throws SQLException {
        if(conn != null)
        {
            conn.close();
        }
    }
    // vytvoření tab s tudents a grades pokud nejsou
     //Vytvorenie tabuliek students a grades ak neexistuju
     //students - id, name, surname, birthday, type -> id je Primary key
     //grades - id, grade, num -> num je primary key
     
    public boolean CreateTable() throws SQLException {
        if(conn == null)
            return false;
        String studentsTable = "CREATE TABLE IF NOT EXISTS students("+"id integer PRIMARY KEY,"+"name varchar(255) NOT NULL," +
                ""+"surname varchar(255) NOT NULL,"+"birthday text NOT NULL,"+"type varchar(20) NOT NULL"+");";
        String gradeTable ="CREATE TABLE IF NOT EXISTS grades(id integer,grade integer,num integer,PRIMARY KEY (num), FOREIGN KEY (id) REFERENCES students (id) ON DELETE CASCADE ON UPDATE NO ACTION)";
        Statement stmt = conn.createStatement();
        stmt.execute(studentsTable);
        stmt.execute(gradeTable);
        return true;
    }
   
    public void AddStudent(int ID, String name, String surname, int[] birthday, String type)
    {
        String sql = "INSERT OR IGNORE INTO students(id,name,surname,birthday,type)VALUES(?,?,?,?,?)";
        try
        {
            String date = birthday[2]+"-"+birthday[1]+"-"+birthday[0];
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,surname);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5,type);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void AddGrades(int ID, int grade, int i)
    {
        String sql = "INSERT OR REPLACE INTO grades(id,grade,num)VALUES(?,?,?)";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            preparedStatement.setInt(2,grade);
            preparedStatement.setInt(3,i);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // nacteni studentu z tabulky do treemap db běžící v programu
    
    public  void LoadStudent(Db db)
    {
        String sql = "SELECT id, name, surname, birthday, type FROM students";
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
             
                //sql uklada jako rok,mesic, den takže je nutné rozdělit do stringu a prevod do int
                String[] date = rs.getString("birthday").split("-");
                int[] datum = {Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])};
                String type = rs.getString("type");
                db.setPrvkyDatabaze(id,name,surname,datum[0],datum[1],datum[2],type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //nacteni znamek do array dle id 
    public void LoadGrades(int ID, Db db)
    {
        String sql = "SELECT grade FROM grades WHERE id=?";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                db.addZnamku(ID,rs.getInt("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void DeleteStudent(int ID)
    {
        String sql = "DELETE FROM students WHERE id=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void DeleteGrades(int ID)
    {
        String sql = "DELETE FROM grades WHERE id=?";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}