/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hsenid
 */
public class test {
    static Logger log=LogManager.getFormatterLogger(test.class);
    public static void main(String[] args) {
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
    }
}
