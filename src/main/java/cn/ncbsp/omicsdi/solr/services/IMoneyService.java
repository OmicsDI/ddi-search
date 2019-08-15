package cn.ncbsp.omicsdi.solr.services;

import cn.ncbsp.omicsdi.solr.model.Money;

import java.util.List;

public interface IMoneyService {
    public List<Money> getMoneyByName(String name);
}
