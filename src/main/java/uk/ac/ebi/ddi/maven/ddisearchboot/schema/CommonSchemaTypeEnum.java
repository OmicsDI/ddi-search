package uk.ac.ebi.ddi.maven.ddisearchboot.schema;

/**
 * @author Xpon
 */

public enum CommonSchemaTypeEnum {
    STRING("string"),
    STRINGS("strings"),
    TEXT_GENERAL("text_general"),
    TEXT_EN("text_en"),
    TEXT_SUGGEST("text_suggest"),
    LONG("plong"),
    FLOAT("pfloat"),
    DOUBLE("pdouble"),
    DATABASE_STRING("database_string"),
    TEXT_AUTO("text_auto");

    private String typeName;

    CommonSchemaTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
