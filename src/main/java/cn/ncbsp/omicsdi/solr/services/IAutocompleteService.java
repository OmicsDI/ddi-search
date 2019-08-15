package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.model.Suggestions;

/**
 * @author Xpon
 */
public interface IAutocompleteService {


    // 暂时仅支持字符串的自动联想，其他的暂不考虑
    Suggestions getSuggestions(String core, String str, Class clazz);
}
