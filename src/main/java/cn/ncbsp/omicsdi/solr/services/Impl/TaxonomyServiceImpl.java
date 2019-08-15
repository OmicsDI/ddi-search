package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.services.ITaxonomyService;
import cn.ncbsp.omicsdi.solr.solrmodel.NCBITaxonomy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TaxonomyServiceImpl implements ITaxonomyService {

    @Autowired
    SolrTemplate solrTemplate;

    @Override
    public void loadTaxonData() {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream("C:\\Users\\MS\\Desktop\\solr\\新建文件夹\\names.dmp"));


            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            int j = 0;
            int i = 0;
            List<NCBITaxonomy> list = new ArrayList<NCBITaxonomy>();
            while ((str = bufferedReader.readLine()) != null) {
                j++;
                i++;
                String[] params = str.split("\\|");
                NCBITaxonomy ncbiTaxonomy = new NCBITaxonomy();
                ncbiTaxonomy.setTaxId(params[0].trim());
                ncbiTaxonomy.setNameTxt(params[1].trim());
                ncbiTaxonomy.setUniqueName(params[2].trim());
                ncbiTaxonomy.setNameClass(params[3].trim());
                list.add(ncbiTaxonomy);
                if (i >= 1000) {
                    i = 0;
                    solrTemplate.saveBeans("taxonomy", list);
                    solrTemplate.commit("taxonomy");
                    list.clear();
                }
            }

            if (list.size() > 0) {
                solrTemplate.saveBeans("taxonomy", list);
                solrTemplate.commit("taxonomy");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
