# TemporalTagger
Tool for extracting and parsing time expressions from natural language texts. You can find the readme in Portuguese <a href="https://github.com/sankhyalabs/bia-time/blob/master/README.md">here</a>.

## Overview and Usage
TemporalTagger is a library designed for temporal tagging in Brazilian portuguese. The library proposes a solution to the sub-problem of creating a period (start date and end date in a computer-friendly format) from a user's query in natural language, alongside a more general tool for extracting time expressions from natural language texts in general. It can be used in chatbots, schedulers, personal assistants, or any program expected to receive temporal information in a non-standard format. 
<br>
TemporalTagger can be used as shown below: 
```
// instantiate a parser with default date
PeriodParser periodParser = new PeriodParser();

// receive results in the form of a TempoPeriod object
TempoPeriod result = periodParser.parse("Por favor, me manda o relatório das vendas da semana passada.");

// retrieve the start and end dates of the interval 
LocalDate start = result.getInitialDateFormatted();
LocalDate end = result.getFinalDateFormatted();
```

## Why TemporalTagger
### TemporalTagger vs Natural Language Processers (DialogFlow)
The need for TemporalTagger arised when I tried using <a href="https://dialogflow.com/">Dialogflow</a> (DF) for NLP parsing in a chatbot project. DF did a good job finding the intention of the user and parsing most of the query's entities. When it came to time information though, the general-purpose parsers struggled whenever the grammar stirred from strict correctness, or the time units grew too complicated in relativity. As such, TemporalTagger is to be used as a complement to all-purpose natural language parsers, as well as a stand-alone solution for temporal tagging in projects not relying on advanced tech-solutions for NLP. 

### TemporalTagger vs General-Purpose Temporal Taggers
<a href="https://nlp.stanford.edu/projects/time.shtml">Temporal tagging</a> is a widely acknowledged and thoroughly explored subfield of NLP. The aim is to recognize time expressions and parse them into a more computer-friendly format (the format typically agreed on is <a href="">EEE</a>). Given the variety of source texts to work on, as well as the wide range of programs to carter for, general-purpose temporal taggers typically return partially parsed dates that need to be further processed and resolved. TemporalTagger focuses on a narrow-field with a specific purpose in mind and is able to return resolved, formatted and ready-to use dates from the texts it is fed. As such, this tool handles another layer of processing to abstract the entire unit of time extraction.
<br><br>
In addition, TemporalTagger also handles date extraction without partial-resolution (term used throughout the README and development tutorials to refer to the action of extracting implicit date values from a text. E.g: from the 2 (implied month and year values) to the 4 of December 2013) for texts other than user queries. Future versions of TemporalTagger will aim to provide general-purpose resolution schemas to broaden the scope of work of the library.

## What TemporalTagger does
It has the ability to parse absolute and partial date references, expressed in numerical and alphabetic forms. Below are a few examples of the date fromats and types supported by the library. A complete list of the dates supported in version 1 can be found <a href="">here</a>. 
<br>
<table>
      <thead>
            <tr>
                  <th>Date Type</th>
                  <th>Input Examples</th>
                  <th>Output (PeriodParser, LocalDate object)</th>
            </tr>
      </thead>
      <tbody>
            <tr>
                  <td rowspan = 2>Standard Date (absolute)</td>
                  <td>Manda infromações sobre as vendas desde o dia 23/02/2018 até o dia 24/05/2018</td>
                  <td>Init.: <b>01/11/2018</b><br>Fin.: <b>31/12/2018</b></td>
            </tr>
            <tr>
                  <td>Como foram as vendas do dia 05 do dezembro do ano 2015?</td>
                  <td>Init.: <b>05/12/2015</b><br>Fin.: <b>05/12/2015</b></td>
            </tr>
            <tr>
                  <td rowspan = 2>Standard Date (relative, with reference date: 27/12/2018)</td>
                  <td>Por favor, como foi o desempenho da emprasa no ano passado?</td>
                  <td>Init.: <b>01/01/2017</b><br>Fin.: <b>31/12/2017</b></td>
            </tr>
            <tr>
                    <td>Como estao as compras da semana atual?</td>
                    <td>Init.: <b>24/12/2018</b><br>Fin.: <b>30/12/2018</b></td>
            </tr>
            <tr>
                  <td rowspan = 2>Additional Time Units</td>
                  <td>Marca uma viagem na minha agenda para o proximo final de semana.</td>
                  <td>Init.: <b>29/12/2018</b><br>Fin.: <b>30/12/2018</b></td>
            </tr>  
            <tr>
                  <td>Quero ver os medios do semestre passado.</td>
                  <td>Init.: <b>01/01/2018</b><br>Fin.: <b>30/06/2018</b></td>
            </tr>
            <tr>
                  <td rowspan = 2>Cardinal Time Units</td>
                  <td>Quero receber os meus relatorios na primeira semana do terceiro trimestre do ano.</td>
                  <td>Init.: <b>02/07/2018</b><br>Fin.: <b>07/07/2018</b></td>
            </tr>
            <tr>    
                  <td>Qual foram as datas de segunda semana do mes passado?</td>
                  <td>Init.: <b>12/12/2018</b><br>Fin.: <b>17/12/2018</b></td>
            </tr>
            <tr>
                  <td rowspan = 2>Quantity Time Units</td>
                  <td>Manda esse email para meus colegas dois dias depois.</td>
                  <td>Init.: <b>29/12/2018</b><br>Fin.: <b>29/12/2018</b></td>
            </tr>
                  <td>O que aconteceu na minha cidade 3 semanas antes?</td>
                  <td>Init.: <b>10/12/2018</b><br>Fin.: <b>15/12/2018</b></td>
            </tr>
      </tbody>
</table>

## What TemporalTagger does NOT do
As mentioned earlier, TemporalTagger v1 does not offer a resolution schema with its MainParser. As such, any partial dates retrieved by this  parser will be filled automatically from the reference date values (for the default reference day of today (01/01/2019), when using MainParser, the query "2 ao 4 de dezembro de 2013" will return (ini.: 02/02/2019, fin.: 04/12/2013) instead of the intended (ini.: 02/12/2013, fin.: 04/12/2013)). The PeriodParser, while capable of date resolution, is expecting no more than 2 time expressions in every query, and returns the default date (set to today) if more dates are found. Finally, as of v1, no support is provided for time units yet. The table below illustrates the examples mentioned in this text. If you would like to improve the work of the library in this area or more, checkout the development tutorials in the next section.
<br>
<table>
      <thead>
            <tr>
                  <th>Use Case</th>
                  <th>Input Examples</th>
                  <th>Current Output</th>
            </tr>
      </thead>
      <tbody>
            <tr>
                  <td>No partial-resolution for MainParser</td>
                  <td>Mosta as vendas do dia 02 ate o dia 14/03/2016. Amanha, me manda o relatorio dessas vendas por favor.</td>
                  <td>Dates returned with reference date = 01/01/2019: 02/01/01/2019, 14/03/2016, 02/01/2019.</td>
            </tr>
            <tr>
                  <td>Multiple date instances</td>
                  <td>Mosrta as vendas do setor X da semana passada, ontem e dia 12/03/2016.</td>
                  <td>Error: more than 2 dates found, return default date: 01/01/2019</td>
            </tr>
            <tr>
                  <td>Time Units</td>
                  <td>Marca meu calendario para uma reuniao amanha nas 4 horas.</td>
                  <td>Unable to parse hours => no time instance returned.</td> 
            </tr>
      </tbody>
</table>

## Development Tutorials
If you would like to contribute to the development of this library, the tutorials below might help you get better acquainted with the implementation of the library. All tutorials/documentation can be found in the wiki of this repository.

1. <a href="https://github.com/NajwaLaabid/TemporalTagger/wiki/How-the-algorithm-works">How the algorithm works</a>
2. <a href="https://github.com/NajwaLaabid/TemporalTagger/wiki/How-to-debug">How to debug</a>
2. <a href="https://github.com/NajwaLaabid/TemporalTagger/wiki/How-to-add-new-cases-to-TemporalTagger">How to add new cases</a>
3. <a href="https://github.com/NajwaLaabid/TemporalTagger/wiki/Future-work">Future Work</a>

## Installation
To use TemporalTagger, perform the following steps:
- Download the jar file of the latest release from the corresponding section on this github repository. 
- Include the jar file as a dependency in your project.
- Import the library where needed in your program files.
- Instantiate the parser(s) you wish to use (refer to the example in the first section of this README).
- Call the function "parse" of the parser(s) initiated earlier and give it the text you wish to parse.

## License
This work is the property of Sankhya Jiva. It is meant to be used as an internal module by any teams/projects within the company. Any usage of TemporalTagger by external entities requires the explicit permission of the owner of the project (i.e, representative of Sankhya Jiva). These terms of usage shall be changed should the project be released to the public in the future.


