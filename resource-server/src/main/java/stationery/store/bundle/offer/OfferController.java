package stationery.store.bundle.offer;


import org.springframework.web.bind.annotation.*;
import stationery.store.exceptions.EmailExistsException;

import java.util.Collection;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/offer")
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public Collection<Offer> getOffers() {
        return offerService.findAll();
    }

    @GetMapping(value = "/paged")
    public Set<Offer> getOffers(@RequestParam(value = "page", required = false) int page,
                                @RequestParam(value = "size", required = false) int size) {
        return offerService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public Offer findByID(@PathVariable Long id) {
        return offerService.findById(id);
    }

    @PostMapping({"", "/save"})
    public Offer save(@RequestBody Offer offer) throws EmailExistsException {
        return offerService.save(offer);
    }

    @PatchMapping({"", "/update"})
    public Offer update(@RequestBody Offer offer) throws EmailExistsException {
        return offerService.save(offer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        offerService.deleteById(id);
    }

}
