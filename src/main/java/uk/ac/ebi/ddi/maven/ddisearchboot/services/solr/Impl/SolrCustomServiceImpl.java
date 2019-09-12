package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.Impl;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.Suggestions;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.Suggestion;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.MLTQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.SolrQueryBuilder;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.SuggestQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.TermsQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.repo.solr.SolrEntryRepo;
import uk.ac.ebi.ddi.maven.ddisearchboot.repo.solr.SolrSchemaRepo;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.ISolrCustomService;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Entry;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.SimilarResult;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.Term;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.TermResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MoreLikeThisParams;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * Created by Xpon on 2018/11/08
 *
 * @author Xpon
 */

@Service
public class SolrCustomServiceImpl implements ISolrCustomService {


    @Autowired
    SolrClient solrClient;

    @Autowired
    SolrEntryRepo solrEntryRepo;

    @Autowired
    SolrSchemaRepo solrSchemaRepo;

//    @Autowired
//    SolrSuggestTemplate solrSuggestTemplate;


    @Override
    public Suggestions getSuggestion(String core, String term) {
        return null;
    }

    /**
     * @param core
     * @return
     */
    @Override
    public Suggestions getSuggestResult(String core, SuggestQueryModel suggestQueryModel) {
        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(suggestQueryModel);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(core, solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        Map<String, List<org.apache.solr.client.solrj.response.Suggestion>> map = queryResponse.getSuggesterResponse().getSuggestions();
        List<org.apache.solr.client.solrj.response.Suggestion> list = map.get("mySuggester");
        Suggestion[] suggestions = list.stream().map(x -> {
            Suggestion suggestion = new Suggestion();
            suggestion.setSuggestion(x.getTerm());
            return suggestion;
        }).toArray(Suggestion[]::new);
        Suggestions suggestionsCollection = new Suggestions();
        suggestionsCollection.setSuggestions(suggestions);
        return suggestionsCollection;

    }

    @Override
    public Suggestions getSuggestResultWithSuggester(String core, String suggester, Map<String, String[]> paramMap) {
        return null;
    }


    @Override
    public SimilarResult getSimilarResult(String core, Map<String, String[]> paramMap) {
        if (paramMap.isEmpty()) {
            // todo exception
            return null;
        }

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/" + MoreLikeThisParams.MLT);
        Set<String> set = paramMap.keySet();
        for (String key : set) {
            solrQuery.setParam(key, paramMap.get(key));
        }
        ;

        QueryResponse queryResponse = null;
        Map<String, Map<String, Float>> namedList = null;
        SolrDocumentList solrDocumentList = null;

        try {
            queryResponse = solrClient.query(core, solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert queryResponse != null;
        solrDocumentList = queryResponse.getResults();
        namedList = queryResponse.getResponse().asMap(queryResponse.getResponse().size());
        SimilarResult similarResult = new SimilarResult();
        assert namedList != null;
        Map<String, Float> map = namedList.get("interestingTerms");
        Float score = 0.0F;
        for (String key : map.keySet()) {
            score += map.get(key);
        }

        List<Entry> entryList = new ArrayList<>();

        for (SolrDocument solrDocument : solrDocumentList) {
            Entry entry = new Entry();
            entry.setId((String) solrDocument.get("id"));
            entry.setScore(score.toString());
            entryList.add(entry);
        }

        Entry[] entries = entryList.toArray(new Entry[0]);

        similarResult.setEntries(entries);
        return similarResult;
    }

    @Override
    public SimilarResult getSimilarResult(String core, MLTQueryModel mltQueryModel, String order, String sortfield) {
        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(mltQueryModel);

        SolrQueryBuilder.addSort(order, sortfield, solrQuery);

        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(core, solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        assert queryResponse != null;
        NamedList<Object> namedList = queryResponse.getResponse();
        if(namedList == null) {
            return null;
        }
        SolrDocumentList solrDocumentList = (SolrDocumentList) namedList.get("response");
        SimilarResult similarResult = new SimilarResult();
        Entry[] entries = solrDocumentList.stream().map(x -> {
            Entry entry = new Entry();
            entry.setId(String.valueOf(x.get("id")));
            entry.setScore(String.valueOf(x.get("score")));
            entry.setSource(String.valueOf(x.get("database")));
            Map<String, String[]> map = new ConcurrentHashMap<>();
            for (String s : x.keySet()) {
                if (x.get(s) != null && x.get(s) instanceof ArrayList) {
                    ArrayList<String> dataList = (ArrayList<String>) x.get(s);
                    String[] strings = new String[dataList.size()];
                    strings = dataList.toArray(strings);
                    map.put(s, strings);
                } else if (x.get(s) != null) {
                    map.put(s, new String[]{String.valueOf(x.get(s))});
                }
            }
            entry.setFields(map);
            return entry;
        }).toArray(Entry[]::new);
        similarResult.setEntries(entries);
        return similarResult;
    }

    @Override
    public TermResult getFrequentlyTerms(String core, TermsQueryModel termsQueryModel, String excludwords) {

        SolrQuery solrQuery = SolrQueryBuilder.buildSolrQuery(termsQueryModel);

        if(StringUtils.isNotBlank(excludwords)) {
            saveNewStopWords(core, excludwords);
        }

        QueryResponse queryResponse;
        TermsResponse termsResponse = null;
        try {
            queryResponse = solrClient.query(core, solrQuery);
            termsResponse = queryResponse.getTermsResponse();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

        assert termsResponse != null;
        Map<String, List<TermsResponse.Term>> termMap = termsResponse.getTermMap();
        //1st term field
        String termFl = termsQueryModel.getTerms_fl().split(",")[0];

        Term[] terms = termMap.get(termFl).stream().map(x -> {
            Term term = new Term();
            term.setText(x.getTerm());
            term.setDocFreq(String.valueOf(x.getFrequency()));
            return term;
        }).toArray(Term[]::new);

        TermResult termResult = new TermResult();
        termResult.setTopTerms(terms);
        //todo
        termResult.setTotalTermCount(0);


        return termResult;
    }

    public void saveNewStopWords(String core, String excludwords) {
        List<String> currentStopWords = solrSchemaRepo.getAllExcludeWords(core);
        List<String> wantedStopWords = Arrays.asList(excludwords.split(","));
        List<String> newStopWords = wantedStopWords.stream().filter(x -> !currentStopWords.contains(x)).collect(Collectors.toList());
        if(newStopWords!= null && newStopWords.size() > 0) {
            solrSchemaRepo.saveExcludeWords(newStopWords, core);
        }
    }
}
