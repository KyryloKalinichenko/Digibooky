package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.security.Feature;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    ADMIN(newArrayList()),
    LIBRARIAN(newArrayList()),
    MEMBER(newArrayList());
    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }
    public boolean containsFeature(Feature feature){
        return featureList.contains(feature);
    }
}
