package hsenid.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Created by hsenid on 3/10/16.
 */
public class Translator extends HttpServlet {

    String key = "trnsl.1.1.20160310T063945Z.14945888ac849b23.fc507cddeb7ec9d96e1255e0a348b1b4a076f9c3";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String translated_text =null;
        String from_lang = req.getParameter("fromlang");
        String to_lang = req.getParameter("tolang");
        String from_text = req.getParameter("fromtext");
        
        try {
            translated_text = Translate(from_lang, to_lang, from_text);
            ArrayList<String> list = LoadLanguages();
            req.setAttribute("langs", list);
            req.setAttribute("final_result", translated_text);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
            rd.forward(req, resp);
        } catch (Exception ex) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public ArrayList<String> LoadLanguages() throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse("https://translate.yandex.net/api/v1.5/tr/getLangs?key=" + key + "&ui=en");
        NodeList nodeList = document.getElementsByTagName("Item");
        for (int x = 0, size = nodeList.getLength(); x < size; x++) {
            list.add(nodeList.item(x).getAttributes().getNamedItem("value").getNodeValue());
        }
        return list;
    }

    public String Translate(String from_lang, String to_lang, String from_text) throws Exception {
        String text = null;
        String url = "https://translate.yandex.net/api/v1.5/tr/translate?key=" + key + "&lang=" + from_lang + "-" + to_lang + "&text=" + from_text;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(url);
        NodeList nodeList = document.getElementsByTagName("Translation");
        text = nodeList.item(0).getTextContent();
        return text;
    }
}
