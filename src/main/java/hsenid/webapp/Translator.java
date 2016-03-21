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
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class Translator extends HttpServlet {

    ContextListener cl = new ContextListener();
    final String KEY = "trnsl.1.1.20160310T063945Z.14945888ac849b23.fc507cddeb7ec9d96e1255e0a348b1b4a076f9c3";
    String autoDetect = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String translated_text = "Translated text";
        req.setCharacterEncoding("UTF-8");
        String fromlang = req.getParameter("fromlang");
        String tolang = req.getParameter("tolang");
        String fromtext = req.getParameter("fromtext");
        autoDetect = req.getParameter("autodetect");

        try {
            translated_text = Translate(fromlang, tolang, fromtext);
            ArrayList<String> list = LoadLanguages();
            req.setAttribute("langs", list);
            req.setAttribute("final_result", translated_text);
            req.setAttribute("fromlang", fromlang);
            req.setAttribute("tolang", tolang);
            req.setAttribute("fromtext", fromtext);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
            rd.forward(req, resp);
        } catch (Exception ex) {
            throw new ServletException("Something went wrong...");
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
//        url = cl.getDbUrl() +
        Document document = URLProcessor(url);
        NodeList nodeList = document.getElementsByTagName("Item");
        for (int x = 0, size = nodeList.getLength(); x < size; x++) {
            list.add(nodeList.item(x).getAttributes().getNamedItem("value").getNodeValue());
        }
        return list;
    }

    /**
     * @param fromLang Language of the text we need to translate
     * @param toLang   Language of the translated text
     * @param fromText Text we need to translate
     * @return Returns a string contains the translated text
     * @throws Exception
     */
    public String Translate(String fromLang, String toLang, String fromText) throws Exception {
        String text = null;
        String url;
        if ("1".equals(autoDetect)) {
            url = "https://translate.yandex.net/api/v1.5/tr/translate?key=" + KEY + "&lang=" + toLang + "&text=" + fromText;
        } else {
            url = "https://translate.yandex.net/api/v1.5/tr/translate?key=" + KEY + "&lang=" + fromLang + "-" + toLang + "&text=" + fromText;
        }
        url=url.replaceAll(" ", "%20");
        Document document = URLProcessor(url);
        NodeList nodeList = document.getElementsByTagName("Translation");
        text = nodeList.item(0).getTextContent();
        return text;
    }

    /**
     * @param url URL of the website to extract XML data
     * @return Returns a document contains the data extracted form the passed website
     * @throws Exception
     */
    public Document URLProcessor(String url) throws ParserConfigurationException, SAXException, IOException {
        InputStream stream = null;
        Document document = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            stream = response.getEntity().getContent();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(stream);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw e;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return document;
    }
}
