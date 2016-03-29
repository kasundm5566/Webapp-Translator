package hsenid.webapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    @DataProvider(name = "test1")
    public Object[][] users() {
        return new Object[][]{{"abc", "456", false}, {"kdm", "abc", true}, {"test", "123", true}};
    }

    @BeforeTest
    public void createCon() throws SQLException {
        PropertyReader propReader = new PropertyReader("system.properties");
        Connection con;
        DBCon.CreateConnection(propReader.readProperty("db.host"), propReader.readProperty("db.database"), propReader.readProperty("db.user"), propReader.readProperty("db.password"));
        con = DBCon.getConnection();
        String query = "INSERT INTO user_cred (Name,Pass) VALUES ('test',md5('123'));";
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate(query);
    }

    @AfterTest
    public void closeCon() throws SQLException {
        Connection con;
        con = DBCon.getConnection();
        String query = "DELETE FROM user_cred WHERE Name='test';";
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate(query);
        DBCon.getConnection().close();
    }

    @Test(dataProvider = "test1")
    public void testValidateByDB(String uname, String pass, boolean expected) throws Exception {
        boolean b = Login.ValidateByDB(new User(uname, pass));
        Assert.assertEquals(b, expected);
    }
}
