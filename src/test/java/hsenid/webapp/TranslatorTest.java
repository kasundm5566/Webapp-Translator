/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import javax.servlet.ServletException;
import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author hsenid
 */
public class TranslatorTest {

    @Test()
    public void testTranslate() throws ServletException {
        Translator tr = new Translator();
        String val = tr.Translate("en", "fr", "child");
        Assert.assertEquals("enfant", val);
    }
}
