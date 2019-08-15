package cn.ncbsp.omicsdi.solr.queryModel;

import cn.ncbsp.omicsdi.solr.constant.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.solr.client.solrj.SolrQuery;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Xpon
 */
public class SolrQueryBuilder {

    /**
     * @param iqModel
     * @return solrQuery
     * 通用型SolrQueryBuilder
     * 主旨在于将对象转化为solr查询语句
     * <p>
     * todo 由于使用了java反射，object类型数据转化会有问题，暂时将对象转化为以“,”为分隔符的字符串，
     * todo 统一SolrQueryModel的属性数据类型为String
     * @Author Xpon 2018-11-15
     */
    public static SolrQuery buildSolrQuery(IQModel iqModel) {
        SolrQuery solrQuery = new SolrQuery();
//        Field[] fields = iqModel.getClass().getDeclaredFields();
        Field[] fields = ArrayUtils.addAll(iqModel.getClass().getDeclaredFields(), iqModel.getClass().getSuperclass().getDeclaredFields());
        // 如果有Request_handler 添加进去
//        for(Field field : fields) {
//            if(field.getName().equalsIgnoreCase("REQUEST_HANDLER")) {
//                solrQuery.setRequestHandler(field.getName());
//            }
//        }
        solrQuery = buildQuery(iqModel, solrQuery, fields);
        return solrQuery;
    }


    public static SolrQuery buildAdvancedSolrQuery(IQModel iqModel, SolrQuery solrQuery) {
//        Field[] fields = iqModel.getClass().getDeclaredFields();
        Field[] fields = ArrayUtils.addAll(iqModel.getClass().getDeclaredFields(), iqModel.getClass().getSuperclass().getDeclaredFields());
        // 如果有Request_handler 添加进去
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("REQUEST_HANDLER")) {
                solrQuery.setRequestHandler(field.getName());
            }
        }
        solrQuery = buildQuery(iqModel, solrQuery, fields);
        return solrQuery;
    }

    public static SolrQuery buildQuery(IQModel iqModel, SolrQuery solrQuery, Field[] fields) {
        for (Field field : fields) {
            //首字母大写
            String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                //根据方法名获取get方法值
//                Method method = iqModel.getClass().getDeclaredMethod("get"+name);


                Method method = getMethod(iqModel, field);
                Object object = method.invoke(iqModel);

                if (name.equalsIgnoreCase("REQUEST_HANDLER")) {
                    solrQuery.setRequestHandler(String.valueOf(object));
                    continue;
                }

                if (object != null) {
                    if (field.getName().equalsIgnoreCase("q")) {
                        solrQuery.setQuery((String) object);
                    } else {
                        String obj = (String) object;
                        //无论前端传进来的是什么样的字符串，在controller都要解析为“***，***，***”类型
                        String[] params = obj.split(",");
                        //由于solr的查询参数是***.***类型，无法直接以属性名写在属性中，所以以“_”代替
                        String paramName = field.getName().replaceAll("_", ".");

                        //设定查询种类
                        // todo 不手动写，自动判定查询种类
                        for (String queryType : Constants.OPERATIONS) {
                            //由于仅传入一种QueryModel,所以每次只需要命中一次即可停止判定查询种类
                            if (paramName.startsWith(queryType)) {
                                solrQuery.set(queryType, true);
                                break;
                            }
                        }
                        solrQuery.set(paramName, params);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return solrQuery;
    }

    /**
     * 自定义query操作留存方法
     *
     * @param iqModel
     * @param solrQuery
     * @param customQueryType
     * @return
     */
    public static SolrQuery buildCustomAdvancedSolrQuery(IQModel iqModel, SolrQuery solrQuery, String[] customQueryType) {
//        Field[] fields = iqModel.getClass().getDeclaredFields();
        Field[] fields = ArrayUtils.addAll(iqModel.getClass().getDeclaredFields(), iqModel.getClass().getSuperclass().getDeclaredFields());
        // 如果有Request_handler 添加进去
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("REQUEST_HANDLER")) {
                solrQuery.setRequestHandler(field.getName());
                break;
            }
        }
        for (Field field : fields) {
            //首字母大写
            String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                //根据方法名获取get方法值
                Method method = iqModel.getClass().getDeclaredMethod("get" + name);
                Object object = method.invoke(iqModel);

                if (object != null) {
                    if (field.getName().equalsIgnoreCase("q")) {
                        solrQuery.setQuery((String) object);
                    } else {
                        String obj = (String) object;
                        //无论前端传进来的是什么样的字符串，在controller都要解析为“***，***，***”类型
                        String[] params = obj.split(",");
                        //由于solr的查询参数是***.***类型，无法直接以属性名写在属性中，所以以“_”代替
                        String paramName = field.getName().replace("_", ".");

                        //设定查询种类
                        // todo 不手动写，自动判定查询种类
                        for (String queryType : customQueryType) {
                            //由于仅传入一种QueryModel,所以每次只需要命中一次即可停止判定查询种类
                            if (paramName.startsWith(queryType)) {
                                solrQuery.set(queryType, true);
                                break;
                            }
                        }


//                        if(paramName.startsWith(FacetParams.FACET)){
//                            solrQuery.setFacet(true);
//                        }
//                        if (paramName.startsWith(MoreLikeThisParams.MLT)) {
//                            solrQuery.setMoreLikeThis(true);
//                        }
//                        if (paramName.startsWith(HighlightParams.HIGHLIGHT)) {
//                            solrQuery.setHighlight(true);
//                        }
//                        if(paramName.startsWith(TermsParams.TERMS)) {
//                            solrQuery.setTerms(true);
//                        }

                        solrQuery.add(paramName, params);
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return solrQuery;
    }

    public static Method getMethod(IQModel iqModel, Field field) {
        Field[] fields = iqModel.getClass().getDeclaredFields();
        Method method = null;
        boolean flag = false;
        for (Field field1 : fields) {
            if (field1.equals(field)) {
                String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                try {
                    method = iqModel.getClass().getDeclaredMethod("get" + name);
                    flag = true;
                    break;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!flag) {
            Class clazz = iqModel.getClass();
            while (!clazz.getTypeName().equalsIgnoreCase("java.lang.Object")) {
                clazz = clazz.getSuperclass();
                Field[] fields1 = clazz.getDeclaredFields();
                for (Field field1 : fields1) {
                    if (field1.equals(field)) {
                        String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                        try {
                            method = clazz.getDeclaredMethod("get" + name);
                            flag = true;
                            break;
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return method;
    }
}
