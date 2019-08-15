package cn.ncbsp.omicsdi.solr.mongoRepo.Impl;

import cn.ncbsp.omicsdi.solr.model.*;
import cn.ncbsp.omicsdi.solr.mongoRepo.IMongoDataRepo;
import cn.ncbsp.omicsdi.solr.mongoRepo.mongoModel.DatabaseDetail;
import cn.ncbsp.omicsdi.solr.mongoRepo.mongoModel.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MongoDataRepoImpl implements IMongoDataRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Database getAllDatasets(Query query, String databaseName) {

        List<Dataset> list = mongoTemplate.find(query, Dataset.class);
        System.out.println("xx");
        List<cn.ncbsp.omicsdi.solr.model.Entry> entryList = list.stream().map(x -> {
            Entry entry = new Entry();
            Map<String, Set<String>> crossReferencesMap = x.getCrossReferences();
            entry.setId(x.getAccession());
            entry.setAcc(x.getAccession());
            entry.setName(x.getName());
            entry.setDescription(x.getDescription());
            Map<String, Set<String>> dateMaps = x.getDates();
            if(dateMaps != null) {
                List<cn.ncbsp.omicsdi.solr.model.Date> dateList = new ArrayList<>();
                for (String key : dateMaps.keySet()) {
                    for (String value : dateMaps.get(key)) {
                        cn.ncbsp.omicsdi.solr.model.Date date = new cn.ncbsp.omicsdi.solr.model.Date();
                        date.setType(key);
                        date.setValue(value);
                        dateList.add(date);
                    }
                }
                DatesType datesType = new DatesType();
                datesType.setDate(dateList);
                entry.setDates(datesType);
            }
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
            List<Field> additionalFields = new ArrayList<>();
            for (String key : additionalMap.keySet()) {
                for (String value : additionalMap.get(key)) {
                    Field field = new Field();
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

            Map<String, Set<String>> fileMaps = x.getFiles();
            if(fileMaps != null) {
                List<File> fileList = new ArrayList<>();
                for (String key : fileMaps.keySet()) {
                    for (String value : fileMaps.get(key)) {
                        File file = new File();
                        file.setValue(value);
                        file.setName(key);
                        fileList.add(file);
                    }
                }
                FilesType filesType = new FilesType();
                filesType.setFile(fileList);
                entry.setFiles(filesType);
            }


            return entry;
        }).collect(Collectors.toList());

        Database database = new Database();
        database.setEntries(entryList);
        database.setName(databaseName);
        database.setEntryCount(entryList.size());
        database.setReleaseDate(new Date().toString());
        return database;
    }

    @Override
    public Map<String, Integer> getAllDatabase() {

        List<DatabaseDetail> databaseDetailList = mongoTemplate.findAll(DatabaseDetail.class);
        List<String> databases = databaseDetailList.stream().map(DatabaseDetail::getRepository).collect(Collectors.toList());

        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (String database : databases) {
            long l = mongoTemplate.count(new Query(Criteria.where("database").is(database)), "datasets.dataset");
            map.put(database, (int) l);
        }
        return map;
    }
}
