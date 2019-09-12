package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.FacetQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.FacetList;

/**
 * @author Xpon
 */
public interface ISolrFacetService {
    //    FacetList getFacetEntriesByDomains(String core, Map<String,String[]> paramMap);
    FacetList getFacetEntriesByDomains(String core, FacetQueryModel facetQueryModel, String order, String sortField);
}
