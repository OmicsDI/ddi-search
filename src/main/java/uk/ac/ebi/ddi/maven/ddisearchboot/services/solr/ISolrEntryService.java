package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.IQModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.QueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.NCBITaxonomy;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.QueryResult;

import java.util.List;

/**
 * @author Xpon
 */
public interface ISolrEntryService<T> {
//    void saveSolrEntry(String xml, String core);
//
//    void saveSolrEntries(String folderPath, String core, String backupPath);

    void saveSolrEntry(String xml);

    void saveSolrEntries(String folderPath, String backupPath);

    List<T> getSolrEntries(String core, QueryModel queryModel, Class<T> clazz);

    QueryResult getQueryResult(String domain, IQModel iqModel, String order, String sortfield);

    List<NCBITaxonomy> getNCBITaxonomyData(String... taxonId);
}
