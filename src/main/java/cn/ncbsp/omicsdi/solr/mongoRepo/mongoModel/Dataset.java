package cn.ncbsp.omicsdi.solr.mongoRepo.mongoModel;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 04/05/2016
 */

@Document(collection = "datasets.dataset")
@CompoundIndexes({
        @CompoundIndex(name = "accession_database", def = "{'accession' : 1, 'database': 1}", unique = true)
})
public class Dataset implements Serializable, IDataset {

    int initHashCode;
    @Id
    ObjectId _id;
    // Name
    String name;
    // Description
    String description;
    @Indexed
    private String accession;
    @Indexed
    private String database;
    private Map<String, Set<String>> dates;

    // Additional fields
    private Map<String, Set<String>> additional;
    //Cross References
    private Map<String, Set<String>> crossReferences;

    private Map<String, Set<String>> files = new HashMap<>();

    private Map<String, String> configurations = new HashMap<>();

    private String filePath;

    private String currentStatus;

    private Scores scores;
    private boolean isClaimable;

    public Dataset() {
    }


    public Dataset(String accession, String database) {
        this.accession = accession;
        this.database = database;
        this.currentStatus = DatasetCategory.INSERTED.getType();
    }

    /**
     * This Constructor generate an Id for the entity using the MongoDB driver
     *
     * @param accession The access of the experiment in the repository
     * @param database  The id of the repository
     */
    public Dataset(String accession, String database, DatasetCategory category) {
        this(accession, database);
        this.currentStatus = category.getType();
        this.initHashCode = hashCode();
    }

    public Dataset(ObjectId resourceUUID, String accession, String database, DatasetCategory category) {
        this(accession, database);
        this._id = resourceUUID;
        this.currentStatus = category.getType();
        this.initHashCode = hashCode();
    }

    public Dataset(String accession, String database, String name, String description, Map<String, Set<String>> dates,
                   Map<String, Set<String>> additional, Map<String, Set<String>> crossReferences, DatasetCategory category) {
        this(accession, database);
        this.name = name;
        this.description = description;
        this.dates = dates;
        this.additional = additional;
        this.crossReferences = crossReferences;
        this.currentStatus = category.getType();
        this.initHashCode = initHashCode();
    }

    public Scores getScores() {
        return scores;
    }

    public void setScores(Scores scores) {
        this.scores = scores;
    }

    public boolean isClaimable() {
        return isClaimable;
    }

    public void setClaimable(boolean claimable) {
        isClaimable = claimable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Set<String>> getDates() {
        return dates;
    }

    public void setDates(Map<String, Set<String>> dates) {
        this.dates = dates;
    }

    public Map<String, Set<String>> getAdditional() {
        return additional;
    }

    public void setAdditional(Map<String, Set<String>> additional) {
        this.additional = additional;
    }

    public Map<String, Set<String>> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(Map<String, Set<String>> crossReferences) {
        this.crossReferences = crossReferences;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getInitHashCode() {
        return initHashCode;
    }

    public void setInitHashCode(int initHashCode) {
        this.initHashCode = initHashCode;
    }

    public void addAdditional(String key, Set<String> values) {
        if (additional == null)
            additional = new HashMap<>();
        additional.put(key, values);
    }

    public void addCrossReferences(String key, Set<String> values) {
        if (crossReferences == null)
            crossReferences = new HashMap<>();
        crossReferences.put(key, values);
    }

    public Map<String, Set<String>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Set<String>> files) {
        this.files = files;
    }

    public Map<String, String> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Map<String, String> configurations) {
        this.configurations = configurations;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        hashCode = (accession != null) ? 31 * hashCode + accession.hashCode() : hashCode;
        hashCode = (database != null) ? 31 * hashCode + database.hashCode() : hashCode;
        hashCode = (name != null) ? 31 * hashCode + name.hashCode() : hashCode;
        hashCode = (description != null) ? 31 * hashCode + description.hashCode() : hashCode;
        hashCode = (dates != null) ? 31 * hashCode + dates.hashCode() : hashCode;
        hashCode = (additional != null) ? 31 * hashCode + additional.hashCode() : hashCode;
        hashCode = (crossReferences != null) ? 31 * hashCode + crossReferences.hashCode() : hashCode;

        return hashCode;
    }

    public int initHashCode() {
        int hashCode = 31;
        hashCode = (accession != null) ? 31 * hashCode + accession.hashCode() : hashCode;
        hashCode = (database != null) ? 31 * hashCode + database.hashCode() : hashCode;
        hashCode = (name != null) ? 31 * hashCode + name.hashCode() : hashCode;
        hashCode = (description != null) ? 31 * hashCode + description.hashCode() : hashCode;
        hashCode = (dates != null) ? 31 * hashCode + dates.hashCode() : hashCode;
        hashCode = (additional != null) ? 31 * hashCode + additional.hashCode() : hashCode;
        hashCode = (crossReferences != null) ? 31 * hashCode + crossReferences.hashCode() : hashCode;
        return hashCode;
    }

    @Override
    public String toString() {
        return "INSERTED{" +
                "accession='" + accession + '\'' +
                ", database='" + database + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dates=" + dates +
                ", additional=" + additional +
                ", crossReferences=" + crossReferences +
                '}';
    }
}
