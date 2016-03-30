package hsenid.webapp;

import javax.servlet.ServletException;

import junit.framework.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class TranslatorTest {

    @DataProvider(name = "trans_test")
    public Object[][] transData(){
        return new Object[][]{
                {"en","fr","child","enfant"},
                {"en","fr","",""},
                {"en","fr"," ",""}
        };
    }

    @Test(dataProvider = "trans_test")
    public void testTranslate(String fromLang, String toLang, String text, String expected) throws ServletException {
        Translator tr = new Translator();
        String translatedVal = tr.Translate(fromLang, toLang, text);
        Assert.assertEquals(expected, translatedVal);
        System.out.println("Translate Testing: from:"+fromLang+" to:"+toLang+"\tExpected:"+expected+"\tActual:\t"+translatedVal);
    }
}
