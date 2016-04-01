package hsenid.webapp;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;
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

    PropertyReader propReader = new PropertyReader("system.properties");
    ContextListener cl = new ContextListener();
    final String KEY = propReader.readProperty("yandex.key");
    String getLangsUrl = propReader.readProperty("yandex.getlangsurl");
    String translateUrl = propReader.readProperty("yandex.translateurl");
    String autoDetect = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String translated_text = "Translated text";
        req.setCharacterEncoding("UTF-8");
        String fromLang = req.getParameter("fromlang");
        String toLang = req.getParameter("tolang");
        String fromText = req.getParameter("fromtext");
        autoDetect = req.getParameter("autodetect");

        translated_text = Translate(fromLang, toLang, fromText);
        Vector<String> list = LoadLanguages();
        req.getSession().setAttribute("langs", list);
        req.getSession().setAttribute("final_result", translated_text);
        req.getSession().setAttribute("fromlang", fromLang);
        req.getSession().setAttribute("tolang", toLang);
        req.getSession().setAttribute("fromtext", fromText);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
        rd.forward(req, resp);
    }

    /**
     * @return Language list will return as an string vector
     * @throws javax.servlet.ServletException
     */
    public Vector<String> LoadLanguages() throws ServletException {
        Vector<String> list = new Vector<String>();
        String url = getLangsUrl + KEY + "&ui=en";
        try {
            Document document = URLProcessor(url);
            NodeList nodeList = document.getElementsByTagName("Item");
            for (int x = 0, size = nodeList.getLength(); x < size; x++) {
                list.add(nodeList.item(x).getAttributes().getNamedItem("value").getNodeValue());
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new ServletException(ex);
        }
        return list;
    }

    /**
     * @param fromLang Language of the text we need to translate
     * @param toLang Language of the translated text
     * @param fromText Text we need to translate
     * @return Returns a string contains the translated text
     * @throws javax.servlet.ServletException
     */
    public String Translate(String fromLang, String toLang, String fromText) throws ServletException {

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
            throw new  ServletException(ex);
        }
        return text;
    }

    /**
     * @param url URL of the website to extract XML data
     * @return Returns a document contains the data extracted form the passed website
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    public Document URLProcessor(String url) throws ParserConfigurationException, SAXException, IOException, ServletException {
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
            throw new ServletException(e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                throw new ServletException(e);
            }
        }
        return document;
    }
}
