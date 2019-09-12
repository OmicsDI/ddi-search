package uk.ac.ebi.ddi.maven.ddisearchboot.services.solr;

import uk.ac.ebi.ddi.maven.ddisearchboot.model.mongo.Suggestions;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.MLTQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.SuggestQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.queryModel.TermsQueryModel;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.SimilarResult;
import uk.ac.ebi.ddi.maven.ddisearchboot.model.solr.TermResult;

import java.util.Map;

/**
 * @author Xpon
 */
public interface ISolrCustomService {

    Suggestions getSuggestResult(String core, SuggestQueryModel suggestQueryModel);

    Suggestions getSuggestion(String core, String term);

    Suggestions getSuggestResultWithSuggester(String core, String suggester, Map<String, String[]> paramMap);

    SimilarResult getSimilarResult(String core, Map<String, String[]> paramMap);

    SimilarResult getSimilarResult(String core, MLTQueryModel mltQueryModel, String order, String sortfield);

    TermResult getFrequentlyTerms(String core, TermsQueryModel termsQueryModel, String excludeWords);
}
