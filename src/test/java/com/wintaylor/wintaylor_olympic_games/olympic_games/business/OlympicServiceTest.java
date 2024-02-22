package com.wintaylor.wintaylor_olympic_games.olympic_games.business;

import com.wintaylor.wintaylor_olympic_games.olympic_games.entities.Athlete;
import com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos.AthleteBrazilFootballDTO;
import com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos.AthleteEditDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OlympicServiceTest {

    @Mock
    private Map<String, Athlete> athletesMap;

    @InjectMocks
    private OlympicService olympicService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAthleteToBrazilOlympicFootballTeam_Success() {
        AthleteBrazilFootballDTO athleteDTO = new AthleteBrazilFootballDTO();
        athleteDTO.setCountry("BRA");
        athleteDTO.setSport("football");
        athleteDTO.setGender("male");

        when(athletesMap.size()).thenReturn(0);

        assertTrue(olympicService.addAthleteToBrazilOlympicFootballTeam(athleteDTO));
    }

    @Test
    public void testAddAthleteToBrazilOlympicFootballTeam_Fail() {
        AthleteBrazilFootballDTO athleteDTO = new AthleteBrazilFootballDTO();
        athleteDTO.setCountry("USA");
        athleteDTO.setSport("football");
        athleteDTO.setGender("male");

        assertFalse(olympicService.addAthleteToBrazilOlympicFootballTeam(athleteDTO));
    }

    @Test
    public void testEditAthleteInformation_Success() {
        AthleteEditDTO athleteEditDTO = new AthleteEditDTO();
        athleteEditDTO.setContinent("South America");
        athleteEditDTO.setCountry("BRA");
        athleteEditDTO.setSport("football");
        athleteEditDTO.setGender("male");
        athleteEditDTO.setHeight(180.0);
        athleteEditDTO.setWeight(75.0);
        athleteEditDTO.setBMI(athleteEditDTO.getWeight() / (athleteEditDTO.getHeight() * athleteEditDTO.getHeight()));
        athleteEditDTO.setAge(25.0);

        Athlete athlete = new Athlete();
        athlete.setContinent("South America");
        athlete.setCountry("BRA");
        athlete.setSport("football");
        athlete.setGender("male");
        athlete.setHeight(180.0);
        athlete.setWeight(75.0);
        athlete.setBMI(athleteEditDTO.getBMI());
        athlete.setAge(25.0);

        when(athletesMap.containsKey("1")).thenReturn(true);
        when(athletesMap.get("1")).thenReturn(athlete);

        assertTrue(olympicService.editAthleteInformation("1", athleteEditDTO));

        assertEquals(athleteEditDTO.getContinent(), athlete.getContinent());
        assertEquals(athleteEditDTO.getCountry(), athlete.getCountry());
        assertEquals(athleteEditDTO.getSport(), athlete.getSport());
        assertEquals(athleteEditDTO.getGender(), athlete.getGender());
        assertEquals(athleteEditDTO.getHeight(), athlete.getHeight());
        assertEquals(athleteEditDTO.getWeight(), athlete.getWeight());
        assertEquals(athleteEditDTO.getBMI(), athlete.getBMI());
        assertEquals(athleteEditDTO.getAge(), athlete.getAge());
    }

    @Test
    public void testEditAthleteInformation_Fail() {
        AthleteEditDTO athleteEditDTO = new AthleteEditDTO();

        assertFalse(olympicService.editAthleteInformation("1", athleteEditDTO));
    }
}
