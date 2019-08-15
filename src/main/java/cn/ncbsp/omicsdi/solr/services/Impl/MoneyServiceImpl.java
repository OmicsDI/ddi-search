package cn.ncbsp.omicsdi.solr.services.Impl;

import cn.ncbsp.omicsdi.solr.model.Money;
import cn.ncbsp.omicsdi.solr.repo.MoneyRepo;
import cn.ncbsp.omicsdi.solr.services.IMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyServiceImpl implements IMoneyService {
    @Autowired
    MoneyRepo moneyRepo;

    @Override
    public List<Money> getMoneyByName(String name) {
        return moneyRepo.findByName(name);
    }
}
