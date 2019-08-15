package cn.ncbsp.omicsdi.solr.util;

import org.apache.commons.lang3.StringUtils;

public class ParamStringUtil {

    public static String[] splitString(String sentence) {
        if (StringUtils.isBlank(sentence)) {
            return new String[]{};
        } else if (sentence.indexOf(",") < 0) {
            return new String[]{sentence};
        } else {
            return sentence.split(",");
        }
    }
}
