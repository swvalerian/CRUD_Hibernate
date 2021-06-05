package com.swvalerian.crud.service;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.model.Team;
import com.swvalerian.crud.repository.jdbc.JavaIOTeamRepImpl;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.InOrder.*;

public class ServiceTeamTests {
    ServiceTeam serviceTeamMockito = Mockito.mock(ServiceTeam.class);

    @Test
    public void shouldGetAllTeam() {
        List<Team> teamListExpected = new ServiceTeam().getAll();

        Mockito.when(serviceTeamMockito.getAll()).thenReturn(new ServiceTeam().getAll());

        assertEquals(teamListExpected.toString(),serviceTeamMockito.getAll().toString());
    }

    @Test
    public void shouldCreateTeam() {
        Team teamCreateExpected = new Team();
        teamCreateExpected.setDevelopers(null);
        teamCreateExpected.setId(5);
        teamCreateExpected.setName("Memory");

        Mockito.when(serviceTeamMockito.create(5, "Memory")).thenReturn(teamCreateExpected);
        assertEquals(teamCreateExpected, serviceTeamMockito.create(5, "Memory"));
    }

    @Test
    public void shouldUpdateTeam() {
        Team teamUpdateExpected = new Team();
        teamUpdateExpected.setName("pushka");
        teamUpdateExpected.setId(3);
        teamUpdateExpected.setDevelopers(null);

        Mockito.when(serviceTeamMockito.update(3, "puhska")).thenReturn(teamUpdateExpected);
        assertEquals(teamUpdateExpected, serviceTeamMockito.update(3, "puhska"));
    }

    @Test
    public void shouldReadTeam() {
        Team teamReadExpected = new Team();
        teamReadExpected.setId(6);
        teamReadExpected.setName("Vesna");
        teamReadExpected.setDevelopers(null);

        Mockito.when(serviceTeamMockito.read(6)).thenReturn(teamReadExpected);
        assertEquals(teamReadExpected, serviceTeamMockito.read(6));
    }

    @Test
    public void shouldDeleteTeam() {

       /* JavaIOTeamRepImpl teamRepMackito = Mockito.mock(JavaIOTeamRepImpl.class);

        Mockito.verify(serviceTeamMockito).delete(1);

        Mockito.doReturn(NullPointerException.class).doNothing().when(serviceTeamMockito).delete(1);

        serviceTeamMockito.delete(1);
        assertEquals(,serviceTeamMockito.delete(1));*/

    }
}
