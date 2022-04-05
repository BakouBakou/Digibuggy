package com.switchfully.digibuggy.security;

import java.util.List;
import static com.switchfully.digibuggy.security.Feature.REGISTER_LIBRARIAN;

public enum Role {
    ADMIN(List.of(REGISTER_LIBRARIAN));

    private final List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
