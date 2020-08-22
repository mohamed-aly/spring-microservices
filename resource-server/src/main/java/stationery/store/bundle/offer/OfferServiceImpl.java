package stationery.store.bundle.offer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stationery.store.utilities.Utils;

import java.util.Collection;
import java.util.Set;


@Slf4j
@Service
public class OfferServiceImpl implements OfferService {

    private OfferDAO offerDAO;

    public OfferServiceImpl(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    @Override
    @Transactional
    public Collection<Offer> findAll() {
        return offerDAO.findAll();
    }

    @Override
    @Transactional
    public Set<Offer> findAll(int page, int size) {
        Pageable pageable = Utils.pageable(page, size);
        return offerDAO.findAll(pageable).toSet();
    }

    @Override
    @Transactional
    public Offer findById(Long aLong) {
        return offerDAO.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    public Offer save(Offer object) {
        if(object.getDiscount()<=0||object.getDiscount()>=1){
            throw new RuntimeException("Discount should be greater than 0");
        }
        return offerDAO.save(object);
    }

    @Override
    @Transactional
    public void delete(Offer object) {
        offerDAO.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {
        offerDAO.deleteById(aLong);
    }

}





