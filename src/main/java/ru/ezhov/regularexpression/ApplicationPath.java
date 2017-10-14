package ru.ezhov.regularexpression;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ApplicationPath {
            public static final String ENC = "UTF-8";
    
            public  static String getPath() throws UnsupportedEncodingException{
                String path = new File(RegularExpression.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()+"/";
                return URLDecoder.decode(path, ENC);  
            }
}
