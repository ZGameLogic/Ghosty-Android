package com.zgamelogic.ghosty;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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