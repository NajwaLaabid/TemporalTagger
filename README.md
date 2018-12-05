# English:

## Description
Temporal Tagger is a library desigened for temporal tagging in Brazilian portuguese. It has the ability to parse absolute and partial date references, expressed 
in numerical and alphabetic forms. The library offers support for major date formats, such as dd/mm/yyyy, Day of Month of Year, last December, this January...etc. 

## Algorithm Overview
Temporal Tagger lays on top of a finite automaton created manually for every language supported. Every parser runs a DFS on one or many automatas to find all the possible date parsing schemas in the given text, then parses the results found onto a reductor which works on removing duplicate and partial date parsing, to only leave the correct time expressions mentioned in the document. 
Finally, every parser of Temporal Tagger works on merging the results found in a specific way, depending on the goal of the parser.
<br><br>PeriodParser is the only parser available currently in V1.It parses a user's query with the aim of finding a time interval for data querying, and throws an exception when it finds more than a single date reference.
<br><br>The automaton for Brazilian Portuguese V1 can be found <a href="">here</a>.

## Examples
The following are basic examples of the cases handled by Temporal Tagger's different parsers. The reference for relative dates is today (date of writing this file: 2018-12-04).

<table>
      <thead>
            <tr>
                  <th>Sentence</th>
                  <th>PeriodParser (formatted dates)</th>
            </tr>
      </thead>
      <tbody>
            <tr>
                  <td>Como estão as vendas do mês passado até o final desse ano?</td>
                  <td>
                        Init.: <b>01/11/2018</b>
                        <br>Fin.: <b>31/12/2018</b>
                  </td>
            </tr>
            <tr>
                  <td>Por favor, como foi o desempenho da emprasa no ano passado?</td>
                  <td>
                        Init.: <b>01/01/2017</b>
                        <br>Fin.: <b>31/12/2017</b>
                  </td>
            </tr>
            <tr>
                  <td>Manda infromações sobre as vendas desde o dia 23/02/2018 até o dia 24/05/2018</td>
                  <td>
                        Init.: <b>23/02/2018</b>
                        <br>Fin.: <b>24/05/2018</b>
                  </td>
            </tr>
      </tbody>
</table>

A full list of the date formats supported by the library can be found here.

## Development Tutorials
If you would like to contribute to the development of this library, the tutorials below might be of good use to you. More tutorials/documentation can be found in the wiki of this repository.

1. <a href="">How to add new cases to Temporal Tagger</a>
2. DFS and automata traversal: understand how the traversal algorithm works
3. Understanding Temporal Tagger Parsers
4. Basic Regex rules in Temporal Tagger
5. Temporal Tagger components breakdown
6. Expanding Temporal Tagger for multiple language support

## Your turn!
To use Temporal Tagger, perform the following steps:
- Download the jar file of the latest release from the corresponding section on this github repository. 
- Include the jar file as a dependency in your project.
- Import the library where needed in your program files.
- Instantiate the parser(s) you wish to use, as shown below:
      - 
- Call the function "parse" of the parser(s) initiated earlier and give it the text your wish to parse
