package com.company.session;

import com.company.enitities.PollEntity;
import com.company.enitities.VoterEntity;

import java.util.HashMap;

public class UserSessionStorage {

        private static final HashMap<String, VoterEntity> userSession =
                new HashMap<String, VoterEntity>();

        public static void setSession(String sessionId,  VoterEntity voter ) {
            //HashMap<String, Integer> test = userSession.get();
            userSession.put(sessionId, voter);
        }

        public static VoterEntity getUser(String sessionId) {
//            HashMap<String, Integer> test = userSession.get();
            return userSession.get(sessionId);
        }

        public static void deleteSession(String sessionId) {
            userSession.remove(sessionId);
        }

}
