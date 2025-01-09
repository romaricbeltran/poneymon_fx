# Poneymon_Fx (Archive Lyon 1)
Master Informatique Université Claude Bernard Lyon 1  
Dernière mise à jour : 30/09/2018

Compile sur Windows via Java JDK 8.

## Gestion de Projet et Génie Logiciel, M1, département informatique, Lyon 1

### PROJET PONEYMON_FX
Poneymon_Fx est un jeu de course où des poneys concourent sur un terrain divisé en cinq tours. Le joueur peut choisir le nombre de poneys et parier sur le gagnant. Chaque poney peut utiliser un mode "boost" pour augmenter sa vitesse, activable une seule fois. Le jeu intègre des fonctionnalités comme la mise en pause et le suivi en temps réel du parcours.

Le projet a été développé dans le cadre de l'UE “Gestion de projet & Génie logiciel” avec l'usage de Java et JavaFX. Nous avons utilisé des design patterns pour structurer le projet, notamment MVC, Observer, Composite, et Expert, afin de favoriser la modularité et la réutilisabilité. Ces choix ont permis une gestion efficace des événements en temps réel et une séparation claire des responsabilités entre la logique métier, l'affichage et le contrôle.

## Technologies et Outils Utilisés
- **Java 8**, avec JavaFX pour l'interface graphique.
- **Design Patterns** : MVC, Observer (via PropertyChangeEvent/Listener), Composite, Expert.
- **Outils** : Maven pour la gestion de projet, JUnit pour les tests unitaires, GitLab CI pour l'intégration continue.

## Fonctionnalités
- Course entre plusieurs poneys, avec possibilité de choisir le nombre de participants.
- Chaque poney dispose d'un boost activable une seule fois, doublant sa vitesse.
- Contrôle complet de la course avec boutons pour "Boost", "Pause", et "Arrêt".
- Mise à jour graphique en temps réel via les patterns MVC et Observer.
- Architecture modulaire avec un modèle métier (PoneyModel), une vue graphique (PoneyView, FieldView), et un contrôleur pour la gestion des interactions.

## Gestion de Version
La branche principale **main** est utilisée pour le développement. Initialement, nous avons commencé avec une branche "dev" pour les fonctionnalités, avant de migrer vers un flux de travail directement sur la branche "main" en pair-programming.

L'intégration continue via GitLab CI est fonctionnelle et nous avons utilisé Git pour gérer l'historique des versions et la collaboration.

## Commandes Maven

1. **Téléchargez les dépendances du projet** :
```bash
mvn clean install
```

2. **Compilez le projet** :
```bash
mvn compile
```

3. **Executer le projet** :
```bash
- mvn exec:java
```

Les sources se situent dans m1if01/src/main/java/fr/univ_lyon1/info/m1/poneymon_fx

## Patterns de Conception
Le projet suit plusieurs design patterns afin de garantir une architecture robuste et évolutive :

### 1. Modèle-Vue-Contrôleur (MVC)
Le pattern MVC sépare la logique métier de l’affichage graphique. Le modèle gère la logique (par exemple la position des poneys, la progression de la course), la vue affiche l'interface graphique et le contrôleur fait le lien entre ces deux composants.

### 2. Observer
Nous avons utilisé le pattern Observer pour gérer les mises à jour en temps réel de la vue en fonction des changements du modèle. L'implémentation de `PropertyChangeListener` et `PropertyChangeEvent` permet de notifier la vue lorsque des poneys progressent.

### 3. Composite
Le pattern Composite est utilisé dans le modèle pour regrouper et traiter plusieurs objets de même type (comme les poneys) de manière uniforme, facilitant ainsi le traitement des étapes du jeu.

### 4. Expert
Le pattern Expert a été appliqué en séparant les responsabilités entre les classes `FieldModel` et `PoneyModel`. Chaque modèle gère indépendamment son état et ses propriétés sans interférence de l’autre.

## Diagrammes
Les diagrammes UML décrivent les relations entre les classes principales, telles que `FieldModel`, `PoneyModel`, et `PoneyImprovedModel`, ainsi que la gestion des événements via `PropertyChangeListener`.
