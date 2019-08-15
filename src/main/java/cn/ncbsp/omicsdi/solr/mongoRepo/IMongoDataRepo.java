package cn.ncbsp.omicsdi.solr.mongoRepo;

import cn.ncbsp.omicsdi.solr.model.Database;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;

public interface IMongoDataRepo {
    public Database getAllDatasets(Query query, String database);

    public Map<String, Integer> getAllDatabase();
}
