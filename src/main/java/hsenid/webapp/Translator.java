package hsenid.webapp;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class Translator extends HttpServlet {

    final String KEY = "trnsl.1.1.20160310T063945Z.14945888ac849b23.fc507cddeb7ec9d96e1255e0a348b1b4a076f9c3";
    String auto_detect = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String translated_text = "Translated text";
        req.setCharacterEncoding("UTF-8");
        String from_lang = req.getParameter("fromlang");
        String to_lang = req.getParameter("tolang");
        String from_text = req.getParameter("fromtext");
        auto_detect = req.getParameter("autodetect");

        try {
            translated_text = Translate(from_lang, to_lang, from_text);
            ArrayList<String> list = LoadLanguages();
            req.setAttribute("langs", list);
            req.setAttribute("final_result", translated_text);
            req.setAttribute("fromlang", from_lang);
            req.setAttribute("tolang", to_lang);
            req.setAttribute("fromtext", from_text);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
            rd.forward(req, resp);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * @return Language list will return as an string arraylist
     * @throws Exception
     */
    public ArrayList<String> LoadLanguages() throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        String url = "https://translate.yandex.net/api/v1.5/tr/getLangs?key=" + KEY + "&ui=en";
        Document document = URLProcessor(url);
        NodeList nodeList = document.getElementsByTagName("Item");
        for (int x = 0, size = nodeList.getLength(); x < size; x++) {
            list.add(nodeList.item(x).getAttributes().getNamedItem("value").getNodeValue());
        }
        return list;
    }

    /**
     * @param from_lang Language of the text we need to translate
     * @param to_lang Language of the translated text
     * @param from_text Text we need to translate
     * @return Returns a string contains the translated text
     * @throws Exception
     */
    public String Translate(String from_lang, String to_lang, String from_text) throws Exception {
        String text = null;
        String url;
        if ("1".equals(auto_detect)) {
            url = "https://translate.yandex.net/api/v1.5/tr/translate?key=" + KEY + "&lang=" + to_lang + "&text=" + from_text;
        } else {
            url = "https://translate.yandex.net/api/v1.5/tr/translate?key=" + KEY + "&lang=" + from_lang + "-" + to_lang + "&text=" + from_text;
        }
        Document document = URLProcessor(url);
        NodeList nodeList = document.getElementsByTagName("Translation");
        text = nodeList.item(0).getTextContent();
        return text;
    }

    /**
     * @param url URL of the website taking XML data
     * @return Returns a document contains the data extracted form the passed
     * website
     * @throws Exception
     */
    public Document URLProcessor(String url) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        InputStream stream = response.getEntity().getContent();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(stream);
        stream.close();
        return document;
    }
}
