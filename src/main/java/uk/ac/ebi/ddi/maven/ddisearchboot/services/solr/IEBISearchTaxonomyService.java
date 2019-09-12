package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr;


import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.NCBITaxonomy;

import java.util.List;

public interface IEBISearchTaxonomyService {
    List<NCBITaxonomy> getNCBITaxonomyData(String... id);
}
