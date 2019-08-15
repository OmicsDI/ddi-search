package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.model.Domain;
import cn.ncbsp.omicsdi.solr.model.DomainList;
import cn.ncbsp.omicsdi.solr.model.IndexInfo;
import cn.ncbsp.omicsdi.solr.repo.SolrEntryRepo;
import cn.ncbsp.omicsdi.solr.services.IStatisticsService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.common.params.CoreAdminParams;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    SolrEntryRepo solrEntryRepo;

    @Autowired
    SolrClient solrClient;

    @Override
    public DomainList getQueryResult() {
        CoreAdminRequest coreAdminRequest = new CoreAdminRequest();
        coreAdminRequest.setAction(CoreAdminParams.CoreAdminAction.STATUS);
        CoreAdminResponse coreAdminResponse = null;
        try {
            coreAdminResponse = coreAdminRequest.process(solrClient);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert coreAdminResponse != null;
        NamedList<NamedList<Object>> coreStatus = coreAdminResponse.getCoreStatus();
        DomainList domainList = new DomainList();
        List<Domain> domains = new ArrayList<>();

        for (Map.Entry<String, NamedList<Object>> status : coreStatus) {

            NamedList<Object> statusValue = status.getValue();

            NamedList<Object> indexMap = (NamedList<Object>) statusValue.get("index");
            Domain domain = new Domain();
            domain.setId((String) statusValue.get("name"));
            domain.setName((String) statusValue.get("name"));
            domain.setDescription((String) statusValue.get("name"));


            IndexInfo numberOfEntries = new IndexInfo();
            numberOfEntries.setName("Number of entries");
            numberOfEntries.setValue(String.valueOf(indexMap.get("numDocs")));

            IndexInfo lastModificationDate = new IndexInfo();
            lastModificationDate.setName("Last modification date");
            lastModificationDate.setValue(String.valueOf(indexMap.get("lastModified")));

            IndexInfo releaseDate = new IndexInfo();
            releaseDate.setName("Release date");
            releaseDate.setValue(String.valueOf(statusValue.get("startTime")));

            IndexInfo updateDate = new IndexInfo();
            updateDate.setName("Update date");
            updateDate.setValue(String.valueOf(indexMap.get("lastModified")));

            IndexInfo url = new IndexInfo();
            url.setName("URL");
            url.setValue(String.valueOf(statusValue.get("name")));

            IndexInfo indexSize = new IndexInfo();
            indexSize.setName("Index size");
            indexSize.setValue(String.valueOf(indexMap.get("size")));

            IndexInfo indexSizeB = new IndexInfo();
            indexSizeB.setName("Index size (B)");
            indexSizeB.setValue(((Long) indexMap.get("sizeInBytes")).toString());

            IndexInfo hiddenDomain = new IndexInfo();
            hiddenDomain.setName("Hidden domain");
            hiddenDomain.setValue("false");

            IndexInfo dictionaryAvailability = new IndexInfo();
            dictionaryAvailability.setName("Dictionary Availability");
            dictionaryAvailability.setValue("true");


            IndexInfo[] indexInfos = new IndexInfo[]{numberOfEntries, lastModificationDate, releaseDate,
                    updateDate, url, indexSize, indexSizeB, hiddenDomain, dictionaryAvailability};
            domain.setIndexInfos(indexInfos);
            Domain subDomain = new Domain();
            subDomain.setId(domain.getId());
            subDomain.setName(domain.getId());
            subDomain.setDescription(domain.getId());


            domain.setSubdomains(new Domain[]{subDomain});
            domains.add(domain);
        }
        Domain[] domainCo = new Domain[domains.size()];
        domainList.setList(domains.toArray(domainCo));

        return domainList;
    }
}
