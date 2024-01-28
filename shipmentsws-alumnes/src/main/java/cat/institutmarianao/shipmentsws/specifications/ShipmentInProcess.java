package cat.institutmarianao.shipmentsws.specifications;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.Shipment.Category;
import cat.institutmarianao.shipmentsws.model.Shipment.Status;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ShipmentInProcess implements Specification<Shipment> {
    
    private static final long serialVersionUID = 1L;
    private Status status;
    private String receivedBy;
    private String courierAssigned;
    private Category category;
    private Date from;
    private Date to;

    public ShipmentInProcess(Status status, String receivedBy, String courierAssigned, Category category, Date from, Date to) {
        this.status = status;
        this.receivedBy = receivedBy;
        this.courierAssigned = courierAssigned;
        this.category = category;
        this.from = from;
        this.to = to;
    }

    @Override
    public Predicate toPredicate(Root<Shipment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // Add condition for status
        if (status != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }

        // Add condition for receivedBy
        if (!receivedBy.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("receivedBy"), receivedBy));
        }

        // Add condition for courierAssigned
        if (!courierAssigned.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("courierAssigned"), courierAssigned));
        }

        // Add condition for category
        if (category != null) {
            predicates.add(criteriaBuilder.equal(root.get("category"), category));
        }

        // Add condition for from date
        if (from != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), from));
        }

        // Add condition for to date
        if (to != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), to));
        }

        // Combine predicates using AND operator
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}