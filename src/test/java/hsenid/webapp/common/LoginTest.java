package hsenid.webapp.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import hsenid.webapp.common.DBCon;
import hsenid.webapp.common.PropertyReader;
import hsenid.webapp.common.Login;
import hsenid.webapp.usermanagement.common.User;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class LoginTest {
    static Connection con;
    static ComboPooledDataSource dataSource;
    @DataProvider(name = "test1")
    public Object[][] users() {
        return new Object[][]{
                {"", "123", false},
                {"test", "", false},
                {" ", "abc", false},
                {"test", " ", false},
                {" ", " ", false},
                {"test", "123", true},
                {"test ", "123", false},
                {"test ", " 123", false},
                {" test", "123", false},
                {" test", " 123", false},
                {" test ", " 123 ", false},
                {"tESt", "123", false},
                {"kdm", "123", true},
                {"Kdm", "abc", false},
                {"kdm", "Abc", false},
                {"KDM", "ABC", false},
                {"kdm", "ABC", false},
                {"KDM", "abc", false},
                {"123", "test", false},
                {"KDM", "abc", false},
        };
    }

    @BeforeTest
    public void createCon() throws SQLException {
        System.out.println("Started Login Test.");
        PropertyReader propReader = new PropertyReader("system.properties");
        DBCon.createComboDataSource(propReader.readProperty("db.driver"), propReader.readProperty("db.host"), propReader.readProperty("db.database"), propReader.readProperty("db.user"), propReader.readProperty("db.password"));
        dataSource = DBCon.getComboDataSource();
        con = dataSource.getConnection();
        String query = "INSERT INTO user_cred (UserName,Pass,FirstName,DOB,Country,CityId,Email,ContactNo) VALUES ('temp',md5('123'),'test','1990-10-10','Sri Lanka',1,'asdasdas@asd.com',94778587663);";
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate(query);
    }

    @AfterTest
    public void closeCon() throws SQLException {
        String query = "DELETE FROM user_cred WHERE UserName='temp';";
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate(query);
        con.close();
        dataSource.close();
        System.out.println("\nFinished Login Test.");
    }

    @Test(dataProvider = "test1")
    public void testValidateByDb(String uname, String pass, boolean expected) throws Exception {
        boolean actual = new Login().validateByDb(new User(uname, pass));
        Assert.assertEquals(actual, expected, "Evaluate user validation results.");
        //System.out.println("Login Testing: UName:"+uname+" PWD:"+pass+"\tExpected:"+expected+"\tActual:\t"+b);
        //System.out.print(".");
    }
}