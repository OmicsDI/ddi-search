package uk.ac.ebi.ddi.maven.ddisearchboot.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import uk.ac.ebi.ddi.maven.ddisearchboot.services.solr.ISolrEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Xpon
 */

@Configuration
@Component
@EnableScheduling
public class ImportJob{

    private static final Logger logger = LoggerFactory.getLogger(ImportJob.class);

    @Autowired
    ISolrEntryService iSolrEntryService;

    @Value("${ddi.scheduler.quartz.input.path}")
    private String folderPath;


    @Value("${ddi.scheduler.quartz.backup.path}")
    private String backupPath;

    public ImportJob() {
    }

    public void indexData() {
        iSolrEntryService.saveSolrEntries(folderPath, backupPath);
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }


}
