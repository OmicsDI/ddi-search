package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.Impl;

import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.IEBISearchTaxonomyService;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.NCBITaxonomy;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EBISearchTaxonomyServiceImpl implements IEBISearchTaxonomyService {

    @Value("${ebi.search.taxonomy}")
    private String url;

    @Override
    public List<NCBITaxonomy> getNCBITaxonomyData(String ...id) {

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme("https").host(url).path("/ebisearch/ws/rest/taxonomy/entry").path("/"+ StringUtils.join(id, ",")).queryParam("fields", new Object[]{"name"}).queryParam("format", new Object[]{"JSON"});

        RestTemplate restTemplate = new RestTemplate();

        QueryResult queryResult = restTemplate.getForObject(builder.toUriString(), QueryResult.class);
        return Arrays.stream(queryResult.getEntries()).map(x -> {
            NCBITaxonomy ncbiTaxonomy = new NCBITaxonomy();
            ncbiTaxonomy.setId(x.getId());
            ncbiTaxonomy.setTaxId(x.getId());
            ncbiTaxonomy.setNameTxt(((String[])x.getFields().get("name"))[0]);
            return ncbiTaxonomy;
        }).collect(Collectors.toList());
    }
}
