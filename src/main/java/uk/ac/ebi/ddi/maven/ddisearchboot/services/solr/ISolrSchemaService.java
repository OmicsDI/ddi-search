package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.Entry;
import uk.ac.ebi.ddi.maven.ddisearchboot.schema.CommonSolrSchema;
import org.apache.solr.common.SolrInputDocument;

import java.util.List;

public interface ISolrSchemaService {
    Integer autoGenerateField(String fieldName, String collection);

    Boolean checkFieldExists(String fieldName, String collection);

    List<String> getAllFields(String collection);

    Integer manuallyGenerateField(CommonSolrSchema commonSolrSchema, String collection);

    List<SolrInputDocument> parseEntryToSolrInputDocument(List<Entry> list, String database, String core);
}
