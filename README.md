# meli-recruiting-mutants

In order to execute unit test and integration is necessary execute the following maven command:

###### ./mvnw clean compile test jacoco:report package

About jacoco report, this one is in the following path:
###### {base.project}/recruiting-mutants/target/site/jacoco

# API Rest

The API Rest contains 2 endpoints
1. Mutant Service ("/mutant")
2. Stats Service ("/stats")

You can send request to the following url: http://ec2-user@ec2-100-26-255-192.compute-1.amazonaws.com:8080


## Mutant Service 
Mutant Service allows you save dna mutants


URL: http://localhost:8080/mutant

Headers: Content-Type:application/json

Body example:


    {
        "dna": [
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        ]
    }

## Stats Service
Stats service allows you get information about mutant and human saved



