package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.DomainList;

public interface IStatisticsService {
    DomainList getQueryResult();
}
