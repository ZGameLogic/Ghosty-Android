package com.zgamelogic.ghosty;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import com.zgamelogic.ghosty.data.Ghost;

import java.util.LinkedList;

public class GhostUnitTest {

    private Ghost ben;

    @Before
    public void beforeEach(){
        LinkedList<String> evidence = new LinkedList<>();
        evidence.add("Fingerprints");
        evidence.add("Ghost orbs");
        evidence.add("EMF Level 5");
        ben = new Ghost("Ben", "A fine spooky ghost", 1, evidence);
    }

    @After
    public void afterEach(){
        ben = null;
    }

    @Test(expected = JSONException.class)
    public void jsonConstructorNotWorking() throws JSONException {
        JSONObject jsonGhost = new JSONObject("{\"Seconds to kill\", 15}");
        Ghost ghost = new Ghost(jsonGhost);
    }

    @Test
    public void jsonConstructorWorks() throws JSONException {
        JSONObject jsonGhost = new JSONObject("{\"evidence\": [" +
                "\"Fingerprints\"," +
                "\"Freezing Temperatures\"," +
                "\"Ghost Writing\"" +
                "]," +
                "\"name\": \"Spooky ghost\"," +
                "\"description\": \"This is a description\"," +
                "\"id\": 4" +
                "}");
        try {
            Ghost ghost = new Ghost(jsonGhost);
            assertEquals(ghost.getName(), "Spooky ghost");
            assertEquals(ghost.getDescription(), "This is a description");
            assertEquals(ghost.getId(), 4);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void ghostIsValidTrue(){
        LinkedList<String> foundEvidence = new LinkedList<>();
        foundEvidence.add("Fingerprints");
        assertTrue(ben.isValid(foundEvidence));
    }

    @Test
    public void ghostIsValidFalse(){
        LinkedList<String> foundEvidence = new LinkedList<>();
        foundEvidence.add("Ghost Writing");
        assertFalse(ben.isValid(foundEvidence));
    }

    @Test
    public void remainingEvidence(){
        LinkedList<String> foundEvidence = new LinkedList<>();
        foundEvidence.add("Fingerprints");
        assertArrayEquals(new String[]{"Ghost orbs", "EMF Level 5"}, ben.remainingEvidence(foundEvidence).toArray());
    }
}