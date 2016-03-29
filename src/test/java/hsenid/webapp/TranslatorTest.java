/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import javax.servlet.ServletException;

import junit.framework.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author hsenid
 */
public class TranslatorTest {

    @Test(parameters = {"toLang", "fromLang", "text", "expected"})
    public void testTranslate(String toLang, String fromLang, String text, String expected) throws ServletException {
        Translator tr = new Translator();
        String translatedVal = tr.Translate(toLang, fromLang, text);
        Assert.assertEquals(expected, translatedVal);
    }

    @Test(enabled = false)
    @Parameters({"toLang", "fromLang", "text", "expected"})
    public void TestMethod(String toLang, String fromLang, String text, String expected) throws ServletException {
        System.out.print("This is test method.");
    }
}
