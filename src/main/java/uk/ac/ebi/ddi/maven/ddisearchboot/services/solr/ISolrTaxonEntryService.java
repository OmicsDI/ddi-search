package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.IQModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.QueryResult;

public interface ISolrTaxonEntryService {
    public QueryResult getTaxonomy(String domain, IQModel iqModel);
}
