package stationery.store.bundle.offer;


import stationery.store.bundle.abstractAndInterfaces.AbstractService;

import java.util.Set;

public interface OfferService extends AbstractService<Offer, Long> {

    Set<Offer> findAll(int page, int size);

}
