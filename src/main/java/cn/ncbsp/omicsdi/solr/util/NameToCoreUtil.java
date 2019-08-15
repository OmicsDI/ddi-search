package cn.ncbsp.omicsdi.solr.util;

public class NameToCoreUtil {

    public static String getCore(String name) {

        String core = null;
        switch (name) {
            case "Pride":
                core = "pride";
                break;
            case "ArrayExpress":
                core = "arrayexpress-repository";
                break;
            case "EGA":
                core = "ega";
                break;
            case "MetaboLights":
                core = "metabolights_dataset";
                break;
            case "PeptideAtlas":
                core = "peptide_atlas";
                break;
            case "GPMDB":
                core = "gpmdb";
                break;
            case "MetabolomeExpress":
                core = "metabolome_express";
                break;
            case "GNPS":
                core = "gnps";
                break;
            case "BioModels Database":
                core = "biomodels";
                break;
            case "LINCS":
                core = "lincs";
                break;
            case "PAXDB":
                core = "paxdb";
                break;
            case "JPOST Repository":
                core = "jpost";
                break;
            case "ExpressionAtlas":
                core = "atlas-experiments";
                break;
            case "MetabolomicsWorkbench":
                core = "metabolomics_workbench";
                break;
            case "Massive":
                core = "massive";
                break;
            default:
                core = "unknown";

        }
        return core;
    }
}
