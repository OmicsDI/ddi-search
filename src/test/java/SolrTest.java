import cn.ncbsp.omicsdi.solr.controller.Constans;
import cn.ncbsp.omicsdi.solr.model.*;
import cn.ncbsp.omicsdi.solr.model.Suggestion;
import cn.ncbsp.omicsdi.solr.mongoRepo.IMongoDataRepo;
import cn.ncbsp.omicsdi.solr.mongoRepo.mongoModel.Dataset;
import cn.ncbsp.omicsdi.solr.mongoService.IMongoService;
import cn.ncbsp.omicsdi.solr.queryModel.*;
import cn.ncbsp.omicsdi.solr.repo.SolrEntryRepo;
import cn.ncbsp.omicsdi.solr.services.*;
import cn.ncbsp.omicsdi.solr.solrTool.SolrSuggestTemplate;
import cn.ncbsp.omicsdi.solr.solrmodel.Entry;
import cn.ncbsp.omicsdi.solr.solrmodel.*;
import cn.ncbsp.omicsdi.solr.util.SolrQueryBuilder;
import cn.ncbsp.omicsdi.solr.util.XmlHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.SolrHttpRequestRetryHandler;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.request.GenericSolrRequest;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.request.RequestWriter;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.client.solrj.response.schema.SchemaResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.*;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.data.solr.core.schema.SolrJsonRequest;
import org.springframework.data.solr.core.schema.SolrJsonResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//import cn.ncbsp.omicsdi.solr.repo.MoneyRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:springMVC/spring-mvc-sso.xml")
public class SolrTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    SolrTemplate solrTemplate;

//    @Autowired
//    MoneyRepo moneyRepo;

//    @Autowired
//    IDatabaseService databaseService;
    @Autowired
    HttpSolrClient solrClient;
    @Autowired
    ISolrEntryService iSolrEntryService;


    //    @Test
//    public void test3() {
//        Money money = new Money();
//        money.setId("Pony Coin");
//        money.setName("PCI");
//        solrTemplate.saveDocument();
//    }
    @Autowired
    SolrEntryRepo solrEntryRepo;
//    @Autowired
//    IDomainSearchService domainSearchService;
//    @Autowired
//    IAutocompleteService autocompleteService;
    @Autowired
    ISolrCustomService solrCustomService;
    @Autowired
    ISolrFacetService solrFacetService;
    @Autowired
    ISolrTaxonEntryService solrTaxonEntryService;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    IMongoDataRepo mongoDataRepo;
    @Autowired
    IMongoService mongoService;
    @Autowired
    private SolrSuggestTemplate solrSuggestTemplate;

    @Test
    public void test1() {
        SimpleQuery sq = new SimpleQuery("name:Dollar");
        Optional<Money> m = solrTemplate.queryForObject("new_core", sq, Money.class);
        System.out.println(m.get().toString());
    }

    @Test
    public void test2() {
//        List<Money> list = moneyRepo.findByName("Dollar");
//        for(Money m: list) {
//            System.out.println(m.toString());
//        }
    }

    @Test
    public void test3() {
        SolrQuery query = new SolrQuery();
        query.set("q", "name:Dollar");
        try {
            SolrDocumentList list = solrClient.query(query).getResults();
            System.out.println(list.toString());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        Database database = new Database();
        database = XmlHelper.xmlToObject("C:\\Users\\MS\\Desktop\\solr\\PRIDE_EBEYE_1.xml", database);
        System.out.println(database.getName().toString());
    }

    @Test
    public void test5() {
        Database database = new Database();
        database = XmlHelper.xmlToObject("C:\\Users\\MS\\Desktop\\solr\\PRIDE_EBEYE_1.xml", database);
        solrTemplate.saveBean("omicsdi", database);
        solrTemplate.commit("omicsdi");
//            String updateResponse = databaseService.indexSolrData("omicsdi",database);
//            System.out.println(updateResponse);
//            SolrInputDocument inputDocument = new SolrInputDocument();
//            inputDocument.addField("database_name",database.getName());
//            UpdateResponse updateResponse = solrTemplate.saveDocument("omicsdi",inputDocument);
//            System.out.println(updateResponse.getResponse().toString());
//            try {
//                solrClient.add(inputDocument);
//                UpdateResponse updateResponse = solrClient.commit();
//                System.out.println(updateResponse);
//            } catch (SolrServerException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
    }

    @Test
    public void test6() {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "552199");
        document.addField("name", "Gouda cheese wheel");
        document.addField("price", "49.99");
        try {
            UpdateResponse response = solrClient.add(document);
            UpdateResponse response2 = solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addDocument() {
        String SOLR_URL = new String("http://localhost:8983/solr/");
        //创建一个连接,my_core为我创建的一个实例名
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(SOLR_URL + "new_core")
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000);
        HttpSolrClient solr = builder.build();
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();

        //往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义
        document.addField("id", "test001");
        document.addField("item_price", 123);
        document.addField("item_title", "测试商品1");

        try {
            //把这个文档对象写入索引库
            solr.add(document);
            //提交
            solr.commit();
            solr.close();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void test7() {
        Database database = new Database();
        database = XmlHelper.xmlToObject("C:\\Users\\MS\\Desktop\\solr\\PRIDE_EBEYE_1.xml", database);
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("name", database.getName());
        solrInputDocument.addField("description", database.getDescription());
        solrInputDocument.addField("release", database.getRelease());
        solrInputDocument.addField("release_date", database.getReleaseDate());
        solrInputDocument.addField("entry_count", database.getEntryCount());

        database.getEntries().getEntry().stream().forEach(x -> {
            x.getCrossReferences().getRef().stream().forEach(ycr -> {
                solrInputDocument.addField(ycr.getDbname(), ycr.getDbkey());
            });
            x.getAdditionalFields().getField().stream().forEach(yaf -> {
                solrInputDocument.addField(yaf.getName(), yaf.getValue());
            });
        });

//        databaseService.indexSolrData("omicsdi", solrInputDocument);


    }

    //    @Test
//    public void test8() {
//        databaseService.deleteDocument("omicsdi","Pride");
//    }
    @Test
    public void test8() {
//        SolrEntry solrEntry = new SolrEntry();
//        solrEntry.setDatabase("dbdbdbdb2");
//        List<String> list = new ArrayList<>();
//        list.add("test1 test4");
//        list.add("test2 test5");
//        list.add("test3 test6");
//        solrEntry.setPublication(list);
//        solrEntry.setAcc("AMD 1700X");
//        DataDate dataDate = new DataDate();
//        dataDate.setDay("11");
//        dataDate.setMonth("09");
//        dataDate.setYear("17");
//        java.util.Date date = new java.util.Date(2018,10,27);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-mm-dd");
//        String str = simpleDateFormat.format(date);
//
//        System.out.println(str);
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("heiheihei");
        list.add("hohoho");
        list.add("hoheiho");
        map.put("test", list);
        map.put("publication", list);
        map.put("submitter_mail", list);
        TestModel testModel = new TestModel(map);

//        solrEntry.setDatePublication(date);
//        List<String> list = new ArrayList<String>();
//        list.add("omicsDI_type1");
//        list.add("omicsDI_type2");
//        solrEntry.setOmicsType(list);
        solrTemplate.saveBean("omicsdi", testModel);
        solrTemplate.commit("omicsdi");
    }

    @Test
    public void test9() {
        iSolrEntryService.saveSolrEntry("C:\\Users\\MS\\Desktop\\solr\\PRIDE_EBEYE_2.xml");
    }

    @Test
    public void test10() {
//        iSolrEntryService.saveSolrEntries("C:\\Users\\MS\\Desktop\\solr","omicsdi");
    }

    @Test
    public void test11() {
//        solrEntryRepo.getEntry("omicsdi");
//        solrClient.
        FacetQuery facetQuery = new SimpleFacetQuery();
        FacetOptions facetOptions = new FacetOptions();
        facetOptions.addFacetOnField("database");
        facetQuery.setFacetOptions(facetOptions);
        facetQuery.addCriteria(new Criteria().where("id").isNotNull());
        FacetPage<SolrEntry> facetPage = solrTemplate.queryForFacetPage("omicsdi", facetQuery, SolrEntry.class);

        System.out.println("ok");
        System.gc();
    }
//
//    @Test
//    public void test12() {
//        solrEntryRepo.getQueryResult("omicsdi", new SimpleQuery(new Criteria().where("id").contains("PXD").and("name").isNotNull()), new SolrEntry().getClass());
//        System.out.println();
//    }
//
//    @Test
//    public void test13() {
//        solrEntryRepo.getFacetQueryResult("omicsdi", new SimpleFacetQuery(new Criteria().where("id").in(new String[]{"PXD000025", "PXD004655"})).setFacetOptions(new FacetOptions("database")), SolrEntry.class);
//    }

    @Test
    public void test14() {
//        QueryModel queryModel = new QueryModel();
//        Map<String,List<String>> map = new HashMap<>();
//        List<String> list = new ArrayList<>();
//        list.add("PXD004655");
//        list.add("PXD000025");
//        map.put("id",list);
//        List<String> listName = new ArrayList<>();
//        list.add("Phosphoproteomic analysis of Rhodopseudomonas palustris");
//        list.add("Arginine (di)methylated Human Leukocyte Antigen class I peptides are favorably presented by HLA-B*07");
//        map.put("name",listName);
//        queryModel.setQ(map);
//
//        List<String> listFacet = new ArrayList<>();
//        listFacet.add("database");
//        queryModel.setFacet(listFacet);
//        SimpleFacetQuery simpleFacetQuery = SolrQueryBuilder.buildSimpleFacetQuery(queryModel);
//        SolrResultPage<SolrEntry> solrResultPage = (SolrResultPage<SolrEntry>) solrEntryRepo.getFacetQueryResult("omicsdi",simpleFacetQuery,SolrEntry.class);
//
//

//        SimpleQuery simpleQuery = SolrQueryBuilder.buildQuery(queryModel);
//        simpleQuery.setRows(3);
//        SolrResultPage<SolrEntry> solrEntrySolrResultPage =  solrEntryRepo.getQueryResult("omicsdi",simpleQuery,SolrEntry.class);
//        System.out.println(solrResultPage);
    }

    @Test
    public void test15() {
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        SolrResultPage<SolrEntry> solrEntrySolrResultPage = solrTemplate.query("omicsdi", simpleQuery, SolrEntry.class);
        System.out.println(solrEntrySolrResultPage);

    }

    @Test
    public void test16() {
//        Set<String> set = new HashSet<String>();
//        set.add("a");
//        set.add("b");
//        solrClient.setQueryParams(set);
//        solrClient.query("omicsdi",new SolrQuery());
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setFacet(true).addFacetField("id", "name");
        solrQuery.setQuery("*:* AND id:PXD000025 AND acc:PXD000025");
        try {
            QueryResponse queryResponse = solrClient.query("omicsdi", solrQuery);
//            List<SolrEntry> solrEntry = queryResponse.getBeans(SolrEntry.class);
            SolrDocumentList solrDocuments = queryResponse.getResults();
//            for(SolrEntry s : solrEntry) {
//                System.out.println(s);
//            }
            for (SolrDocument solrDocument : solrDocuments) {
                Set<String> set = solrDocument.keySet();
                for (String str : set) {
                    Object o = solrDocument.get(str);
                    System.out.println(o);
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUtils1() {
        QueryModel queryModel = new QueryModel();
        queryModel.setQuery("");
        SolrQueryBuilder.buildQuery(queryModel);
    }

    @Test
    public void testUtils2() {
        QueryModel queryModel = new QueryModel();
        queryModel.setSize(5);
        queryModel.setQuery("*:*");
        queryModel.setFacets(new String[]{"id"});
        SimpleFacetQuery simpleFacetQuery = SolrQueryBuilder.buildSimpleFacetQuery(queryModel);
        FacetPage<SolrEntry> facetPage = solrTemplate.queryForFacetPage("omicsdi", simpleFacetQuery, SolrEntry.class);
        System.out.println(facetPage);
    }

    @Test
    public void testService() {
        QueryModel queryModel = new QueryModel();
        queryModel.setSize(5);
        queryModel.setDomain("omicsdi");
        queryModel.setQuery("*:*");
        queryModel.setFacets(new String[]{"database", "additional_submitter"});
        queryModel.setFacetcount(6);
//        domainSearchService.getQueryResult(queryModel);
    }

    @Test
    public void testAllField() {
        try {
            NamedList<Object> namedList = solrClient.request(new SchemaRequest.Fields(), "omicsdi");
            NamedList<Object> namedList2 = solrClient.request(new SchemaRequest.DynamicFields(), "omicsdi");
            NamedList<Object> namedList3 = solrClient.request(new LukeRequest(), "omicsdi");

            Object o = namedList3.get("fields");
            NamedList<Map<String, String>> nl = (NamedList<Map<String, String>>) o;

            ArrayList<String> arr = new ArrayList<String>();

            for (int i = 0; i < nl.size(); i++) {
                arr.add(nl.getName(i));
            }
            System.out.println("ok");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSuggest() {
//
        SimpleQuery simpleQuery = new SimpleQuery("a");
        simpleQuery.setRequestHandler("/suggest");
//        SolrResultPage<Suggestions> solrEntrySolrResultPage = solrTemplate.query("omicsdi",simpleQuery,Suggestions.class);
        SolrResultPage<Suggestions> solrResultPage = solrSuggestTemplate.query("omicsdi", simpleQuery, Suggestions.class);

        System.out.println("a?");


//        System.out.println("a?");
//
//        SimpleTermsQuery simpleTermsQuery = new SimpleTermsQuery();
//        simpleTermsQuery.addCriteria(new Criteria().expression("a"));
//        TermsPage termsPage = solrTemplate.queryForTermsPage("omicsdi",simpleTermsQuery);
        System.out.print("a?");

//        SimpleHighlightQuery


//        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.setRequestHandler("/suggest");
//        solrQuery.set("q","a");
//        try {
//            QueryResponse queryResponse = solrClient.query("omicsdi",solrQuery);
//            System.out.println("ok?");
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("ok?");
//        SimpleQuery simpleQuery = new SimpleQuery();
//        simpleQuery.setRequestHandler("/suggest");
//        SolrResultPage<Suggestions> solrEntrySolrResultPage = (SolrResultPage<Suggestions>) solrTemplate.queryForPage("omicsdi",simpleQuery, Suggestions.class);
////        SolrResultPage<Suggestions> solrEntrySolrResultPage = solrTemplate.query("omicsdi",simpleQuery, Suggestions.class);

//        SuggesterResponse suggesterResponse = new SuggesterResponse();
//
//        SimpleTermsQuery simpleTermsQuery = new SimpleTermsQuery();
//        simpleTermsQuery.setRequestHandler("/suggest");
//        simpleTermsQuery.addCriteria(new Criteria().expression("q=an"));
//        TermsPage termsPage = solrTemplate.queryForTermsPage("omicsdi",simpleTermsQuery);
//        SolrResultPage<Suggestions> solrEntrySolrResultPage = solrTemplate.query("omicsdi",simpleQuery, Suggestions.class);
//        System.out.println("a?");
//
//        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.setRequestHandler("/suggest");
//        solrQuery.set("q","a");
//
//        try {
//            QueryResponse queryResponse = solrClient.query("omicsdi",solrQuery);
//            SuggesterResponse suggesterResponse = queryResponse.getSuggesterResponse();
//            Map<String, List<Suggestion>> mapSuggest = suggesterResponse.getSuggestions();
//            System.out.println("a");
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testSuggestService() {
//        Suggestions suggestions = autocompleteService.getSuggestions("omicsdi", "an", Suggestion.class);
        System.out.println("a");
    }

    @Test
    public void testRegex() {
        String regex = "[a-zA-Z0-9]+";
        Boolean b = Pattern.matches(regex, "asdf9087sdfKHE");
        System.out.println(b);

    }

    @Test
    public void testSuggestionService() {
        Map<String, String[]> map = new HashMap<>();
        map.put("q", new String[]{"a"});
//        solrCustomService.getSuggestResult("omicsdi",map);
    }

    @Test
    public void testMoreLikeThisSerivce() {
        Map<String, String[]> map = new HashMap<>();
        map.put("q", new String[]{"id:PXD002734"});
        solrCustomService.getSimilarResult("omicsdi", map);
    }

    @Test
    public void testAPI() {
        SolrQuery solrQuery = new SolrQuery();
//        FacetParams

        Map<String, String[]> paramMap = new HashMap<>();

        String[] fields = new String[]{"description", "database"};
        paramMap.put(TermsParams.TERMS_FIELD, fields);

//        solrCustomService.getFrequentlyTerms("omicsdi", paramMap);
    }

    @Test
    public void testhihihi() {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "id:PXD002734");
        solrQuery.setFacet(true);
        solrQuery.addFacetField("acc");
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query("omicsdi", solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hihihihihihi");
    }

    @Test
    public void testfacetImple() {
//        Map<String,String[]> paramMap = new HashMap<>();
//        paramMap.put("q", new String[]{"*:*"});
//        paramMap.put(FacetParams.FACET, new String[]{"database"});
//        solrFacetService.getFacetEntriesByDomains("omicsdi", paramMap);
        FacetQuery facetQuery = new SimpleFacetQuery(new Criteria().expression("*:*"));
        FacetOptions facetOptions = new FacetOptions();
        facetOptions.addFacetOnField("database");
        facetQuery.setFacetOptions(facetOptions);

//        FacetPage<SolrEntry> facetPage = solrTemplate.queryForFacetPage("omicsdi",simpleFacetQuery,SolrEntry.class);
        FacetPage<SolrEntry> facetPage = solrTemplate.queryForFacetPage("omicsdi", facetQuery, SolrEntry.class);

        System.out.println("x");
    }

    @Test
    public void TestParam() {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set(CommonParams.Q, "*:*");
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query("omicsdi", solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }

    @Test
    public void testInvoke() {
//        Field[] fields = FacetParams.class.getDeclaredFields();
//        for(Field field : fields) {
//            field.setAccessible(true);
//            String name = field.getName();
//        }
//
//        FacetParams.class.getMethods();

//        Field[] fields = QueryModel.class.getFields();
//        Method[] methods = QueryModel.class.getMethods();
        QueryModel queryModel = new QueryModel();
        queryModel.setDomain("asdfdsa");
        queryModel.setQuery("hihihihihi");

        try {
            Field field = queryModel.getClass().getDeclaredField("domain");
            field.setAccessible(true);
            String str = (String) field.get(queryModel);
            System.out.println(str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

//        Class clazz = queryModel.getClass();
//        Field[] fields = clazz.getFields();
//        for(Field field : fields) {
//            try {
//                Method method = clazz.getMethod("get" + field.getName());
//                String value = (String) method.invoke(queryModel);
//                System.out.println(value);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }


//        try {
//            Class clazz = Class.forName(queryModel.getClass().getName());
//            Method[] methods = clazz.getMethods();
//            Field[] fields = clazz.getFields();
//
//
//            for(Field field : fields) {
//                Method method = clazz.getMethod("get" + field.getName());
//                String name = (String) method.invoke(clazz.newInstance());
//                System.out.println(name);
//            }
//
//
//            for(Method method : methods) {
//
//            }
////            clazz.getMethod()
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testAutoSolr() {
        String str = FacetParams.FACET;
        String str2 = CommonParams.Q;
        System.out.println(QueryModel.class.getName());
    }

    @Test
    public void testFacetImpl() {
        Map<String, String[]> paramMap = new HashMap<>();
        paramMap.put("facetfields", new String[]{"TAXONOMY"});
        paramMap.put("database", new String[]{"Pride"});
        paramMap.put("q", new String[]{"*:*"});
//        solrFacetService.getFacetEntriesByDomains("omicsdi",paramMap);
    }

    @Test
    public void TT() {
        String str = "domain_source:(arrayexpress-repository OR atlas-experiments OR biomodels OR dbgap OR ega OR eva OR geo OR gnps OR gpmdb OR jpost OR lincs OR massive OR metabolights_dataset OR metabolome_express OR metabolomics_workbench OR omics_ena_project OR paxdb OR peptide_atlas OR pride)";
        String[] str1 = str.substring(15, str.length() - 1).split(" OR ");
        System.out.println(str1);
    }

    @Test
    public void autoQueryBuilder() {

        SimpleQueryModel simpleQueryModel = new SimpleQueryModel();
        simpleQueryModel.setQ("id:PXD002734" + " " + SimpleParams.OR_OPERATOR + " " + "id:PXD002565");


//        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.setQuery("id:PXD002734"+" "+SimpleParams.OR_OPERATOR+" "+"id:PXD002565");
////        solrQuery.setParam(CommonParams.Q, "id:PXD001224 OR PXD002734");
////        solrQuery.set(CommonParams.Q, new String[]{"id:PXD011618 OR PXD021618"});
////        solrQuery.set(CommonParams.Q, "id:PXD001224 OR PXD002734");
////        solrQuery.add(CommonParams.Q, new String[]{"id:PXD002734","id:PXD002565"});
////        solrQuery.add(CommonParams.Q, new String[]{"id:PXD002565"});
////        solrQuery.set("id", new String[]{"*"});
////        solrQuery.set("database", new String[]{"Pride","Jpost"});
//        QueryResponse queryResponse = null;
//        try {
//            queryResponse = solrClient.query("omicsdi", solrQuery);
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("ok");
    }

    @Test
    public void testInvoke2() {
        IQModel iqModel = new SimpleQueryModel();
        SolrQuery solrQuery = new SolrQuery();
        Field[] fields = iqModel.getClass().getDeclaredFields();
        cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildQuery(iqModel, solrQuery, fields);
//        cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(iqModel);
        System.out.println("x");
    }

    @Test
    public void newUtils() {
        IQModel iqModel = new SimpleQueryModel();
        ((SimpleQueryModel) iqModel).setQ("*:*");
        SolrQuery solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(iqModel);
        FacetQueryModel facetQueryModel = new FacetQueryModel();
//        facetQueryModel.setFacet("true");
        facetQueryModel.setFacet_field("database,id");
        solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildAdvancedSolrQuery(facetQueryModel, solrQuery);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query("omicsdi", solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("x");
    }

    @Test
    public void testFinalStatic() {
        MLTQueryModel mltQueryModel = new MLTQueryModel();
        Field[] fields = mltQueryModel.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("xxx");
    }

    @Test
    public void testSolrQuery() {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setFacet(true);
        String str = solrQuery.get("facet");
        System.out.println(str);

    }

    @Test
    public void testTerms() {
//        TermsQueryModel termsQueryModel = new TermsQueryModel();
//        termsQueryModel.setTerms_fl("default_search");
////        termsQueryModel.setTerms_list("PXD,protein,proteins,analysis,we,2016");
//        SolrQuery solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(termsQueryModel);
//        TermResult termResult = solrCustomService.getFrequentlyTerms("omicsdi", termsQueryModel);

        FacetQueryModel facetQueryModel = new FacetQueryModel();
        facetQueryModel.setQ("*:*");
        facetQueryModel.setFacet_field("description,name,additional_submitter_keywords,additional_curator_keywords,date_publication,TAXONOMY,omics_type,additional_ensembl,additional_uniprot,additional_chebi,citation_count,view_count,search_count");
        SolrQuery solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(facetQueryModel);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query("omicsdi", solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setHitCount(queryResponse.getResults().size());

        Entry[] entries = queryResponse.getResults().stream().map(x -> {
            Entry entry = new Entry();
            entry.setScore(null);
            entry.setId((String) x.get("id"));
            entry.setSource((String) x.get("database"));
            Set<String> keys = x.getFieldValueMap().keySet();
            Map<String, String[]> map = new HashMap<>();
            Set<String> set = x.getFieldValueMap().keySet();
            for (String key : keys) {
                if (x.get(key) instanceof String) {
                    String str = (String) x.get(key);
                    map.put(key, new String[]{str});
                } else if (x.get(key) instanceof Long) {
                    String str = String.valueOf(x.get(key));
                    map.put(key, new String[]{str});
                } else {
                    ArrayList<String> list = (ArrayList<String>) x.get(key);
                    String[] str = new String[list.size()];
                    str = list.toArray(str);
                    map.put(key, str);
                }
            }
            entry.setFields(map);
            return entry;
        }).toArray(Entry[]::new);
        queryResult.setEntries(entries);

        queryResult.setDomains(null);
        Facet[] facets = queryResponse.getFacetFields().stream().map(x -> {
            Facet facet = new Facet();
            facet.setId(x.getName());
            facet.setLabel(x.getName());
            Long total = x.getValues().stream().mapToLong(y -> y.getCount()).sum();
            facet.setTotal(Math.toIntExact(total));
            FacetValue[] facetValues = x.getValues().stream().map(z -> {
                FacetValue facetValue = new FacetValue();
                facetValue.setValue(z.getName());
                facetValue.setLabel(z.getName());
                facetValue.setCount(String.valueOf(z.getCount()));
                return facetValue;
            }).toArray(FacetValue[]::new);
            facet.setFacetValues(facetValues);
            return facet;
        }).toArray(Facet[]::new);
        queryResult.setFacets(facets);
        queryResult.setHitCount(queryResponse.getResults().size());


//        solrFacetService.getFacetEntriesByDomains("omicsdi", facetQueryModel);
//
////        SolrQuery solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(facetQueryModel);
//        System.out.println("x");


//        Class clazz = facetQueryModel.getClass().getSuperclass();
//        if(clazz != null) {
//            System.out.println("1");
//        }
//
//        SimpleQueryModel simpleQueryModel = new SimpleQueryModel();
//        Class clazz1 = simpleQueryModel.getClass().getSuperclass();
//        System.out.println(clazz1.getTypeName());
//        if(clazz1.getTypeName().equalsIgnoreCase("java.lang.Object")) {
//            System.out.println("2");
//        }
//        Method method = null;
//        String str = null;
//        try {
//            method = facetQueryModel.getClass().getDeclaredMethod("getQ");
//            str = (String) method.invoke(facetQueryModel);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

//        Field[] fields = facetQueryModel.getClass().getSuperclass().getDeclaredFields();
//        System.out.println("x");
//        Method[] methods = facetQueryModel.getClass().getDeclaredMethods();
//        for(Field field : fields) {
//
//        }
//        SolrQuery solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(facetQueryModel);
//        QueryResponse queryResponse = null;
//        try {
//            queryResponse = solrClient.query("omicsdi", solrQuery);
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("x");
    }

    @Test
    public void test33() {
//        SimpleQueryModel simpleQueryModel = new SimpleQueryModel();
//        Field[] fields = simpleQueryModel.getClass().getSuperclass().getFields();
//        System.out.println("x");
//        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.addSort();
//        solrQuery.setFacet();

        Term term = new Term();
        term.setDocFreq("12323123");
        term.setText("asdfdfdsasdfdsa");

        Item item = new Item("namenamenamename");

        String str = JSON.toJSONString(term);
        String str1 = JSON.toJSONString(item);

        Term term1 = JSON.parseObject(str, Term.class);
        System.out.println("x");

    }

    @Test
    public void TestProperties() {
        Properties properties = new Properties();
        InputStream in = getClass().getResourceAsStream("properties/domainList.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
//            properties.load(new FileInputStream(new File("classpath*:properties/domainList.properties")));
        String id = (String) properties.get("id");
        String[] ids = id.split(";");
        for (String str : ids) {
            System.out.println(str);
        }
        System.out.println(ids.length);
        System.out.println(id);
    }

    @Test
    public void testTaxonomy() {
        FacetQueryModel facetQueryModel = new FacetQueryModel();
        facetQueryModel.setQ("taxonomy".toUpperCase() + ":(" + "9606 OR 70448 OR 5476" + ")");
        facetQueryModel.setFacet_field("name");
        QueryResult queryResult = iSolrEntryService.getQueryResult("omicsdi", facetQueryModel, null, null);
        System.out.println("xxxxx");
    }

    //https://www.ebi.ac.uk/ebisearch/ws/rest/taxonomy/entry/10090,9544,5476,9606,1140?fields=name&format=JSON

    @Test
    public void mysqlDump() {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream("C:\\Users\\MS\\Desktop\\solr\\新建文件夹\\names.dmp"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        Long l = bufferedReader.lines().count();
//        System.out.println(l);
//        try {
//            for(int i = 0;i<100;i++){
//                String str = bufferedReader.readLine();
//                System.out.println(str);
//                String[] strs = str.split("|");
//                String[] data = Arrays.asList(strs).stream().map(x -> {
//                    return x.trim();
//                }).toArray(String[]::new);
//                System.out.println(data.toString());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            while (bufferedReader.markSupported()) {
                String str = bufferedReader.readLine();
                System.out.println(str);
            }
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInput() throws IOException {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream("D:\\omicsDI\\solrAPI\\names.dmp"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        int j = 0;
        int i = 0;
        List<NCBITaxonomy> list = new ArrayList<NCBITaxonomy>();
        while ((str = bufferedReader.readLine()) != null) {
            j++;
            i++;
            String[] params = str.split("\\|");
            NCBITaxonomy ncbiTaxonomy = new NCBITaxonomy();
            ncbiTaxonomy.setTaxId(params[0].trim());
            ncbiTaxonomy.setNameTxt(params[1].trim());
            ncbiTaxonomy.setUniqueName(params[2].trim());
            ncbiTaxonomy.setNameClass(params[3].trim());
            list.add(ncbiTaxonomy);
            if (i >= 1000) {
                i = 0;
                solrTemplate.saveBeans("taxonomy", list);
                solrTemplate.commit("taxonomy");
                list.clear();
            }
        }

        if (list.size() > 0) {
            solrTemplate.saveBeans("taxonomy", list);
            solrTemplate.commit("taxonomy");
        }
//        for(int i = 0;i<100;i++){
////            String[] str = bufferedReader.readLine().split("|");
////            System.out.println(str[0]+str[1]+str[2]+str[3]);
//            String str = bufferedReader.readLine();
//            System.out.println(str);
//        }
    }

    @Test
    public void testNewSchema() {
        NCBITaxonomy ncbiTaxonomy = new NCBITaxonomy();
//        ncbiTaxonomy.setId(UUID.randomUUID().toString());
        ncbiTaxonomy.setNameClass("yohiohiohiohio");
        ncbiTaxonomy.setNameTxt("yihihihihihihih");
        ncbiTaxonomy.setTaxId("jopiopiopiopio");
        ncbiTaxonomy.setUniqueName("xiohiohiohiohio");
        UpdateResponse updateResponse = solrTemplate.saveBean("taxonomy", ncbiTaxonomy);
        solrTemplate.commit("taxonomy");
    }

    @Test
    public void testTaxono() {
        FacetQueryModel facetQueryModel = new FacetQueryModel();
        facetQueryModel.setQ("tax_id:(10090 OR 9544 OR 5476 OR 9606) AND name_class:\"scientific name\"");
        QueryResult queryResult = solrTaxonEntryService.getTaxonomy("taxonomy", facetQueryModel);
    }

    @Test
    public void newSuggest() {
        SuggestQueryModel suggestQueryModel = new SuggestQueryModel();
        suggestQueryModel.setSuggest_q("a");
        suggestQueryModel.setSuggest_count("10");
        SolrQuery solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(suggestQueryModel);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query("omics", solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, List<org.apache.solr.client.solrj.response.Suggestion>> map = queryResponse.getSuggesterResponse().getSuggestions();
//        Suggestions suggestions = new Suggestions();
        List<org.apache.solr.client.solrj.response.Suggestion> list = map.get("mySuggester");
        Suggestion[] suggestions = list.stream().map(x -> {
            Suggestion suggestion = new Suggestion();
            suggestion.setSuggestion(x.getTerm());
            return suggestion;
        }).toArray(Suggestion[]::new);
        Suggestions suggestionsCollection = new Suggestions();
        suggestionsCollection.setSuggestions(suggestions);


        System.out.println("XXXXX");
    }

//    @Autowired
//    MongoClient mongoClient;

    @Test
    public void MLTNEW() {
        MLTQueryModel mltQueryModel = new MLTQueryModel();
        mltQueryModel.setQ("id:PXD002734 AND database:pride");
//        https://www.ebi.ac.uk/ebisearch/ws/rest/pride/entry/PXD000210/morelikethis/omics?mltfields=name,description,data_protocol,sample_protocol,omics_type&excludesets=omics_stopwords&entryattrs=score&format=JSON
        mltQueryModel.setFl("name,description,omics_type,score");
        SolrQuery solrQuery = cn.ncbsp.omicsdi.solr.queryModel.SolrQueryBuilder.buildSolrQuery(mltQueryModel);
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query("omicsdi", solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NamedList<Object> namedList = queryResponse.getResponse();
        SolrDocumentList solrDocumentList = (SolrDocumentList) namedList.get("response");
        SimilarResult similarResult = new SimilarResult();
        Entry[] entries = solrDocumentList.stream().map(x -> {
            Entry entry = new Entry();
            entry.setId(String.valueOf(x.get("name")));
            entry.setScore(String.valueOf(x.get("score")));
            entry.setSource(String.valueOf(x.get("omics_type")));
            return entry;
        }).toArray(Entry[]::new);
        similarResult.setEntries(entries);

        System.out.println("xxxxxxx");
    }

    @Test
    public void fileSize() {
//        File file = new File("C:\\Users\\MS\\Desktop\\fileTest\\QQ视频20180821115305.mp4");
//        long l = file.length();
//        System.out.println(l);
    }

    @Test
    public void mongtest() {
        org.springframework.data.mongodb.core.query.Criteria criteria = new org.springframework.data.mongodb.core.query.Criteria();
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
//        mongoTemplate.count(query.addCriteria(criteria.))
//        query.addCriteria(org.springframework.data.mongodb.core.query.Criteria.where(""));
//        org.springframework.data.mongodb.core.query.Criteria.where("database").is("Pride");
        criteria = criteria.where("database").is("Pride");
        query.with(new Sort(Sort.Direction.DESC, "_id"));
//        query.with(new QPageRequest(0,10));
        query.addCriteria(criteria);
        query.limit(10);
//        List<Dataset> list = mongoTemplate.find(query,Dataset.class);
        List<Dataset> list = mongoTemplate.find(query, Dataset.class);
        System.out.println("xx");
        List<cn.ncbsp.omicsdi.solr.model.Entry> entryList = list.stream().map(x -> {
            cn.ncbsp.omicsdi.solr.model.Entry entry = new cn.ncbsp.omicsdi.solr.model.Entry();
            Map<String, Set<String>> crossReferencesMap = x.getCrossReferences();
            entry.setId(x.getAccession());
            entry.setAcc(x.getAccession());
            entry.setName(x.getName());
            entry.setDescription(x.getDescription());
            List<Reference> references = new ArrayList<>();
            for (String key : crossReferencesMap.keySet()) {
                for (String value : crossReferencesMap.get(key)) {
                    Reference reference = new Reference();
                    reference.setDbkey(key);
                    reference.setDbname(value);
                    references.add(reference);
                }
            }
            Map<String, Set<String>> additionalMap = x.getAdditional();
            List<cn.ncbsp.omicsdi.solr.model.Field> additionalFields = new ArrayList<>();
            for (String key : additionalMap.keySet()) {
                for (String value : additionalMap.get(key)) {
                    cn.ncbsp.omicsdi.solr.model.Field field = new cn.ncbsp.omicsdi.solr.model.Field();
                    field.setName(key);
                    field.setValue(value);
                    additionalFields.add(field);
                }
            }
            CrossReferences crossReferences = new CrossReferences();
            crossReferences.setRef(references);
            entry.setCrossReferences(crossReferences);
            AdditionalFields additionalFields1 = new AdditionalFields();
            additionalFields1.setField(additionalFields);
            entry.setAdditionalFields(additionalFields1);
            return entry;
        }).collect(Collectors.toList());

        Database database = new Database();
        database.setEntries(entryList);
        database.setName("Pride");
        database.setEntryCount(entryList.size());
        database.setReleaseDate(new Date().toString());
        System.out.println("xxxxx");
    }

    @Test
    public void unmarshall() {
        Database database = new Database();

        database = XmlHelper.xmlToObject("C:\\Users\\MS\\Desktop\\solr\\PRIDE_EBEYE_1.xml", database);

        Database database1 = new Database();
//        StringWriter sw = XmlHelper.objectToXml(database,"C:\\Users\\MS\\Desktop\\solr\\solrTest");

        System.out.println("xxxx");
    }

    @Test
    public void testMongoNum() {
        Map<String, Integer> map = mongoDataRepo.getAllDatabase();
        System.out.println("asdfsdafas");
    }

    @Test
    public void testMongoService() {
        mongoService.MongoToSolrXml();
    }

    @Test
    public void testSolrService() {
        iSolrEntryService.saveSolrEntries("C:\\omicsDI\\solrAPI\\output","C:\\omicsDI\\solrAPI\\backup");
    }

    @Test
    public void doDate() throws ParseException {
        String date = "Tue Feb 07 17:49:00 GMT 2017";
        String regex = "[a-zA-Z]{3}\\s[a-zA-Z]{3}\\s[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}\\s[a-zA-Z]{3}\\s[0-9]{4}";

        String a = "asd fds";
        String regex1 = "[a-zA-Z]{3}\\s[a-zA-Z]{3}";

        boolean b = date.matches(regex);
        boolean b1 = a.matches(regex1);

////        Date date1 = DateUtils.parseDate(date);
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        Date date2 = sdf.parse(date);
        System.out.println("999");
    }


    @Test
    public void testsolrssss() {
//        iSolrEntryService.saveSolrEntry("C:\\Users\\MS\\Desktop\\solr\\solrTest\\15452046331109.xml", "peptide_atlas");
    }


    @Test
    public void StrringBuffer() {
        StringBuffer stringBuffer = new StringBuffer("createTime");
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (Character.isUpperCase(stringBuffer.charAt(i))) {
                stringBuffer.replace(i, i + 1, "_" + Character.toLowerCase(stringBuffer.charAt(i)));
            }
        }
        System.out.println(stringBuffer);
    }


    @Test
    public void testTemplate() {
        org.springframework.data.mongodb.core.query.Criteria criteria = new org.springframework.data.mongodb.core.query.Criteria();
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        criteria = criteria.where("database").is("Pride");
        query.with(new Sort(Sort.Direction.DESC, "_id"));
        query.addCriteria(criteria);
        query.skip(4300).limit(100);
        List<Dataset> list = mongoTemplate.find(query, Dataset.class);
        Map<String, Integer> map = mongoDataRepo.getAllDatabase();

        System.out.println("asdfsdfasf");
    }

    @Test
    public void newSolr() {
        mongoService.MongoToSolrXml();
    }

    @Test
    public void testAdminQuery() {
        CoreAdminRequest coreAdminRequest = new CoreAdminRequest();
        coreAdminRequest.setAction(CoreAdminParams.CoreAdminAction.STATUS);
        try {
            CoreAdminResponse coreAdminResponse = coreAdminRequest.process(solrClient);
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
                domains.add(domain);
            }
            Domain[] domainCo = new Domain[domains.size()];
            domainList.setList(domains.toArray(domainCo));
            System.out.println("xxxxd");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("xxxxd");
    }



    @Test
    public void testLukeFunctions() {
//        LukeRequest lukeRequest = new LukeRequest();
//        try {
//            lukeRequest.addField("ok");
//            Object object = lukeRequest.process(solrClient).getResponse();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("x");



        Map<String, Object> newField = new HashMap<>();
        newField.put("name", "test");
        newField.put("type", "string");
        newField.put("stored", true);
        newField.put("indexed", true);

        SchemaRequest.AddField addField = new SchemaRequest.AddField(newField);

        try {
            SchemaResponse.UpdateResponse schemaResponse = addField.process(solrClient, "taxonomy");
            System.out.println("xxxx");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            SchemaResponse schemaResponse = schemaRequest.process(solrClient, "taxonomy");
//            SchemaRepresentation schemaRepresentation = schemaResponse.getSchemaRepresentation();
//            System.out.println("xxxx");
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        LukeRequest lukeRequest = new LukeRequest();
//        lukeRequest.addField("test");
//        try {
//            LukeResponse lukeResponse = lukeRequest.process(solrClient, "taxonomy");
//            Map<String, LukeResponse.FieldInfo> map = lukeResponse.getFieldInfo();
//            System.out.println("xx");
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Autowired
    ISolrSchemaService solrSchemaService;


    @Test
    public void autoGeneratorofSchema() {
        solrSchemaService.checkFieldExists("accession", "omics");
    }

    @Test
    public void autoDocument() {
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "test-01-01");
        UpdateResponse updateResponse = null;
        try {
            updateResponse = solrClient.add("atlas-experiments", solrInputDocument);
            updateResponse = solrClient.commit("atlas-experiments");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("xx");
    }

    @Test
    public void beanToSolrDoc() {
        SolrEntry solrEntry = new SolrEntry();
        solrEntry.setId("aaa-01-03");
        solrEntry.setDescriptionSynonyms("fddfdfsasdfdfdsasdfdsasdf");
        List<String> omicsType = new ArrayList<String>();
        omicsType.add("asdf");
        omicsType.add("asdfdsa");
        solrEntry.setOmicsType(omicsType);

        SolrInputDocument solrInputDocument = solrClient.getBinder().toSolrInputDocument(solrEntry);
        System.out.println("xxxx");
//        try {
//            solrClient.addBean("atlas-experiments", solrEntry);
//            solrClient.commit("atlas-experiments");
//        } catch (IOException | SolrServerException e) {
//            e.printStackTrace();
//        }
//        System.out.println("xxxx");

    }


    @Test
    public void newSolrdocsave() {
//        File file = new File("D:\\self-support_20190816.xml");
        Database database = new Database();
        database = XmlHelper.xmlToObject("D:\\NODE_self-support_20190823.xml", database);
        assert database != null;
        String core = Constans.Database.retriveSorlName(database.getName());
        Entries entries = database.getEntries();
        List<cn.ncbsp.omicsdi.solr.model.Entry> list = entries.getEntry();
        List<SolrInputDocument> solrInputDocuments = solrSchemaService.parseEntryToSolrInputDocument(list, database.getName().toLowerCase(), core);
        solrEntryRepo.saveEntryList("omics", solrInputDocuments);
        solrEntryRepo.saveEntryList(core, solrInputDocuments);
    }


    @Test
    public void stopwordsTest() {
//        StopwordsRequest stopwordsRequest = new StopwordsRequest(SolrRequest.METHOD.PUT, "/schema/analysis/stopwords/english");
//        stopwordsRequest.addContentToStream("aaa");
//        stopwordsRequest.setBasicAuthCredentials("solr", "OmicsDI@Solr@2019");
//        solrClient.setRequestWriter(new RequestWriter(){
//            @Override
//            public ContentWriter getContentWriter(SolrRequest req) {
//                return new ContentWriter() {
//                    @Override
//                    public void write(OutputStream os) throws IOException {
//                        JavaBinCodec javaBinCodec = new JavaBinCodec();
//                        javaBinCodec.marshal(req, os);
//                    }
//
//                    @Override
//                    public String getContentType() {
//                        return "application/json;charset=utf-8";
//                    }
//                };
//            }
//        });
//        try {
//            SolrResponse solrResponse = stopwordsRequest.process(solrClient, "NODE");
//            System.out.println("xxx");
//        } catch (SolrServerException | IOException e) {
//            e.printStackTrace();
//        }
//        SolrJsonRequest solrJsonRequest = new SolrJsonRequest(SolrJsonRequest.METHOD.PUT, "/schema/analysis/stopwords/english");
//        List<String> stopwords = new ArrayList<>();
//        stopwords.add("balabala");
//        Object ok = JSONObject.toJSON(stopwords);
//        solrJsonRequest.addContentToStream(stopwords);
//        solrJsonRequest.setBasicAuthCredentials("solr", "OmicsDI@Solr@2019");
//        try {
//            solrJsonRequest.process(solrClient, "NODE");
//
//            System.out.println("xxx");
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        SolrJsonRequest solrJsonRequest2 = new SolrJsonRequest(SolrJsonRequest.METHOD.GET, "/schema/analysis/stopwords/english");
//        solrJsonRequest2.setBasicAuthCredentials("solr", "OmicsDI@Solr@2019");
//        try {
//            solrClient.request(solrJsonRequest2, "NODE");
////            SolrJsonResponse solrJsonResponse = solrJsonRequest2.process(solrClient, "NODE");
//            System.out.println("xxx");
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        GenericSolrRequest genericSolrRequest = new GenericSolrRequest(SolrRequest.METHOD.PUT, "/schema/analysis/stopwords/english", null);


//        List<String> stopwords = new ArrayList<>();
//        stopwords.add("casdfasdkljfkasdjf");
//        String ok = JSONObject.toJSON(stopwords).toString();
//        JSONArray jsonObject = JSONObject.parseArray(str);
//        String ok = jsonObject.toJSONString();
//        genericSolrRequest.setContentWriter(new RequestWriter.StringPayloadContentWriter(ok, "application/json;charset=utf-8"));

        genericSolrRequest.setBasicAuthCredentials("solr", "OmicsDI@Solr@2019");

        genericSolrRequest.setResponseParser(new DelegationTokenResponse.JsonMapResponseParser());

        ArrayList<String> oks = new ArrayList<>();
        oks.add("asdf1234");
        oks.add("balabala1234");
        oks.add("casdfasdkljfkasdjf1234");
        oks.add("ccc1234");

        String str = JSONArray.toJSONString(oks);
        genericSolrRequest.setContentWriter(new RequestWriter.StringPayloadContentWriter(str, "application/json;charset=utf-8"));

        try {
            SimpleSolrResponse simpleSolrResponse = genericSolrRequest.process(solrClient, "NODE");
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) simpleSolrResponse.getResponse().get("responseHeader");
            Object o = map.get("status");

            if(String.valueOf(o).equalsIgnoreCase("0")) {
                System.out.println("lklklklkl");
            }
            System.out.println("xxx");
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }





































    @Test
    public void testUpdateSOlrDoc() {
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "*");
        solrInputDocument.addField("database" ,"omics_ena_project");
        try {
            UpdateResponse updateResponse = solrClient.add("omics_ena_project", solrInputDocument);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("123123");
    }
}
