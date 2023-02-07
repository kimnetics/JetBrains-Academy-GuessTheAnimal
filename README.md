# JetBrains Academy Guess the Animal Project

An example of a passing solution to the final phase of the JetBrains Academy Java Guess the Animal project.

## Description

The relative directory structure was kept the same as the one used in my JetBrains Academy solution. The resource files originated from JetBrains Academy. They were edited slightly to meet my needs.

The project makes use of Jackson to handle JSON/XML/YAML storage of the animal data file. The command line supports a "-type [json|xml|yaml]" argument to specify which file format you prefer. The default is JSON.

The project supports both English and Esperanto text. Which language to use is determined by the language code for the environment. In IntelliJ IDEA, this can be set with a VM option value like "-Duser.language=en".

In the last phase of the project, I replaced my enter animal parsing logic with the regex patterns from the JetBrains Academy provided resource files. This seems to have broken the "the" handling logic of the enter animal method. The final phase test doesn't seem to catch this. But if you use this code as a model, you could fail earlier phase validation.
