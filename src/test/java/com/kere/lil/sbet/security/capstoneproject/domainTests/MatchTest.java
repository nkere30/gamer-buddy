package com.kere.lil.sbet.security.capstoneproject.domainTests;

import com.kere.lil.sbet.security.capstoneproject.domain.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testMatchProperties() {
        Match match = new Match();
        match.setUser1Id(1L);
        match.setUser2Id(2L);

        assertEquals(1L, match.getUser1Id());
        assertEquals(2L, match.getUser2Id());
    }
}
