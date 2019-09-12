package uk.ac.ebi.ddi.maven.ddisearchboot.repo.solr;

import java.util.List;

/**
 * @author Xpon
 */
public interface SolrSchemaRepo {
    List<String> getAllExcludeWords(String collectionName);
    Integer saveExcludeWords(List<String> strings, String collectionName);
}
