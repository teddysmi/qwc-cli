package org.qwc.cli.tool.service;

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

    @Override
    public void clearSI() {
        // clear sis before adding
        siRepository.deleteAll();
    }

    @Transactional
    @Override
    public SI createSI(SI si) {


        SIEntity entity = new SIEntity();
        si.loadIntoEntity(entity);

        siRepository.saveAndFlush(entity);

        SI newSI = new SI();
        newSI.loadFromEntity(entity);

        return newSI;
    }

    @Override
    public boolean findByID(Long id) {

        Optional<SIEntity> siEntity = siRepository.findById(id);

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
