package com.example.MatrimonyProject.utilits;

import com.example.MatrimonyProject.model.UserProfile;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class UserProfileSpecification {

    private UserProfileSpecification() {}

    public static Specification<UserProfile> withFilters(UserProfileSearchRequest req) {
        return (root, query, cb) -> {
            query.distinct(true);

            // Join child tables (avoid N+1)
            var relJoin = root.join("religionDetails", JoinType.LEFT);
            var perJoin = root.join("personalDetails", JoinType.LEFT);
            var proJoin = root.join("professionalDetails", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();

            // --- Age / Gender ---
            if (req.getMinAge() != null && req.getMaxAge() != null && req.getMinAge().equals(req.getMaxAge())) {
                // exact age match
                LocalDate startDob = LocalDate.now().minusYears(req.getMaxAge() + 1).plusDays(1);
                LocalDate endDob   = LocalDate.now().minusYears(req.getMaxAge());
                predicates.add(cb.between(root.get("dob"), startDob, endDob));
            } else {
                if (req.getMinAge() != null) {
                    LocalDate maxDob = LocalDate.now().minusYears(req.getMinAge());
                    predicates.add(cb.lessThanOrEqualTo(root.get("dob"), maxDob));
                }
                if (req.getMaxAge() != null) {
                    LocalDate minDob = LocalDate.now().minusYears(req.getMaxAge() + 1).plusDays(1);
                    predicates.add(cb.greaterThanOrEqualTo(root.get("dob"), minDob));
                }
            }

            if (notBlank(req.getGender())) {
                predicates.add(cb.equal(cb.lower(root.get("gender")), req.getGender().toLowerCase()));
            }

            // --- ReligionDetails ---
            if (notBlank(req.getReligion())) {
                predicates.add(ilike(cb, relJoin.get("religion"), req.getReligion()));
            }
            if (notBlank(req.getCaste())) {
                predicates.add(ilike(cb, relJoin.get("caste"), req.getCaste()));
            }
            if (notBlank(req.getSubCaste())) {
                predicates.add(ilike(cb, relJoin.get("subCaste"), req.getSubCaste()));
            }

            // --- PersonalDetails ---
            if (notBlank(req.getMaritalStatus())) {
                predicates.add(ilike(cb, perJoin.get("maritalStatus"), req.getMaritalStatus()));
            }
            if (notBlank(req.getFamilyStatus())) {
                List<String> statuses = Arrays.stream(req.getFamilyStatus().split(","))
                        .map(String::trim)
                        .map(String::toLowerCase)
                        .toList();

                predicates.add(cb.lower(perJoin.get("familyStatus")).in(statuses));
            }


            if (notBlank(req.getFamilyType())) {
                predicates.add(ilike(cb, perJoin.get("familyType"), req.getFamilyType()));
            }

            // ✅ Mother Tongue (join Language table by ID)
            if (req.getMotherTongueId() != null) {
                predicates.add(cb.equal(perJoin.get("motherTongue").get("id"), req.getMotherTongueId()));
            }

            // ✅ Languages Known (many-to-many join)
            if (req.getLanguagesKnownIds() != null && !req.getLanguagesKnownIds().isEmpty()) {
                var langJoin = perJoin.join("languagesKnown", JoinType.LEFT);
                predicates.add(langJoin.get("id").in(req.getLanguagesKnownIds()));
            }




            // --- ProfessionalDetails ---
            if (notBlank(req.getEducation())) {
                predicates.add(ilike(cb, proJoin.get("education"), req.getEducation()));
            }
            if (notBlank(req.getOccupation())) {
                predicates.add(ilike(cb, proJoin.get("occupation"), req.getOccupation()));
            }


            // Numeric comparisons based on request
            if (req.getAnnualIncomeMin() != null) {   // or req.getMinIncome()
                predicates.add(cb.greaterThanOrEqualTo(proJoin.get("annualIncome"), req.getAnnualIncomeMin()));
            }

            if (req.getAnnualIncomeMax() != null) {   // or req.getMaxIncome()
                predicates.add(cb.lessThanOrEqualTo(proJoin.get("annualIncome"), req.getAnnualIncomeMax()));
            }


            // --- Location ---
            if (notBlank(req.getCity())) {
                predicates.add(ilike(cb, proJoin.get("city"), req.getCity()));
            }
            if (notBlank(req.getState())) {
                predicates.add(ilike(cb, proJoin.get("state"), req.getState()));
            }
            if (notBlank(req.getCountry())) {
                predicates.add(ilike(cb, proJoin.get("country"), req.getCountry()));
            }

            // --- Free text search (multi-field OR) ---
            if (notBlank(req.getQ())) {
                var qLike = "%" + req.getQ().trim().toLowerCase() + "%";
                predicates.add(
                        cb.or(
                                cb.like(cb.lower(root.get("fullName")), qLike),
                                cb.like(cb.lower(proJoin.get("occupation")), qLike),
                                cb.like(cb.lower(proJoin.get("companyName")), qLike),
                                cb.like(cb.lower(proJoin.get("education")), qLike),
                                cb.like(cb.lower(relJoin.get("religion")), qLike),
                                cb.like(cb.lower(relJoin.get("caste")), qLike)
                        )
                );
            }

            // ✅ Return final combined predicate
            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }

    // --- Helpers ---
    private static boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private static jakarta.persistence.criteria.Predicate ilike(
            jakarta.persistence.criteria.CriteriaBuilder cb,
            jakarta.persistence.criteria.Expression<String> path,
            String value
    ) {
        return cb.like(cb.lower(path), "%" + value.trim().toLowerCase() + "%");
    }
}
