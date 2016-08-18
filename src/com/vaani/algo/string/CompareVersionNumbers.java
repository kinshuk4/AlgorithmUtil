package com.vaani.algo.string;

/**
 * Compare two version numbers version1 and version1.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * <p>
 * Here is an example of version numbers ordering:
 * <p>
 * 0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int sectionV1 = Integer.parseInt(v1[0]);
        int sectionV2 = Integer.parseInt(v2[0]);
        int section = 1;

        if (sectionV1 < sectionV2) {
            return -1;
        } else if (sectionV1 > sectionV2) {
            return 1;
        } else {
            while (sectionV1 == sectionV2) {
                sectionV1 = v1.length > section ? Integer.parseInt(v1[section]) : 0;
                sectionV2 = v2.length > section ? Integer.parseInt(v2[section]) : 0;
                section++;
                if (sectionV1 < sectionV2) {
                    return -1;
                } else if (sectionV1 > sectionV2) {
                    return 1;
                } else {
                    if (v1.length <= section && v2.length <= section) return 0;
                }
            }
            return 0;
        }
    }
}
