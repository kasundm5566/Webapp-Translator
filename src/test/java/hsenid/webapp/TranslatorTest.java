package hsenid.webapp;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class TranslatorTest {

    @BeforeTest
    public void start(){
        System.out.println("Started translate Test.");
    }

    @AfterTest
    public void end(){
        System.out.println("\nFinished translate Test.");
    }

    @DataProvider(name = "trans_test")
    public Object[][] transData() {
        return new Object[][]{
                {"en", "fr", "child", "enfant"},
                {"en", "fr", "", ""},
                {"en", "fr", " ", ""},
                {"en", "fr", " ", ""},
                {"", "fr", "eee", new NullPointerException().toString()},
                {"en", "", "eee", new NullPointerException().toString()},
                {"", "", "eee", new NullPointerException().toString()},
                {"en", "fr", "%^%$", new NullPointerException().toString()}
        };
    }

    @Test(dataProvider = "trans_test")
    public void testTranslate(String fromLang, String toLang, String text, String expected){
        Translator tr = new Translator();
        String translatedVal = null;
        try {
            translatedVal = tr.translate(fromLang, toLang, text);
            Assert.assertEquals(expected, translatedVal, "Evaluate the translated text with expected result.");
            //System.out.println("translate Testing: from:"+fromLang+" to:"+toLang+"\tExpected:"+expected+"\tActual:\t"+translatedVal);
            System.out.print(".");
        } catch (Exception e) {
            Assert.assertNotNull(e);
            Assert.assertEquals(e.toString(), expected, "Check for the expected exception.");
            //System.out.println("translate Testing: from:"+fromLang+" to:"+toLang+"\tExpected:"+expected+"\tActual:\t"+e.toString());
            System.out.print(".");
        }
    }
}
