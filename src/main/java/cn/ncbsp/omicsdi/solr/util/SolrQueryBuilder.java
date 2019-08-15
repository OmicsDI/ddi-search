package cn.ncbsp.omicsdi.solr.util;

import cn.ncbsp.omicsdi.solr.queryModel.QueryModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleQuery;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolrQueryBuilder {

    /*
    简单查询
    合成query语句
     */
    public static SimpleQuery buildQuery(QueryModel queryModel) {
        SimpleQuery simpleQuery = null;
        if (StringUtils.isBlank(queryModel.getQuery())) {
            simpleQuery = new SimpleQuery("*:*");
        } else {
            simpleQuery = new SimpleQuery(queryModel.getQuery());
        }
        return simpleQuery;
    }

    ;


    /*
    facet查询
    1.无query则查询全部
    2.限制展示条数10条
    3.检测所有queryModel中条件，非空后合成simpleFacetQuery
     */
    public static SimpleFacetQuery buildSimpleFacetQuery(QueryModel queryModel) {

        if (queryModel == null) {
            return null;
        }

        SimpleFacetQuery simpleFacetQuery = null;
        Criteria criteria = new Criteria();

        // 生成query
        if (StringUtils.isBlank(queryModel.getQuery())) {
            simpleFacetQuery = new SimpleFacetQuery(criteria.expression("*:*"));
        } else {
            simpleFacetQuery = new SimpleFacetQuery(criteria.expression(queryModel.getQuery()));
        }

        // 添加rows
        if (queryModel.getSize() != 0) {
            simpleFacetQuery.setRows(queryModel.getSize());
        }

        // 添加facet功能
        FacetOptions facetOptions = new FacetOptions();
        if (queryModel.getFacets().length > 0 || queryModel.getFacets() != null) {
            for (String field : queryModel.getFacets()) {
                facetOptions.addFacetOnField(field);
            }

        }
        // todo 其他的facet功能
        // 控制facet页数
        if (queryModel.getFacetcount() != 0) {
            facetOptions.setFacetLimit(queryModel.getFacetcount());
        }

        // 检测是否包含facet
        if (facetOptions.hasFacets()) {
            simpleFacetQuery.setFacetOptions(facetOptions);
        }


        return simpleFacetQuery;
    }


    private static Criteria buildCriteria(Criteria criteria, Map<String, List<String>> attribute) {
        Set<String> attributeKey = attribute.keySet();

        for (String key : attributeKey) {
            List<String> qValues = attribute.get(key);
            criteria = criteria.where(key).in(qValues);
        }

        return criteria;
    }

    private static FacetOptions buildFacetOptions(FacetOptions facetOptions, List<String> attributes) {
        for (String attribute : attributes) {
            facetOptions = facetOptions.addFacetOnField(attribute);
        }
        return facetOptions;
    }

    /*
    简单facet查询
     */

    public static void commonBuildQuery() {

    }

}
