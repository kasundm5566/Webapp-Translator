package hsenid.webapp;

import javax.servlet.ServletException;

import junit.framework.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class TranslatorTest {

    @Test(parameters = {"toLang", "fromLang", "text", "expected"})
    public void testTranslate(String toLang, String fromLang, String text, String expected) throws ServletException {
        Translator tr = new Translator();
        String translatedVal = tr.Translate(toLang, fromLang, text);
        Assert.assertEquals(expected, translatedVal);
    }

    //This test method is created to practice include, exclude and other commands.
    @Test
    @Parameters({"toLang", "fromLang", "text", "expected"})
    public void TestMethod(String toLang, String fromLang, String text, String expected) throws ServletException {
        System.out.print("This is test method.");
    }
}
