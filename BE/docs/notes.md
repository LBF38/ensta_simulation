# Notes sur le BE

Pour la modélisation du BE, on peut se baser sur le cahier des charges fourni pour la cure thermale Tatooine.

## Comment gérer les évènements qui sont annulés ?

Il y a plusieurs méthodes pour modéliser cela.
Dans la modélisation générale du projet, on peut définir le générateur de clients avec un processus bien défini pour la
production de clients.

Il faut que ce processus de production des clients connaisse les horaires d'ouvertures de la cure thermale.

Ainsi, pour la gestion des évènements annulés, on peut:

- Annuler l'évènement en le supprimant.
    - pour cela, il faut garder un lien vers l'évènement posté.
    - Il faut donc utiliser un pattern de listener.
- Déposter l'évènement ou poster un évènement qui annule cet évènement ?
- => autre modélisation personnalisé.

Le sujet est libre, on peut le modéliser comme on le souhaite.

De même, pour la panne des machines, on peut utiliser le pattern listener pour écouter les états actuels des machines et
ainsi mettre à jour les évènements en fonction des états des machines.

## Propositions de modélisation

Cette partie reprend les informations fournies par le prof sur des pistes de modélisation concernant le sujet de BE.

Pour implémenter le sujet, il faut:

- modéliser les créneaux horaires
- modéliser les taux d'avancement d'une donnée continue (ex: quantité de traitement)

> [!TIP]
> On peut poster un même évènement à T0.

## Concernant les états

Il est important de dissocier les états des entités simulées des objets, langages et de l'engine de simulation.
Les états sont explicitement définis afin de dissocier la gestion du cycle de vie de celui du moteur de simulation.

Ainsi, pour les états de mort de nos entités, il est préférable de définir des états explicites, un état MORT, afin que
l'entité soit toujours présente dans la simulation tout en étant mort.

De même, pour l'initialisation, il est important d'avoir un état INITIALIZED pour bien dissocier l'état de création des
objets de celui d'initalisation de celles ci.

Ainsi, on ne peut rien faire avec les objets non initialisés.

Et cela permet également de garantir le fait que l'on ait toutes les entités simulées initialisées lors du début de la
simulation.

## Evènements

Pour gérer les activités, avec leur liste d'attente, on part sur un modèle de demande/réponse.

Quand un client arrive sur une activité, il demande à l'activité s'il y a encore de la place.

En fonction de la réponse, il peut commencer l'activité ou il est directement placé dans la liste d'attente.

## Configuration en JSON

Pour faciliter la configuration de notre simulation et des différents objets à créer, on souhaiterait utiliser des
fichiers de configuration au format JSON.

Ainsi, un premier test est réalisé dans la classe `InitWorkshop` afin de voir comment fonctionne la librairie `jakarta`.

Bref, on constate donc que, pour pouvoir utiliser cette méthode de création d'objets, on doit créer un constructeur de
classe par défaut (pas d'arguments) car ce seront ces arguments par défaut qui seront utilisés en cas de problèmes de
parsing du JSON lors de la création d'une classe.

Ce point est démontré dans la méthode `main` de la classe `InitWorkshop`. Le second json comporte une erreur, ce qui est
remplacé par l'argument par défaut lors de l'initialisation.

On peut également initialiser une liste d'objets/classes configuré à partir d'un json (bien formatté, naturellement).
Pour cela, il suffit d'utiliser la méthode `jsonb.fromJson(config, classToInstanciate);`.
(cf. codebase pour un exemple concret).

> [!IMPORTANT]
> Il semblerait qu'il y ait quelques erreurs avec des types plus complexes de données.
> Ainsi, à voir comment on fonctionne pour ces types de données, et si on les inclut dans le fichier de configuration
> JSON ou non.

## Distances entre zones

Afin de faciliter la gestion des temps de marche entre zones, l'idée serait de créer une classe qui enregistre ces
distances entre deux zones et construit ainsi la matrice de correspondance entre ces dernières.

On peut imaginer une API du style:

```java
Calculator.setWalkingDuration(workshop1, workshop2, duration);
```

Et ensuite, il serait consommé par les clients avec une API comme:

```java
Calculator.goFrom(workshop1, workshop2);
```

Quelque chose comme ça. A voir avec les implémentations.
