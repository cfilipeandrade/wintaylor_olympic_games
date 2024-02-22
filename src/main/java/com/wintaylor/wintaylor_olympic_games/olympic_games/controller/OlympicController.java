package com.wintaylor.wintaylor_olympic_games.olympic_games.controller;

import com.wintaylor.wintaylor_olympic_games.olympic_games.business.OlympicService;
import com.wintaylor.wintaylor_olympic_games.olympic_games.entities.Athlete;
import com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OlympicController {

    private final OlympicService olympicService;

    @Autowired
    public OlympicController(OlympicService olympicService) {
        this.olympicService = olympicService;
    }

    // Exercício 1
    @GetMapping("/load-data")
    public String loadDataFromURL() {
        olympicService.loadDataFromURL();
        return "Data loaded successfully.";
    }

    // Exercício 2
    @GetMapping("/sports")
    public ResponseEntity<List<String>> getSportsByCountryCode(@RequestParam String countryCode) {
        List<String> sports = olympicService.getSportsByCountry(countryCode);
        if (sports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sports, HttpStatus.OK);
    }

    // Exercício 3
    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDTO> getStatisticsByCountryAndSport(
            @RequestParam String countryCode,
            @RequestParam String sport
    ) {
        StatisticsDTO statistics = olympicService.getStatisticsByCountryAndSport(countryCode, sport);
        if (statistics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    // Exercício 4
    @GetMapping("/age-media-by-continent")
    public ResponseEntity<List<StatisticsContinentDTO>> getAgeMediaByContinent(
            @RequestParam String sport,
            @RequestParam double ageThreshold
    ) {
        List<StatisticsContinentDTO> result = olympicService.getAgeMediaByContinent(sport, ageThreshold);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Exercício 5
    @GetMapping("/max-bmi-country")
    public ResponseEntity<MaxBMICountryDTO> getMaxBMIAverageByContinentAndGender(
            @RequestParam String continent, @RequestParam String gender) {
        MaxBMICountryDTO maxBMIAverageDTO = olympicService.getMaxBMIAverageByContinentAndGender(continent, gender);
        if (maxBMIAverageDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(maxBMIAverageDTO, HttpStatus.OK);
    }

    // Exercício 6
    @PostMapping("/add-athlete-to-brazil-olympic-football-team")
    public ResponseEntity<String> addAthleteToBrazilOlympicFootballTeam(@RequestBody AthleteBrazilFootballDTO athleteDTO) {
        if (athleteDTO.getCountry().equalsIgnoreCase("BRA")
                && athleteDTO.getSport().equalsIgnoreCase("football")
                && athleteDTO.getGender().equalsIgnoreCase("male")) {
            // Adiciona o atleta à seleção de futebol olímpica masculina do Brasil
            boolean success = olympicService.addAthleteToBrazilOlympicFootballTeam(athleteDTO);
            if (success) {
                return ResponseEntity.ok("Atleta foi adicionado com sucesso à seleção olímpica de futebol do Brasil.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao adicionar atleta à equipe.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O atleta não atende aos critérios para integrar a seleção brasileira de futebol olímpico.");
        }
    }

    // Exercício 7
    @GetMapping("/brazil-football-team")
    public List<Athlete> getBrazilOlympicFootballTeam() {
        return olympicService.getBrazilOlympicFootballTeam();
    }

    // Exercício 8
    @PutMapping("/edit-athlete/{athleteId}")
    public ResponseEntity<String> editAthleteInformation(@PathVariable String athleteId, @RequestBody AthleteEditDTO athleteDTO) {
        boolean success = olympicService.editAthleteInformation(athleteId, athleteDTO);
        if (success) {
            return ResponseEntity.ok("Informações do atleta atualizadas com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atleta não encontrado.");
        }
    }

    // Exercício 9
    @GetMapping("/preselect-paris-olympics-female-athletes")
    public ResponseEntity<List<Athlete>> preSelectParisOlympicsFemaleAthletes() {
        List<Athlete> preSelectedAthletes = olympicService.preSelectParisOlympicsFemale();
        if (preSelectedAthletes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        olympicService.preSelectParisOlympicsFemaleAthletes();
        return new ResponseEntity<>(preSelectedAthletes, HttpStatus.OK);
    }

    // Exercício 10
    @PostMapping("/add-athlete-to-brazil-olympic-athletics-team")
    public ResponseEntity<List<Athlete>> addAthleteToBrazilOlympicAthleticsTeam() {
        olympicService.addAthleteToBrazilOlympicAthleticsTeam();
        List<Athlete> preSelectedAthletes = olympicService.preSelectParisOlympicsFemale();
        olympicService.preSelectParisOlympicsFemaleAthletes();
        return new ResponseEntity<>(preSelectedAthletes, HttpStatus.OK);
    }


}
