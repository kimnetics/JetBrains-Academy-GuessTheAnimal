# JetBrains Academy Guess the Animal Project

An example of a passing solution to the final phase of the JetBrains Academy Java Guess the Animal project.

## Description

This project is a command line application that plays a guess the animal game. Each round the application asks a series of questions to attempt to determine the animal being thought of by the user. The questions are based on information gleaned from prior rounds.

The project covered tree structures. The data which powers the questions is stored in a tree structure.

The project covered internationalization. The game may be played in English or Esperanto. Notable is that the entry validation logic supports different languages.

The project covered data persistence. Between runs of the application, the data tree structure is saved to a JSON/XML/YAML file.

## Notes

The relative directory structure was kept the same as the one used in my JetBrains Academy solution.

The resource files which are used for the prompts and validation originated from JetBrains Academy. They were edited slightly to meet my needs.

The project makes use of Jackson to handle JSON/XML/YAML storage of the animal data file. The command line supports an optional "-type [json|xml|yaml]" argument to specify which file format you prefer. The default is JSON.

Because the application uses a library and does not have a build tool configured, it will probably be easiest to run the application from IntelliJ. The repo includes an IntelliJ iml file with the needed dependencies.

The project supports both English and Esperanto text. Which language to use is determined by the language code for the environment. In IntelliJ, this can be set with a VM option value like "-Duser.language=en".

Here is an example session:

(User entered items were given a '> ' prefix, which did not actually show in the console.)

```
Good afternoon!

Welcome to the animal’s expert system!

What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 1
You think of an animal, and I guess it.
Press enter when you’re ready.
> 
Is it a cat?
> No
I give up. What animal do you have in mind?
> Dolphin
Specify a fact that distinguishes a cat from a dolphin.
The sentence should be of the format: It can/has/is ....
> It can swim
Is the statement correct for a dolphin?
> Yes
the cat can't swim
the dolphin can swim
Nice! I’ve learned so much about animals!
Want to play again?
> Yes
You think of an animal, and I guess it.
Press enter when you’re ready.
> 
Can it swim?
> Yes
Is it a dolphin?
> No
I give up. What animal do you have in mind?
> Piranha
Specify a fact that distinguishes a dolphin from a piranha.
The sentence should be of the format: It can/has/is ....
> It is mean
Is the statement correct for a piranha?
> Yes
the dolphin isn't mean
the piranha is mean
Awesome! I’ve learned so much about animals!
Want to try again?
> Yes
You think of an animal, and I guess it.
Press enter when you’re ready.
> 
Can it swim?
> No
Is it a cat?
> No
I give up. What animal do you have in mind?
> Dog
Specify a fact that distinguishes a cat from a dog.
The sentence should be of the format: It can/has/is ....
> It can bark
Is the statement correct for a dog?
> Yes
the cat can't bark
the dog can bark
Wonderful! I’ve learned so much about animals!
Would you like to play again?
> No
What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 2
Here are the animals (facts) I know:
 - cat
 - dog
 - dolphin
 - piranha

What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 3
Enter the animal:
> Piranha
Facts about the piranha:
 - It can swim
 - It is mean

What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 4
The Knowledge Tree stats

- root node                    It can swim
- total number of nodes        7
- total number of animals      4
- total number of statements   3
- height of the tree           2
- minimum depth                2
- average depth                2.0

What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 5
└ Can it swim?
  ├ Is it mean?
  │ ├ a piranha
  │ └ a dolphin
  └ Can it bark?
    ├ a dog
    └ a cat

What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 0

It was nice seeing you!
```

In the last phase of the project, I replaced my enter animal parsing logic with the regex patterns from the JetBrains Academy provided resource files. This seems to have broken the "the" handling logic of the enter animal method. The final phase test doesn't seem to catch this. But if you use this code as a model, you could fail earlier phase validation.
