package com.company.session;

import java.util.HashMap;
import java.util.HashSet;

public class AdminSessionStorage {

        private static final HashSet<String> adminSession =
                new HashSet<String>();
        public static void setSession(String sessionId) {
            adminSession.add(sessionId);
        }

        public static boolean sessionIsActive(String sessionId) {
            return adminSession.contains(sessionId);
        }

        public static void deleteSession(String sessionId) {
            adminSession    .remove(sessionId);
        }

}
