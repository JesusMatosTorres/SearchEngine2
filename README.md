# SearchEngine
Full implementation of a search engine using an inverted index structure.
## Overview 📋
The code comprises of the following architecture:

* Indexer, that extracts all the words from each document and build the inverted index (datamart).

* Crawler, a small program that periodically determines what documents to download and feeds them to the document repository (datalake).

* A query engine that publishes an API for receiving search requests from users.


## The code 🔧
*Java 17 and json are pre-requirements for the execution of this project*

Ensure all the files from the repo are present in the same directory.

## Insight on the implementation ⚙️

The *API* module handles requests that are delivered to the system through the client. Later, this very request is processed and filtered through different methods in classes which have specific functionalities and are comprised within a module, the result is shown if there are matches to the given query. A *Datalake* and *Datamart* repository will contain all the data obtained from the *Crawler* and the *Indexer*. 

The engine in it's final state, is deployed into a docker container, to solve *scalability*. As well as a cluster is built with the following containers to distribute the load of tasks to various systems, this in in turn solves the problem of *availability*.

## Built With 🛠️

* [Intellij Idea](https://www.jetbrains.com/es-es/idea/) - The text editor used.
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - The java version used.
## Authors ✒️
* **Javier García**
* **Jesús Matos**
* **Liam Mahmud**
* **Krish Sadhwani**
