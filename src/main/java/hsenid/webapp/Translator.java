package hsenid.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    PropertyReader propReader = new PropertyReader();
    ContextListener cl = new ContextListener();
    final String KEY = propReader.getYandexKey();
    String getLangsUrl = propReader.getYandexLangsUrl();
    String translateUrl = propReader.getYandexTranslateUrl();
    String autoDetect = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String translated_text = "Translated text";
        req.setCharacterEncoding("UTF-8");
        String fromlang = req.getParameter("fromlang");
        String tolang = req.getParameter("tolang");
        String fromtext = req.getParameter("fromtext");
        autoDetect = req.getParameter("autodetect");

        translated_text = Translate(fromlang, tolang, fromtext);
        ArrayList<String> list = LoadLanguages();
        req.getSession().setAttribute("langs", list);
        req.getSession().setAttribute("final_result", translated_text);
        req.getSession().setAttribute("fromlang", fromlang);
        req.getSession().setAttribute("tolang", tolang);
        req.getSession().setAttribute("fromtext", fromtext);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * @return Language list will return as an string arraylist
     */
    public ArrayList<String> LoadLanguages() {
        ArrayList<String> list = new ArrayList<String>();
        String url = getLangsUrl + KEY + "&ui=en";
        try {
            Document document = URLProcessor(url);
            NodeList nodeList = document.getElementsByTagName("Item");
            for (int x = 0, size = nodeList.getLength(); x < size; x++) {
                list.add(nodeList.item(x).getAttributes().getNamedItem("value").getNodeValue());
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {

        }
        return list;
    }

    /**
     * @param fromLang Language of the text we need to translate
     * @param toLang Language of the translated text
     * @param fromText Text we need to translate
     * @return Returns a string contains the translated text
     */
    public String Translate(String fromLang, String toLang, String fromText) {

        String text = null;
        String url;
        if ("1".equals(autoDetect)) {
            url = translateUrl + KEY + "&lang=" + toLang + "&text=" + fromText;
        } else {
            url = translateUrl + KEY + "&lang=" + fromLang + "-" + toLang + "&text=" + fromText;
        }
        url = url.replaceAll(" ", "%20");
        try {
            Document document = URLProcessor(url);
            NodeList nodeList = document.getElementsByTagName("Translation");
            text = nodeList.item(0).getTextContent();
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {

        }
        return text;
    }

    /**
     * @param url URL of the website to extract XML data
     * @return Returns a document contains the data extracted form the passed
     * website
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
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
            throw new ServerException(e.toString());
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
