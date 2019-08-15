package cn.ncbsp.omicsdi.solr.controller;

import cn.ncbsp.omicsdi.solr.model.Domain;
import cn.ncbsp.omicsdi.solr.model.DomainList;
import cn.ncbsp.omicsdi.solr.model.Suggestions;
import cn.ncbsp.omicsdi.solr.queryModel.FacetQueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.MLTQueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.SuggestQueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.TermsQueryModel;
import cn.ncbsp.omicsdi.solr.services.*;
import cn.ncbsp.omicsdi.solr.solrmodel.FacetList;
import cn.ncbsp.omicsdi.solr.solrmodel.QueryResult;
import cn.ncbsp.omicsdi.solr.solrmodel.SimilarResult;
import cn.ncbsp.omicsdi.solr.solrmodel.TermResult;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

//import cn.ncbsp.omicsdi.solr.services.IMoneyService;

/**
 * @author Xpon
 */
@RestController
@RequestMapping(value = "/")
public class DatasetController {

    private static final Logger logger = LoggerFactory.getLogger(DatasetController.class);

    private final static String DEFAULT_SOLR_CORE = "omics";

    private final
    ISolrCustomService solrCustomService;

    private final
    ISolrFacetService solrFacetService;

    private final
    ISolrEntryService solrEntryService;

    private final
    ISolrTaxonEntryService solrTaxonEntryService;

    private final IStatisticsService statisticsService;

    @Autowired
    public DatasetController(ISolrCustomService solrCustomService,
                             ISolrFacetService solrFacetService,
                             ISolrEntryService solrEntryService,
                             ISolrTaxonEntryService solrTaxonEntryService,
                             IStatisticsService statisticsService) {
        this.solrCustomService = solrCustomService;
        this.solrFacetService = solrFacetService;
        this.solrEntryService = solrEntryService;
        this.solrTaxonEntryService = solrTaxonEntryService;
        this.statisticsService = statisticsService;
    }

    // @return TermResult
    //todo exclude in API
    // 可以使用 对应DatasetWSClient getFrequentlyTerms 方法
    @ApiOperation(value = "DatasetWSClient getFrequentlyTerms")
    @RequestMapping(value = "/{domain}/topterms/{fieldid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TermResult getFrequentlyTerms(
            @PathVariable(value = "domain") String domain,
            @PathVariable(value = "fieldid") String fieldid,
            @RequestParam(value = "termlist", required = false) String termlist

    ) {
        TermsQueryModel termsQueryModel = new TermsQueryModel();
        termsQueryModel.setTerms_fl(fieldid);
        return solrCustomService.getFrequentlyTerms(domain, termsQueryModel);
    }
//    https://www.ebi.ac.uk/ebisearch/ws/rest/omics?query=*:*%20NOT%20(isprivate:true)&fields=description,name,submitter_keywords,curator_keywords,publication_date,TAXONOMY,omics_type,ENSEMBL,UNIPROT,CHEBI,citation_count,view_count,reanalysis_count,search_count,view_count_scaled,reanalysis_count_scaled,citation_count_scaled,normalized_connections,download_count,download_count_scaled&start=0&size=20&facetcount=20&format=JSON

//    @ApiOperation(value = "DatasetWSClient getDatasets")
//    @RequestMapping(value = "/omics1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    DomainList getDomainsOfOmics(
//            @RequestParam(value = "query", required = false, defaultValue = "") String query,
//            @RequestParam(value = "fields", required = false, defaultValue = "") String fields,
//            @RequestParam(value = "start", required = false, defaultValue = "0") int start,
//            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
//            @RequestParam(value = "facetcount", required = false, defaultValue = "20") int facetcount,
//            @RequestParam(value = "sortfield", required = false, defaultValue = "") String sortfield,
//            @RequestParam(value = "order", required = false, defaultValue = "asc") String order,
//            @RequestParam(value = "format", required = false, defaultValue = "JSON") String format
//    ) {
//        DomainList domainList = new DomainList();
//        Properties properties = new Properties();
//        InputStream in = null;
//        try {
//            in = new BufferedInputStream(new FileInputStream("properties/domainList.properties"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            assert in != null;
//            properties.load(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String propertiesId = (String) properties.get("id");
//        String propertiesName = (String) properties.get("name");
//        String propertiesDescription = (String) properties.get("description");
//        String[] ids = propertiesId.split(";");
//        String[] names = propertiesName.split(";");
//        String[] descriptions = propertiesDescription.split(";");
//        Domain[] domains = new Domain[ids.length];
//        for (int i = 0; i < domains.length; i++) {
//            Domain domainL = new Domain();
//            domainL.setId(ids[i]);
//            domainL.setName(names[i]);
//            domainL.setDescription(descriptions[i]);
//            domains[i] = domainL;
//        }
//        domainList.setList(domains);
//        return domainList;
//    }


    //https://www.ebi.ac.uk/ebisearch/ws/rest/omics?format=JSON
    @ApiOperation(value = "DatasetWSClient getDatasets")
    @RequestMapping(value = "/status/omics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DomainList getDomainsStatistics() {
        return statisticsService.getQueryResult();
    }

    private String addDefaultFields(String fields) {
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(fields)) {
            sb.append(fields);
            if(!fields.toLowerCase().contains("id")) {
                sb.append(",id");
            }
            if(!fields.toLowerCase().contains("database")) {
                sb.append(",database");
            }
        }else {
            sb.append("id,database");
        }

        return sb.toString();
    }

    /**
     * @param domain
     * @param query
     * @param fields
     * @param facetcount
     * @param sortfield
     * @param order
     * @param format
     * @return https://www.ebi.ac.uk/ebisearch/ws/rest/omics?query=cancer%20human%20NOT%20(isprivate:true)&fields=description,name,submitter_keywords,curator_keywords,publication_date,TAXONOMY,omics_type,ENSEMBL,UNIPROT,CHEBI,citation_count,view_count,reanalysis_count,search_count,view_count_scaled,reanalysis_count_scaled,citation_count_scaled,normalized_connections,download_count,download_count_scaled&start=0&size=20&facetcount=20&format=JSON
     * <p>
     * https://www.ebi.ac.uk/ebisearch/ws/rest/omics?query=domain_source:(arrayexpress-repository%20OR%20atlas-experiments%20OR%20biomodels%20OR%20dbgap%20OR%20ega%20OR%20eva%20OR%20geo%20OR%20gnps%20OR%20gpmdb%20OR%20jpost%20OR%20lincs%20OR%20massive%20OR%20metabolights_dataset%20OR%20metabolome_express%20OR%20metabolomics_workbench%20OR%20omics_ena_project%20OR%20paxdb)&facetfields=TAXONOMY&facetcount=100&size=0&format=JSON
     */
    //statistics
    @ApiOperation(value = "DatasetWSClient getDatasets")
    @RequestMapping(value = "/statistics/{domain}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    FacetList getStaticsData(
            @PathVariable(value = "domain") String domain,
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "fields", required = false, defaultValue = "") String fields,
            @RequestParam(value = "facetfields", required = false, defaultValue = "") String facetfields,
            @RequestParam(value = "facetcount", required = false, defaultValue = "20") int facetcount,
            @RequestParam(value = "sortfield", required = false, defaultValue = "") String sortfield,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order,
            @RequestParam(value = "format", required = false, defaultValue = "JSON") String format

    ) {
        if(facetfields.indexOf(",") > 0) {
            //todo error
        }
        FacetQueryModel facetQueryModel = new FacetQueryModel();
        if (StringUtils.isBlank(query)) {
            query = "*:*";
        } else if (!query.contains(":")) {
            query = Constans.DEFAULT_SEARCH_FIELD + ":" + query;
        }
        facetQueryModel.setQ(query);
        facetQueryModel.setFl(addDefaultFields(fields));
        facetQueryModel.setFacet_field(facetfields);
        facetQueryModel.setFacet_limit(String.valueOf(facetcount));
        return solrFacetService.getFacetEntriesByDomains(DEFAULT_SOLR_CORE, facetQueryModel);

    }

    // 对应DatasetWsClient getDatasets
    // sortfield+order 形成排序
    @ApiOperation(value = "DatasetWSClient getDatasets")
    @RequestMapping(value = "/{domain}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Object getDomains(
            @PathVariable(value = "domain") String domain,
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "fields", required = false, defaultValue = "") String fields,
            @RequestParam(value = "start", required = false, defaultValue = "0") int start,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "facetcount", required = false, defaultValue = "20") int facetcount,
            @RequestParam(value = "facetfields", required = false) String facetfields,
            @RequestParam(value = "sortedField", required = false) String sortedField,
            @RequestParam(value = "order", required = false, defaultValue = "ascending") String order,
            @RequestParam(value = "format", required = false, defaultValue = "JSON") String format

    ) {


        FacetQueryModel facetQueryModel = new FacetQueryModel();
        query = query.replace("NOT (isprivate:true)", "");

        if (StringUtils.isBlank(query)) {
            query = "*:*";
        } else if (!query.contains(":")) {
            query = Constans.DEFAULT_SEARCH_FIELD + ":" + query;
        }
        facetQueryModel.setQ(query);

        if (StringUtils.isNotBlank(fields)) {
            facetQueryModel.setFl(fields);
        }

        facetQueryModel.setStart(String.valueOf(start));
//        facetQueryModel.setRows(String.valueOf(size));

        if(StringUtils.isNotBlank(facetfields)) {
            facetQueryModel.setFacet_field(facetfields);
        }
        facetQueryModel.setFacet_limit(String.valueOf(facetcount));



        if (StringUtils.isNotBlank(sortedField) && StringUtils.isNotBlank(order)) {

            String[] sorts = Arrays.stream(sortedField.split(",")).map(x -> {
                x = x + " " + order;
                return x;
            }).toArray(String[]::new);
            StringBuilder sb = new StringBuilder();
            for (String sort : sorts) {
                if (sb.length() == 0) {
                    sb.append(sort);
                } else {
                    sb.append(",").append(sort);
                }
            }
            facetQueryModel.setSort(sb.toString());
        }

        facetQueryModel.setWt(format);

        return solrEntryService.getQueryResult(domain, facetQueryModel);
    }

    @ApiOperation(value = "FacetWSClient getFacet")
    @RequestMapping(value = "/{domain}/facet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    FacetList getFacetDomains(
            @PathVariable(value = "domain") String domain,
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "facetfields", required = false, defaultValue = "") String facetfields,
            @RequestParam(value = "facetcount", required = false, defaultValue = "") String facetcount,
            @RequestParam(value = "size", required = false, defaultValue = "") String size,
            @RequestParam(value = "format", required = false, defaultValue = "") String format

    ) {
        FacetQueryModel facetQueryModel = new FacetQueryModel();
        if (StringUtils.isBlank(query)) {
            query = "*:*";
        } else if (!query.contains(":")) {
            query = Constans.DEFAULT_SEARCH_FIELD + ":" + query;
        }
        facetQueryModel.setQ(query);
        facetQueryModel.setFacet_field(facetfields);
        facetQueryModel.setFacet_limit(facetcount);
        facetQueryModel.setRows(size);
        return solrFacetService.getFacetEntriesByDomains(domain, facetQueryModel);
    }

    // 对应DatasetWsClient getDatasetsById
    // Entry retrieval

    /**
     * @param domain
     * @param entryids
     * @param fields
     * @param fieldurl
     * @param viewurl
     * @param format
     * @return https://www.ebi.ac.uk/ebisearch/ws/rest/arrayexpress-repository/entry/E-PROT-2,E-PROT-5,E-PROT-4,E-GEOD-51538,E-MTAB-2414,E-GEOD-74414?fields=name,description,publication_date,full_dataset_link,data_protocol,sample_protocol,instrument_platform,technology_type,pubmed,submitter_keywords,curator_keywords,TAXONOMY,disease,omics_type,tissue,submitter_affiliation,dates,submitter,submitter_mail,labhead,labhead_mail&format=JSON
     */
    @ApiOperation(value = "DatasetWsClient getDatasetsById")
    @RequestMapping(value = "/{domain}/entry/{entryids}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    QueryResult getEntries(
            @PathVariable(value = "domain") String domain,
            @PathVariable(value = "entryids") String entryids,
            @RequestParam(value = "fields", required = false, defaultValue = "") String fields,
            @RequestParam(value = "fieldurl", required = false, defaultValue = "false") boolean fieldurl,
            @RequestParam(value = "viewurl", required = false, defaultValue = "false") boolean viewurl,
            @RequestParam(value = "format", required = false, defaultValue = "JSON") String format
    ) {
        QueryResult newQueryResult = null;
        if ("taxonomy".equals(domain)) {
            FacetQueryModel facetQueryModel = new FacetQueryModel();
            facetQueryModel.setQ("tax_id:" + "(" + entryids.replaceAll(",", " OR ") + ")" + " AND name_class:\"scientific name\"");
            facetQueryModel.setWt(format);
            newQueryResult = solrTaxonEntryService.getTaxonomy("taxonomy", facetQueryModel);
        } else {
            FacetQueryModel facetQueryModel = new FacetQueryModel();
            facetQueryModel.setQ("database:" + domain + " AND id:" + "(" + entryids.replaceAll(",", " OR ") + ")");
            facetQueryModel.setFl(addDefaultFields(fields));
            facetQueryModel.setWt(format);
            newQueryResult = solrEntryService.getQueryResult(domain, facetQueryModel);
        }
        return newQueryResult;
    }

    // auto complete
    // DictionaryClient  getWordsDomains
    @ApiOperation(value = "DictionaryClient  getWordsDomains")
    @RequestMapping(value = "/{domain}/autocomplete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Suggestions autoComplete(
            @PathVariable(value = "domain") String domain,
            @RequestParam(value = "term") String term
    ) {

        // 仅做单词的联想
        String regex = "[a-zA-Z0-9]+";
        Boolean match = Pattern.matches(regex, term);
        SuggestQueryModel suggestQueryModel = new SuggestQueryModel();
        suggestQueryModel.setSuggest_q(term);
        if (match) {
            return solrCustomService.getSuggestResult(domain, suggestQueryModel);
        } else {
            return new Suggestions();
        }
    }

    /**
     * @param domain
     * @param entryid
     * @param mltfields
     * @param excludesets
     * @param entryattrs
     * @param format
     * @return getSimilar
     * https://www.ebi.ac.uk/ebisearch/ws/rest/pride/entry/PXD000210/morelikethis/omics?mltfields=name,description,data_protocol,sample_protocol,omics_type&excludesets=omics_stopwords&entryattrs=score&format=JSON
     */
    @ApiOperation(value = "MoreLikeThis")
    @RequestMapping(value = "/{domain}/entry/{entryid}/morelikethis/omics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SimilarResult moreLikeSameDomain(
            @PathVariable(value = "domain") String domain,
            @PathVariable(value = "entryid") String entryid,
            @RequestParam(value = "mltfields", required = false, defaultValue = "") String mltfields,
            @RequestParam(value = "fields", required = false, defaultValue = "") String fields,
            @RequestParam(value = "excludesets", required = false, defaultValue = "") String excludesets,
            @RequestParam(value = "entryattrs", required = false, defaultValue = "") String entryattrs,
            @RequestParam(value = "format", required = false, defaultValue = "") String format
    ) {
        MLTQueryModel mltQueryModel = new MLTQueryModel();
        mltQueryModel.setQ("id:" + entryid + " AND " + "database:" + domain);
        if(StringUtils.isNotBlank(mltfields)) {
            mltQueryModel.setMlt_fl(mltfields);
        }
        mltQueryModel.setSort("score desc");
        if(StringUtils.isNotBlank(fields)) {
            mltQueryModel.setFl(addDefaultFields(fields) +",score");
        } else {
            mltQueryModel.setFl(addDefaultFields(mltfields)+",score");
        }
        return solrCustomService.getSimilarResult(DEFAULT_SOLR_CORE, mltQueryModel);
    }
}
