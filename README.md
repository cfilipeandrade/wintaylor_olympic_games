Teste Java
Pré-requisitos
•	Utilizar de Java8 até Java11.0.19;
Setup
•	Criar o projeto Java com nome “wintaylor_olympic_games”
•	Utilizar o SpringBoot
•	Porta de execução deverá ser a 10001
•	Utilizar como o resultado de um banco de dados o JSON presente no seguinte URL: https://cdn.jsdelivr.net/gh/highcharts/highcharts@24912efc85/samples/data/olympic2012.json
•	Estrutura mínima:
o	Service
o	Controller
Requisitos
•	O projeto deverá ser entregue juntamente com o enunciado
•	Deverá ser entregue uma API de teste e os parâmetros necessários para cada exercício
•	Deverá ser entregue o arquivo .jar bem como o comando de execução do mesmo

Exercícios
Exercício 1
Criar um endpoint  que leia toda a informação do JSON e guarde num HashMap<String,POJO>, a chave de cada objecto será o número da linha correspondente ao seu posicionamento no Array.
Esse método deverá ser executado inicialmente e servirá de base para os exercícios seguintes
O método está rodando de forma assincrona então o endpoint é apenas demonstrativo
GET http://localhost:10001/load-data

Exercício 2
Criar um endpoint que receba como parâmetro o código do país e retorne um objeto que liste todos os esportes que esse pais pratica.
GET http://localhost:10001/sports?countryCode={country}
Exemplo:
http://localhost:10001/sports?countryCode=AFG

Exercício 3
Criar um endpoint que receba como parâmetro o código do país e um esporte, e retorne um objeto com a quantidade de homens e mulheres, e as médias de idade
GET http://localhost:10001/statistics?countryCode={country}&sport={sport}
Exemplo:
http://localhost:10001/statistics?countryCode=AFG&sport=athletics

Exercício 4
Criar um endpoint que receba como parâmetro o esporte e uma idade, e retorne um objeto com a quantidade de pessoas acima dessa idade por continente 
GET http://localhost:10001/age-media-by-continent?sport={sport}&ageThreshold={age}
Exemplo:
http://localhost:10001/age-media-by-continent?sport=athletics&ageThreshold=20

Exercício 5
Criar um endpoint que receba como parâmetro o continente e o gênero, e retorne um objeto com o pais que tenha a maior média de índice de massa corporal (BMI) do continente, juntamente com o valor da média.
Exemplo:
GET http://localhost:10001/max-bmi-country?continent=Europe&gender=female

Exercício 6
Criar um endpoint que insira um atleta na seleção de futebol olímpica masculina do Brasil
Defina o método HTTP como POST.
Insira a URL do endpoint: http://localhost:10001/add-athlete-to-brazil-olympic-football-team
Selecione a aba "Body".
Selecione "raw" e escolha o tipo de dados como JSON (application/json).
No corpo da solicitação, insira os detalhes do atleta no formato JSON. Por exemplo:
{
    "continent": "South America",
    "country": "BRA",
    "height": 1.75,
    "weight": 75,
    "BMI": 24.49,
    "age": 28,
    "gender": "male",
    "sport": "football"
}

Exercício 7
Criar um endpoint que liste todos os atletas da seleção de futebol olímpica masculina do Brasil
GET http://localhost:10001/brazil-football-team

Exercício 8
Criar um endpoint que edite a informação de um determinado atleta.
Defina o método HTTP como PUT.
Insira a URL do endpoint: http://localhost:10001/edit-athlete/{athleteId}
{athleteId} é a posição que ele está no HashMap e no Json
Selecione a aba "Body".
Selecione "raw" e escolha o tipo de dados como JSON (application/json).
No corpo da solicitação, insira os detalhes do atleta no formato JSON. Por exemplo:
{
    "continent": "South America",
    "country": "BRA",
    "height": 1.75,
    "weight": 75,
    "BMI": 24.49,
    "age": 28,
    "gender": "male",
    "sport": "football"
}

Exercício 9
Criar um endpoint que:
•	faça uma pré seleção para os jogos olímpicos de Paris, apenas deverão ser consideradas as atletas femininas de todas as modalidades excepto o futebol e com menos de 20 anos.
•	liste os resultados
Esse resultado deverá ser guardado numa estrutura igual à estrutura presente no exercício 1.
GET http://localhost:10001/preselect-paris-olympics-female-athletes

Exercício 10
Criar um endpoint que:
•	insira uma atleta na seleção de atletismo olímpica feminina do Brasil com 18 anos de idade.
•	execute a pré selecção para os jogos olímpicos de Paris
•	liste os resultados
POST http://localhost:10001/add-athlete-to-brazil-olympic-athletics-team
