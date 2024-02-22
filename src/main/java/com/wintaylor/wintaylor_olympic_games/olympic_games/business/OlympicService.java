package com.wintaylor.wintaylor_olympic_games.olympic_games.business;

import com.wintaylor.wintaylor_olympic_games.olympic_games.entities.Athlete;

import java.util.*;
import java.util.stream.Collectors;

import com.wintaylor.wintaylor_olympic_games.olympic_games.entities.dtos.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class OlympicService {

    private Map<String, Athlete> athletesMap;

    private Map<String, Athlete> athletesPreSelectParisOlympicsFemale;

    @PostConstruct
    public void init() {
        loadDataFromURL();
        preSelectParisOlympicsFemaleAthletes();
    }

    // Exercício 1
    public void loadDataFromURL() {
        String url = "https://cdn.jsdelivr.net/gh/highcharts/highcharts@24912efc85/samples/data/olympic2012.json";
        RestTemplate restTemplate = new RestTemplate();
        Athlete[] athletes = restTemplate.getForObject(url, Athlete[].class);
        storeData(athletes);
    }

    // Exercício 2
    public List<String> getSportsByCountry(String country) {
        if (athletesMap == null || athletesMap.isEmpty()) {
            throw new IllegalStateException("Athletes map is null or empty.");
        }

        List<String> sportsByCountry = new ArrayList<>();
        for (Athlete athlete : athletesMap.values()) {
            if (athlete.getCountry().equalsIgnoreCase(country)) {
                sportsByCountry.add(athlete.getSport());
            }
        }

        if (sportsByCountry.isEmpty()) {
            throw new IllegalArgumentException("Nenhum atleta encontrado para o país especificado.");
        }

        return sportsByCountry;
    }

    // Exercício 3
    public StatisticsDTO getStatisticsByCountryAndSport(String countryCode, String sport) {
        if (athletesMap == null || athletesMap.isEmpty()) {
            throw new IllegalStateException("Athletes map is null or empty.");
        }

        List<Athlete> filteredAthletes = athletesMap.values().stream()
                .filter(athlete -> athlete.getCountry().equalsIgnoreCase(countryCode)
                        && athlete.getSport().equalsIgnoreCase(sport))
                .collect(Collectors.toList());
        int maleCount = 0;
        int femaleCount = 0;
        double totalAge = 0;

        for (Athlete athlete : filteredAthletes) {
            if (athlete.getGender().equalsIgnoreCase("male")) {
                maleCount++;
            } else if (athlete.getGender().equalsIgnoreCase("female")) {
                femaleCount++;
            }
            totalAge += athlete.getAge();
        }

        if (filteredAthletes.isEmpty()) {
            return null;
        }
        double averageAge = totalAge / filteredAthletes.size();

        return new StatisticsDTO(maleCount, femaleCount, averageAge);
    }

    // Exercício 4
    public List<StatisticsContinentDTO> getAgeMediaByContinent(String sport, double ageThreshold) {
        if (athletesMap == null || athletesMap.isEmpty()) {
            throw new IllegalStateException("Athletes map is null or empty.");
        }

        Map<String, Double> totalAgeByContinent = new HashMap<>();
        Map<String, Integer> countByContinent = new HashMap<>();
        for (Athlete athlete : athletesMap.values()) {
            if (athlete.getSport().equalsIgnoreCase(sport) && athlete.getAge() > ageThreshold) {
                totalAgeByContinent.merge(athlete.getContinent(), athlete.getAge(), Double::sum);
                countByContinent.merge(athlete.getContinent(), 1, Integer::sum);
            }
        }

        return totalAgeByContinent.entrySet().stream()
                .map(entry -> new StatisticsContinentDTO(entry.getKey(),
                        entry.getValue() / countByContinent.get(entry.getKey())))
                .collect(Collectors.toList());
    }

    // Exercício 5
    public MaxBMICountryDTO getMaxBMIAverageByContinentAndGender(String continent, String gender) {
        List<Athlete> athletes = getAthletesByContinentAndGender(continent, gender);

        if (athletes == null || athletes.isEmpty()) {
            throw new IllegalArgumentException("Nenhum atleta encontrado para o continente e sexo especificados.");
        }

        Map<String, List<Athlete>> athletesByCountry = athletes.stream()
                .collect(Collectors.groupingBy(Athlete::getCountry));

        Map<String, Double> bmiAveragesByCountry = athletesByCountry.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToDouble(Athlete::getBMI).average().orElse(0.0)));

        Map.Entry<String, Double> maxEntry = bmiAveragesByCountry.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalStateException("Não foi possível determinar o país com a média de IMC mais elevada."));

        String countryWithMaxBMI = maxEntry.getKey();
        double maxBMIAverage = maxEntry.getValue()  ;

        return new MaxBMICountryDTO(countryWithMaxBMI, maxBMIAverage);
    }

    // Exercício 6
    public boolean addAthleteToBrazilOlympicFootballTeam(AthleteBrazilFootballDTO athleteDTO) {
        if (athleteDTO.getCountry().equalsIgnoreCase("BRA")
                && athleteDTO.getSport().equalsIgnoreCase("football")
                && athleteDTO.getGender().equalsIgnoreCase("male")) {
            Athlete athlete = new Athlete();
            athlete.setContinent(athleteDTO.getContinent());
            athlete.setCountry(athleteDTO.getCountry());
            athlete.setHeight(athleteDTO.getHeight());
            athlete.setWeight(athleteDTO.getWeight());
            athlete.setBMI(athleteDTO.getBMI());
            athlete.setAge(athleteDTO.getAge());
            athlete.setGender(athleteDTO.getGender());
            athlete.setSport(athleteDTO.getSport());

            athletesMap.put(String.valueOf(athletesMap.size() + 1), athlete);

            return true;
        } else {
            return false;
        }
    }

    // Exercício 7
    public List<Athlete> getBrazilOlympicFootballTeam() {
        if (athletesMap == null || athletesMap.isEmpty()) {
            throw new IllegalStateException("Athletes map is null or empty.");
        }

        // Filtra os atletas que são do Brasil, praticam futebol e são do gênero masculino
        return athletesMap.values().stream()
                .filter(athlete -> athlete.getCountry().equalsIgnoreCase("BRA")
                        && athlete.getSport().equalsIgnoreCase("football")
                        && athlete.getGender().equalsIgnoreCase("male"))
                .collect(Collectors.toList());
    }
    // Exercício 8
    public boolean editAthleteInformation(String athleteId, AthleteEditDTO athleteDTO) {
        if (!athletesMap.containsKey(athleteId)) {
            return false;
        }
        Athlete athleteToUpdate = athletesMap.get(athleteId);

        athleteToUpdate.setContinent(athleteDTO.getContinent());
        athleteToUpdate.setCountry(athleteDTO.getCountry());
        athleteToUpdate.setHeight(athleteDTO.getHeight());
        athleteToUpdate.setWeight(athleteDTO.getWeight());
        athleteToUpdate.setBMI(athleteDTO.getBMI());
        athleteToUpdate.setAge(athleteDTO.getAge());
        athleteToUpdate.setGender(athleteDTO.getGender());
        athleteToUpdate.setSport(athleteDTO.getSport());

        athletesMap.put(athleteId, athleteToUpdate);

        return true;
    }

    // Exercício 9
    public List<Athlete> preSelectParisOlympicsFemale() {
        return athletesMap.values().stream()
                .filter(athlete -> athlete.getGender().equalsIgnoreCase("female")
                        && athlete.getAge() < 20
                        && !athlete.getSport().equalsIgnoreCase("football"))
                .collect(Collectors.toList());
    }

    // Exercício 10
    public void addAthleteToBrazilOlympicAthleticsTeam() {
        Athlete athlete = new Athlete();
        athlete.setContinent("North America");
        athlete.setCountry("BRA");
        athlete.setHeight(Double.max(1.5, 2.0));
        athlete.setWeight(Double.max(50, 100));
        athlete.setBMI(athlete.getWeight()/(athlete.getHeight() * athlete.getHeight()));
        athlete.setAge(18.0);
        athlete.setGender("female");
        athlete.setSport("athletics");

        athletesMap.put(String.valueOf(athletesMap.size() + 1), athlete);
        preSelectParisOlympicsFemaleAthletes();
    }


    public void preSelectParisOlympicsFemaleAthletes() {
        List<Athlete> preSelectedAthletes = preSelectParisOlympicsFemale();
        athletesPreSelectParisOlympicsFemale = new HashMap<>();
        for (int i = 0; i < preSelectedAthletes.size(); i++) {
            athletesPreSelectParisOlympicsFemale.put(String.valueOf(i), preSelectedAthletes.get(i));
        }
    }

    public List<Athlete> getAthletesByContinentAndGender(String continent, String gender) {
        // Filtra os atletas com base no continente e gênero fornecidos
        return athletesMap.values().stream()
                .filter(athlete -> athlete.getContinent().equalsIgnoreCase(continent)
                        && athlete.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    private void storeData(Athlete[] athletes) {
        athletesMap = new HashMap<>();
        for (int i = 0; i < athletes.length; i++) {
            athletesMap.put(String.valueOf(i), athletes[i]);
        }
    }
}
