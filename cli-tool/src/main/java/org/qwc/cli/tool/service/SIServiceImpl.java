package org.qwc.cli.tool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.qwc.cli.tool.dao.SIEntity;
import org.qwc.cli.tool.dao.SIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SIServiceImpl implements SIService {

    @Autowired
    private SIRepository siRepository;



    @Transactional
    @Override
    public void createSI(List<SI> siList) {

        // clear sis before adding
        siRepository.deleteAll();

        List<SIEntity> siEntityList = new ArrayList<>();

        for(SI si : siList) {
            SIEntity entity = new SIEntity();
            si.loadIntoEntity(entity);
            siEntityList.add(entity);
        }


        siRepository.saveAll(siEntityList);

    }

    @Override
    public boolean findByMsisdn(String msisdn) {

        Optional<SIEntity> siEntity = siRepository.findByMsisdn(msisdn);

        return siEntity.isPresent();
    }

    @Transactional
    @Override
    public SI getSI() {

        Optional<SIEntity> siOpt = siRepository.findAll().stream().findFirst();

        if (siOpt.isPresent()) {
            SI si = new SI();
            si.loadFromEntity(siOpt.get());

            return si;
        }

        return null;
    }

}
