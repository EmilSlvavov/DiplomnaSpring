package com.tutorial.spring.infrastucture.security.filter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class PropertySpecificationsBuilder {
    private final List<SpecSearchCriteria> params;

    public PropertySpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final PropertySpecificationsBuilder with(String key, String operation, Object value,
        String prefix, String suffix) {
        return with(false, key, operation, value, prefix, suffix);
    }

    public final PropertySpecificationsBuilder with(Boolean orPredicate, String key, String operation,
        Object value, String prefix, String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                boolean startWithAsterisk = prefix != null &&
                prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                boolean endWithAsterisk = suffix != null &&
                    suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria( key, op, value,orPredicate));
        }
        return this;
    }

    public Specification build() {
        if (params.size() == 0)
            return null;

        Specification result = new PropertySpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                ? Specification.where(result).or(new PropertySpecification(params.get(i)))
                : Specification.where(result).and(new PropertySpecification(params.get(i)));
        }

        return result;
    }
}
