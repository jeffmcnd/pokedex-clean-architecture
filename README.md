# Pokemon with Clean Architecture
## The Project
First thing is first, we want to define the project. The client (my mind) wants to create a simplistic app that tracks Pokemon and trainers. The app should be useable by trainers and have the following capabilities:
* Become a trainer and login
* Delete their trainer account
* Search Pokemon
* Search other trainers
* Add Pokemon to their roster
* Remove Pokemon from their roster
* Change their name
## Framework Dependencies
The first discussion we must have is which language and libraries on which we want to depend. For this project, we will be using Kotlin as the main language. The only other dependency will will have is on a class called Try. Try is the implementation of a functional paradigm where functions always return an object that can be manipulated that encapsulate different behaviour. Specifically, Try encapsulates the attempt at performing an action whose positive result is defined and whose negative result is a Throwable error encapsulated in the sealed class.Here is a useful article on the abilities and reasoning behind the Try class: https://dzone.com/articles/kotlin-try-type-for-functional-exception-handling

## Defining the Domain
To define the domain we need to know some things about the system. Mainly, what type of data do we want to interact with? What are the rules for that information? Does that data have to be complete? Can some values be optional? Are there any data states that we want to allow or disallow?

We start with the Pokemon class. In order to keep things simple for this example we define the class as follows:

```
data class Pokemon(val id: String, val name: String, val types: Set<Type>)
```

Note that we use a Set for the types variable. Of course, having a Pokemon that has a type twice doesn’t make sense. So we’re good! Oh wait, we need to define types. So we define the type:

```
sealed class Type
object Grass : Type()
object Fire : Type()
object Water : Type()
```

For our purposes, lets keep it simple and choose three types. I won’t pretend to understand all of the implications of choosing a sealed class over an enum, but I know that sealed classes can be helpful. So that’s what I’ve chosen.

Great, we’ve got a Pokemon class that represents a Pokemon! The last concept we want to encompass is the trainer. Let’s create trainer class.

```
data class Trainer(val id: String, val name: String, val pokemon: MutableList<Pokemon>)
```

Perfect. Trainers has an ID, a name, and own a list of Pokemon. Note that the Pokemon list is mutable. A trainer may add or remove Pokemon from the list as they catch, trade, and release new Pokemon. This seems simple and elegant but we’re forgetting one thing: if this app is going to allow new trainers to come to the show, we should be able to start with zero Pokemon and create new trainers. So we allow the ID to be null and add default values for the ID and Pokemon. We’re left with the following:

```
data class Trainer(val id: String? = null, val name: String, val pokemon: MutableList<Pokemon> = ArrayList())
```

OK, this makes sense. We can create a new trainer and all we have to specify is a name. Their ID and Pokemon are defaulted. Only until the trainer is saved will it have an ID that we can reference, until then we should be null checking.

With this we are done the domain layer! We’ve created classes for Pokemon and trainers. So far so good, everything makes sense and is simple. This just goes to show you how separating the layers out can help simplify the code. The real cost in decoupling things.

## Defining the Gateway (Source)
The next piece of work is to define the gateway interfaces. The gateway defines the layer you will use to access your entities from your persistence layer. In other words these are you data sources, which is why I choose to called this “source.”

First we must consider a few things before defining our interfaces. These considerations could be:
* What can be created?
* What can be read?
* What can be updated?
* What can be deleted?
* Paging

There may be several other considerations you should take into account when creating a source interface, but these are the ones I’ve come up with for this example.

### The Pokemon Source
Here is the PokemonSource interface:

```
interface PokemonSource {

    fun getAll(page: Int = 0, pageSize: Int = 25): Try<List<Pokemon>>

    fun getById(id: String): Try<Pokemon?>

    fun getByNameStartingWith(name: String, page: Int = 0, pageSize: Int = 0): Try<List<Pokemon>>

}
```

Note the use of paging in this example which is a consideration for future proofing. Let’s assume that the Pokemon entity will only grow in complexity and that the number of Pokemon will increase for the foreseeable future. In these cases, paging is essential. This is a practicality design. We don’t want to transmit too much data at a time if the user doesn’t require it, regardless of the persistence layer implementation. We’ve defaulted the `page` and `pageSize` variables for ease of use.

So, Pokemon are stored and this application will not support adding new Pokemon to the persistence layer. That would be work on the persistence layer itself to add or remove Pokemon manually. In order to keep things simple we will have a function for getting all of the Pokemon, getting Pokemon by ID, and getting Pokemon by names that start with a given string, also known as searching. Something to note is the naming of the search function `getByNameStartingWith`. It’s important to name this function in a way that the programmer understands how to use it. This name is self-documenting (a dangerous term). Rather than just giving a simplistic name and a parameter/return value, this name tells the user how the function will perform and that we shouldn’t expect Charmander to be returned if we search “arman.”

You might say “We can surely have better search functionality than this” and you would be right. However, for the purposes of this project, we’ll stick with the beginning of the Pokemon’s name.

### The Trainer Source
Next we define the Trainer interface:
```
interface TrainerSource {

    fun save(trainer: Trainer): Try<String>

    fun delete(trainer: Trainer): Try<String>

    fun getAll(page: Int = 0, pageSize: Int = 25): Try<List<Trainer>>

    fun getById(id: String): Try<Trainer?>

    fun getByNameStartingWith(name: String, page: Int = 0, pageSize: Int = 25): Try<List<Trainer>>

}

```

Similar to the PokemonSource interface, we have the same getter functions. What we’ve added are the remaining CRUD functions. We should be able to add new trainers, update those trainers if their list of Pokemon or names change, or delete them if they give up the pursuit to catch ‘em all. In each of these functions we return a string which represents the ID of the trainer that is created/updated or deleted.

And that’s it! We defined three interfaces that interact with two of our entities. These two interfaces define what our persistence layer is capable of doing. Remember that the system cannot add Pokemon and that trainers can be added, updated, and deleted.



## References
* Implementing Clean Architecture by Lieven Doclo [Youtube]: https://www.youtube.com/watch?v=O6tdJO4aB7c
* Java example of the Clean Architecture [GitHub]: [GitHub - lievendoclo/cleanarch: Java example of the Clean Architecture](https://github.com/lievendoclo/cleanarch)
