package hsenid.webapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by hsenid on 5/18/16.
 */
public class GetUserPermissions extends HttpServlet{

    private static final Logger log = LogManager.getLogger(GetUserPermissions.class);
    private String permissionQuery=null;
    private Connection con=null;
    private PreparedStatement preState=null;
    private ResultSet permissions=null;
    private ArrayList<String> permissionList=null;
    JsonObject jsonObject=null;
    JsonArray jsonArray=null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        String userName=req.getParameter("userName");
        User user=new User(userName);
        permissionList=getPermissionList(user);
        jsonArray=new JsonArray();
        for (int i = 0; i < permissionList.size(); i++) {
            jsonArray.add(permissionList.get(i));
        }
        out.println(jsonArray);
    }

    public ArrayList<String> getPermissionList(User user){
        ArrayList<String> perlist =new ArrayList<>();
        try {
            con=DBCon.getComboDataSource().getConnection();
            permissionQuery="SELECT Name FROM permission WHERE ID IN (SELECT permission_id FROM group_permission WHERE group_id IN (SELECT group_id FROM user_group ug,userdata.user_cred uc WHERE ug.user_id=uc.ID AND uc.UserName=\'"+user.getUserName()+"\'));";
            preState=con.prepareStatement(permissionQuery);
            permissions=preState.executeQuery();
            while (permissions.next()){
                perlist.add(permissions.getString("Name"));
            }
        } catch (SQLException e) {
            log.error("Error returning the permissions. "+e);
        }finally {
            try {
                con.close();
                preState.close();
                permissions.close();
            } catch (SQLException e) {
                log.error("Error while closing connection related objects when returning the permissions. "+e);
            }
        }
        return perlist;
    }
}
