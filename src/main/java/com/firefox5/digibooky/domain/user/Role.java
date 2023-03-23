package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.service.security.Feature;

import java.util.List;

import static com.firefox5.digibooky.service.security.Feature.GET_ALL_USERS;
import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    ADMIN(newArrayList()),
    LIBRARIAN(newArrayList()),
    MEMBER(newArrayList(GET_ALL_USERS));
    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }
    public boolean containsFeature(Feature feature){
        return featureList.contains(feature);
    }
}
