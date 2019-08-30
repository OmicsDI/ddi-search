package cn.ncbsp.omicsdi.solr.util;

import cn.ncbsp.omicsdi.solr.schema.CommonSchemaTypeEnum;
import cn.ncbsp.omicsdi.solr.schema.CommonSolrSchema;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SolrSchemaUtil {
    public static Map<String, Object> getSolrSchemaMap(CommonSolrSchema commonSolrSchema) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("name", commonSolrSchema.getName());
        map.put("type", commonSolrSchema.getType().getTypeName());
        map.put("stored", commonSolrSchema.getStored());
        map.put("indexed", commonSolrSchema.getIndexed());
        return map;
    }
}
