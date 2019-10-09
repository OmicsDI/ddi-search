package uk.ac.ebi.ddi.maven.ddisearchboot.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Objects;

/**
 * @author Xpon
 */
@Configuration
public class QuartzScheduler {

    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ImportJob task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false);
        jobDetail.setName("import_data_to_solr");
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("indexData");
        return jobDetail;
    }

    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(Objects.requireNonNull(jobDetail.getObject()));
        tigger.setCronExpression("0 20 15 * * ?");
        tigger.setName("import_data_to_solr");
        return tigger;

    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true);
        bean.setStartupDelay(1);
        bean.setTriggers(cronJobTrigger);
        return bean;
    }
}
