package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.service.security.Feature;

import java.util.List;

import static com.firefox5.digibooky.service.security.Feature.*;
import static com.google.common.collect.Lists.newArrayList;

public enum Role {
    ADMIN(newArrayList(
            GET_ALL_USERS,
            REGISTER_A_NEW_ADMIN,
            REGISTER_LIBRARIAN,
            REGISTER_MEMBER
    )),
    LIBRARIAN(newArrayList(
            CHECK_LENT_BOOKS,
            DELETE_A_BOOK,
            ISBN_VALIDATION,
            OVER_DUE_FINES,
            OVERDUE_BOOKS,
            REGISTER_A_NEW_BOOK,
            UPDATE_A_BOOK
    )),
    MEMBER(newArrayList(
            ENHANCED_BOOK_DETAILS,
            LEND_A_BOOK,
            RETURN_A_BOOK
    ));
    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
