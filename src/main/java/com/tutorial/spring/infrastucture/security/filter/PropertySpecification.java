package com.tutorial.spring.infrastucture.security.filter;

import com.tutorial.spring.domain.property.entity.Property;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PropertySpecification implements Specification<Property> {

    private SearchCriteria criteria;

    public PropertySpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<Property> root, CriteriaQuery<?> query,
        CriteriaBuilder builder) {
        if (criteria.getOperation() == SearchOperation.GREATER_THAN) {
            return builder.greaterThanOrEqualTo(
                root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation() == SearchOperation.LESS_THAN) {
            return builder.lessThanOrEqualTo(
                root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation() == SearchOperation.EQUALITY) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                    root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
