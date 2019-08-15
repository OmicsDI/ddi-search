package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.model.Suggestions;
import cn.ncbsp.omicsdi.solr.queryModel.MLTQueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.SuggestQueryModel;
import cn.ncbsp.omicsdi.solr.queryModel.TermsQueryModel;
import cn.ncbsp.omicsdi.solr.solrmodel.SimilarResult;
import cn.ncbsp.omicsdi.solr.solrmodel.TermResult;

import java.util.Map;

/**
 * @author Xpon
 */
public interface ISolrCustomService {

    Suggestions getSuggestResult(String core, SuggestQueryModel suggestQueryModel);

    Suggestions getSuggestion(String core, String term);

    Suggestions getSuggestResultWithSuggester(String core, String suggester, Map<String, String[]> paramMap);

    SimilarResult getSimilarResult(String core, Map<String, String[]> paramMap);

    SimilarResult getSimilarResult(String core, MLTQueryModel mltQueryModel);

    TermResult getFrequentlyTerms(String core, TermsQueryModel termsQueryModel);
}
