package uk.ac.ebi.ddi.maven.ddisearchboot.util;

import uk.ac.ebi.ddi.maven.ddisearchboot.schema.CommonSolrSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SolrSchemaUtil {
    public static Map<String, Object> getSolrSchemaMap(CommonSolrSchema commonSolrSchema) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("name", commonSolrSchema.getName());
        map.put("type", commonSolrSchema.getType().getTypeName());
        map.put("stored", commonSolrSchema.getStored());
        map.put("indexed", commonSolrSchema.getIndexed());
        map.put("multiValued", true);
        return map;
    }
}
