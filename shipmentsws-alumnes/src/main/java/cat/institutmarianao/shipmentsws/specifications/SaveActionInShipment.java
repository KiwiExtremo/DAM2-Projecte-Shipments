package cat.institutmarianao.shipmentsws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SaveActionInShipment implements Specification<Shipment> {
	private static final long serialVersionUID = 1L;
	
	private Long shipmentId;
    private Action action;

    public SaveActionInShipment(Long shipmentId, Action action) {
        this.shipmentId = shipmentId;
        this.action = action;
    }

    @Override
    public Predicate toPredicate(Root<Shipment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate shipmentPredicate = criteriaBuilder.equal(root.get("id"), shipmentId);
        
        query.multiselect().distinct(true);
        root.fetch("tracking");
        Shipment shipment = (Shipment) query.from(Shipment.class);
        shipment.getTracking().add(action);
        
        return shipmentPredicate;
    }
}

