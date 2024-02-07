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

> [!TIPS]
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