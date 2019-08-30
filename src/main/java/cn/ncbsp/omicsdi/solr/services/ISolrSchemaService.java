package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.model.Entry;
import cn.ncbsp.omicsdi.solr.schema.CommonSolrSchema;
import org.apache.solr.common.SolrInputDocument;

import java.util.List;

public interface ISolrSchemaService {
    Integer autoGenerateField(String fieldName, String collection);

    Boolean checkFieldExists(String fieldName, String collection);

    List<String> getAllFields(String collection);

    Integer manuallyGenerateField(CommonSolrSchema commonSolrSchema, String collection);

    List<SolrInputDocument> parseEntryToSolrInputDocument(List<Entry> list, String database, String core);
}
