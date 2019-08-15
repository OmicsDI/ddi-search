package cn.ncbsp.omicsdi.solr.mongoService.Impl;

import cn.ncbsp.omicsdi.solr.model.Database;
import cn.ncbsp.omicsdi.solr.mongoRepo.IMongoDataRepo;
import cn.ncbsp.omicsdi.solr.mongoService.IMongoService;
import cn.ncbsp.omicsdi.solr.util.XmlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MongoService implements IMongoService {

    @Autowired
    IMongoDataRepo mongoDataRepo;

    @Override
    public void MongoToSolrXml() {
        Map<String, Integer> map = mongoDataRepo.getAllDatabase();
        for (String database : map.keySet()) {
            Query query = new Query();
            Criteria criteria = Criteria.where("database").is(database);
            query.with(new Sort(Sort.Direction.DESC, "_id"));
            query.addCriteria(criteria);
            int totalNum = map.get(database);
            int startNum = 0;
            Database databaseDetail = null;
            //循環
            while (totalNum - (startNum + 50) > 0) {
                query.skip(startNum).limit(50);
                startNum += 50;
                databaseDetail = mongoDataRepo.getAllDatasets(query, database);
                XmlHelper.objectToXml(databaseDetail, "C:\\Users\\MS\\Desktop\\solr\\solrTest", database);
            }
//            if(databaseDetail == null) {
            //跳出循環后結算最後的數據
            databaseDetail = mongoDataRepo.getAllDatasets(query.skip(startNum), database);
            XmlHelper.objectToXml(databaseDetail, "C:\\Users\\MS\\Desktop\\solr\\solrTest", database);
//            }

        }


    }
}
