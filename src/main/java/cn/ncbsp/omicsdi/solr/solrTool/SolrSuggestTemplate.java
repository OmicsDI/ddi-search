package cn.ncbsp.omicsdi.solr.solrTool;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.solr.core.QueryParsers;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.data.solr.core.SolrExceptionTranslator;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.SolrConverter;
import org.springframework.data.solr.core.mapping.SolrPersistentEntity;
import org.springframework.data.solr.core.mapping.SolrPersistentProperty;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.data.solr.core.schema.SolrPersistentEntitySchemaCreator;
import org.springframework.data.solr.server.SolrClientFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/*
 *   @author Pan
 *   重新封装了一次solrTemplate，专门为suggestAPI用
 *
 *
 */

public class SolrSuggestTemplate extends SolrTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolrSuggestTemplate.class);
    private static final PersistenceExceptionTranslator EXCEPTION_TRANSLATOR = new SolrExceptionTranslator();
    @SuppressWarnings("serial") //
    private static final List<String> ITERABLE_CLASSES = new ArrayList<String>() {
        {
            add(List.class.getName());
            add(Collection.class.getName());
            add(Iterator.class.getName());
        }
    };
    private @Nullable
    QueryParsers queryParsers;
    private @Nullable
    MappingContext<? extends SolrPersistentEntity<?>, SolrPersistentProperty> mappingContext;
    private @Nullable
    ApplicationContext applicationContext;
    private @Nullable
    SolrClientFactory solrClientFactory;
    private @Nullable
    SolrConverter solrConverter;
    private Set<SolrPersistentEntitySchemaCreator.Feature> schemaCreationFeatures = Collections.emptySet();


    public SolrSuggestTemplate(SolrClient solrClient) {
        super(solrClient);
    }

    public SolrSuggestTemplate(SolrClient solrClient, RequestMethod requestMethod) {
        super(solrClient, requestMethod);
    }

    public SolrSuggestTemplate(SolrClientFactory solrClientFactory) {
        super(solrClientFactory);
    }

    public SolrSuggestTemplate(SolrClientFactory solrClientFactory, RequestMethod requestMethod) {
        super(solrClientFactory, requestMethod);
    }

    public SolrSuggestTemplate(SolrClientFactory solrClientFactory, SolrConverter solrConverter) {
        super(solrClientFactory, solrConverter);
    }

    public SolrSuggestTemplate(SolrClientFactory solrClientFactory, SolrConverter solrConverter, RequestMethod defaultRequestMethod) {
        super(solrClientFactory, solrConverter, defaultRequestMethod);
    }

    @Override
    public <T, S extends Page<T>> S query(String collection, Query query, Class<T> clazz) {
        return query(collection, query, clazz, getDefaultRequestMethod());
    }

    @Override
    public <T, S extends Page<T>> S query(String collection, Query query, Class<T> clazz, RequestMethod method) {

        Assert.notNull(collection, "Collection must not be null!");
        Assert.notNull(query, "Query must not be 'null'.");
        Assert.notNull(clazz, "Target class must not be 'null'.");
        Assert.notNull(clazz, "Method must not be 'null'.");

        return (S) doQueryForPage(collection, query, clazz, method);
    }

    private <T> SolrResultPage<T> doQueryForPage(String collection, Query query, Class<T> clazz,
                                                 @Nullable RequestMethod requestMethod) {

        QueryResponse response = null;
        QueryParserBase.NamedObjectsQuery namedObjectsQuery = new QueryParserBase.NamedObjectsQuery(query);
        response = querySolr(collection, namedObjectsQuery, clazz,
                requestMethod != null ? requestMethod : getDefaultRequestMethod());
        Map<String, Object> objectsName = namedObjectsQuery.getNamesAssociation();

        return createSolrResultPage(query, response, objectsName);
    }

    private <T> SolrResultPage<T> createSolrResultPage(Query query, QueryResponse response,
                                                       Map<String, Object> objectsName) {

//        List<T> beans = convertQueryResponseToBeans(response, clazz);
        SuggesterResponse suggesterResponse = response.getSuggesterResponse();
        List collection = suggesterResponse.getSuggestions().values().stream().collect(Collectors.toList());

        SolrResultPage<T> solrResultPage = new SolrResultPage<T>(collection);
        return solrResultPage;
    }

    final QueryResponse querySolr(String collection, SolrDataQuery query, @Nullable Class<?> clazz,
                                  @Nullable RequestMethod requestMethod) {

        Assert.notNull(query, "Query must not be 'null'");

        SolrQuery solrQuery = constructQuery(query, clazz);

//        if (clazz != null) {
//            SolrPersistentEntity<?> persistedEntity = mappingContext.getRequiredPersistentEntity(clazz);
//            if (persistedEntity.hasScoreProperty()) {
//                solrQuery.setIncludeScore(true);
//            }
//        }

        LOGGER.debug("Executing query '{}' against solr.", solrQuery);

        return executeSolrQuery(collection, solrQuery, getSolrRequestMethod(requestMethod));
    }


    final QueryResponse executeSolrQuery(String collection, final SolrQuery solrQuery, final SolrRequest.METHOD method) {
        return execute(solrServer -> solrServer.query(collection, solrQuery, method));
    }

    private SolrRequest.METHOD getSolrRequestMethod(@Nullable RequestMethod requestMethod) {

        RequestMethod rm = requestMethod != null ? requestMethod : getDefaultRequestMethod();

        switch (rm) {
            case GET:
                return SolrRequest.METHOD.GET;
            case POST:
                return SolrRequest.METHOD.POST;
            case PUT:
                return SolrRequest.METHOD.PUT;
        }

        throw new IllegalArgumentException("Unsupported request method type");
    }
}
