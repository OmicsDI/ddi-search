package cn.ncbsp.omicsdi.solr.services.Impl;


import cn.ncbsp.omicsdi.solr.model.Suggestions;
import cn.ncbsp.omicsdi.solr.repo.SolrEntryRepo;
import cn.ncbsp.omicsdi.solr.services.IAutocompleteService;
import cn.ncbsp.omicsdi.solr.solrmodel.SolrEntry;
import org.apache.solr.client.solrj.response.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


/**
 * @author Xpon
 */
@Service
public class AutocompleteServiceImpl implements IAutocompleteService {

    static final String DEFAULT_SUGGEST_HANDLER = "/suggest";

    @Autowired
    SolrEntryRepo<SolrEntry> solrEntryRepo;

    @Override
    public Suggestions getSuggestions(String core, String str, Class clazz) {
        SimpleQuery simpleQuery = new SimpleQuery(str);
        simpleQuery.setRequestHandler(DEFAULT_SUGGEST_HANDLER);
        SolrResultPage<LinkedList<Suggestion>> solrResultPage = solrEntryRepo.getSuggestQueryResult(core, simpleQuery, clazz);

        // todo remove hardcode
        List<Suggestion> list = solrResultPage.getContent().get(0);

        cn.ncbsp.omicsdi.solr.model.Suggestion[] suggestionCollection = list.stream().map(x -> {
            cn.ncbsp.omicsdi.solr.model.Suggestion suggestion = new cn.ncbsp.omicsdi.solr.model.Suggestion();
            suggestion.setSuggestion(x.getTerm());
            return suggestion;
        }).toArray(cn.ncbsp.omicsdi.solr.model.Suggestion[]::new);
        Suggestions suggestions = new Suggestions();
        suggestions.setSuggestions(suggestionCollection);
        return suggestions;
    }
}
