package com.company.session;

import com.company.enitities.UserEntity;

import java.util.HashMap;

public class UserSessionStorage {

        private static final HashMap<String, UserEntity> userSession =
                new HashMap<String, UserEntity>();

        public static void setSession(String sessionId,  UserEntity voter ) {
            //HashMap<String, Integer> test = userSession.get();
            userSession.put(sessionId, voter);
        }

        public static UserEntity getUser(String sessionId) {
//            HashMap<String, Integer> test = userSession.get();
            return userSession.get(sessionId);
        }

        public static void deleteSession(String sessionId) {
            userSession.remove(sessionId);
        }

}
