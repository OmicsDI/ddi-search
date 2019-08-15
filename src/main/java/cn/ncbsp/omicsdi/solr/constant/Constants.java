package cn.ncbsp.omicsdi.solr.constant;

import org.apache.solr.common.params.*;

public class Constants {

    // todo 增加其他的可被查询的文档字段
    // 暂时只有这些，以后可以增加（确定需要所有的字段）,时间字段,数字字段,人名字段，邮箱字段排除在外
    public static final String[] INDEXED_FIELD = {
            "id", "acc", "name", "description", "database", "additional_sample_protocol", "additional_quantification_method", "additional_omics_type",
            "additional_submitter_affiliation", "additional_species", "additional_submitter_keywords", "additional_software", "additional_cell_type",
            "additional_doi", "additional_technology_type", "additional_name_synonyms", "additional_data_protocol", "additional_instrument_platform",
            "additional_description_synonyms", "additional_disease", "additional_pubmed_title_synonyms", "additional_repository", "additional_pubmed_abstract",
            "additional_ensembl", "additional_uniprot", "additional_pubmed_title", "additional_pubmed_abstract_synonyms", "additional_tissue",
            "additional_modification", "additional_submission_type"
    };

    public static final String REQUEST_HANDLER_SUGGEST = "/suggest";

    public static final String REQUEST_HANDLER_MORELIKETHIS = "/mlt";

    public static final String[] OPERATIONS = {
            FacetParams.FACET, MoreLikeThisParams.MLT, TermsParams.TERMS, HighlightParams.HIGHLIGHT, StatsParams.STATS
    };

}
