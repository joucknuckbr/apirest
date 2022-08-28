# API In-memory

API desenvolvida no teste da Nelogica

## How to use

### 1º Instalar todas as dependências do pom.xml

### 2º Rodar o ApiHPApplication.java

### 3º Testar os métodos do controller
* GET /pushAll -> Método que busca todos os dados de Characters no endereço "http://hp-api.herokuapp.com/api/characters" e salva em memória estática.
    * Obs: Tem um bug quando vai salvar "Wand.length" pois o JSON que é retornado da API externa fica variando o tipo da key "length", conforme a imagem abaixo:
    ![image info](.\Images\tipos_na_key_length.jpg)
    Então eu tratei cada tipo e salvei no tipo Double.

* GET /showAll -> Método que busca todos os dados de Characters na memória estática e retorna os mesmos.

* GET /showOne?id={id} -> Método que busca todos os dados de um Character na memória estática passando seu Id e retorna os mesmos.

* POST /addNewPersonage -> Método que adiciona um Personage na memória estática passando um JSON no body do request.
    * JSON exemple:
```
{
    "name": "Nuno",
    "alternate_names": ["Braners", "Bruno"],
    "species": "human",
    "gender": "male",
    "house": "Casa do Luiz",
    "dateOfBirth": "1999-05-11",
    "yearOfBirth": 1999,
    "wizard": true,
    "ancestry": "Portuguese",
    "eyeColour": "brown",
    "hairColour": "brown",
    "wand": {
        "wood": "Accacia",
        "core": "Dragon heart",
        "length": 33.0
    },
    "patronus": "Cat",
    "hogwartsStudent": true,
    "hogwartsStaff": false,
    "actor": "Nuno",
    "alternate_actors": ["Fuleco","Pedro"],
    "alive": true,
    "image": "Num tem"
}
```

* DELETE /deletePersonage?id={id} -> Método que deleta um Personage da memória estática passando seu Id.

* PUT updatePersonage?id={id} -> Método que atualiza um Personage da memória estática passando seu Id e um JSON no body do request.
    * JSON exemple:
```
{
    "name": "Nuno",
    "alternate_names": ["Braners", "Bruno"],
    "species": "human",
    "gender": "male",
    "house": "Casa do Luiz",
    "dateOfBirth": "1999-05-11",
    "yearOfBirth": 1999,
    "wizard": true,
    "ancestry": "Portuguese",
    "eyeColour": "brown",
    "hairColour": "brown",
    "wand": {
        "wood": "Accacia",
        "core": "Dragon heart",
        "length": 33.0
    },
    "patronus": "Cat",
    "hogwartsStudent": true,
    "hogwartsStaff": false,
    "actor": "Nuno",
    "alternate_actors": ["Fuleco","Pedro"],
    "alive": true,
    "image": "Num tem"
}
```

### 4º Testes automáticos
* Basta rodar todos na ferramenta de execução da sua preferência.