package hsenid.webapp.usermanagement.adduser;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import hsenid.webapp.common.DBCon;
import hsenid.webapp.common.Login;
import hsenid.webapp.common.PropertyReader;
import hsenid.webapp.usermanagement.common.User;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by hsenid on 5/31/16.
 */
public class CheckUserNameTest {
    static Connection con;
    static ComboPooledDataSource dataSource;

    @DataProvider(name = "userNameTester")
    public Object[][] userNames() {
        return new Object[][]{
                {"kdm", false},
                {"aaa", true},
                {"abc", false},
                {"KDM", false},
                {"kdM", false},
                {"Kdm", false},
                {"Abc", false},
                {"aBC", false},
                {"ttte", true},
                {"noadd", false},
                {"cc", false},
                {"ccc", true},
        };
    }

    @BeforeTest
    public void createCon() throws SQLException {
        System.out.println("Started check username Test.");
        PropertyReader propReader = new PropertyReader("system.properties");
        DBCon.createComboDataSource(propReader.readProperty("db.driver"), propReader.readProperty("db.host"), propReader.readProperty("db.database"), propReader.readProperty("db.user"), propReader.readProperty("db.password"));
        dataSource = DBCon.getComboDataSource();
        con = dataSource.getConnection();
        String query = "INSERT INTO user_cred (UserName,Pass,FirstName,DOB,Country,CityId,Email,ContactNo) VALUES ('testuser',md5('123'),'test','1990-10-10','Sri Lanka',1,'asdasdas@asd.com',94778587663);";
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate(query);
    }

    @AfterTest
    public void closeCon() throws SQLException {
        String query = "DELETE FROM user_cred WHERE UserName='testuser';";
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate(query);
        con.close();
        dataSource.close();
        System.out.println("\nFinished check username Test.");
    }

    @Test(dataProvider = "userNameTester")
    public void testValidateByDb(String uname, boolean expected) throws Exception {
        boolean actual = new CheckUserName().validateUserName(uname);
        Assert.assertEquals(actual, expected, "Evaluate username validation results.");
        //System.out.println("Login Testing: UName:"+uname+"\tExpected:"+expected+"\tActual:\t"+actual);
        //System.out.print(".");
    }
}
