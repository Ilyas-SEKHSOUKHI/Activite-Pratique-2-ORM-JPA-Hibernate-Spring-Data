# 🚀 Activité Pratique N°2 : ORM, JPA, Hibernate & Spring Data

> **Auteur :** Ilyas SEKHSOUKHI  
> **Formation :** 2ème Année Cycle Ingénieur (2ACI) - Architecture JEE & Java Avancé

Ce projet illustre la mise en œuvre du mapping objet-relationnel (**ORM**) avec **Spring Data JPA** et **Hibernate** à travers une application Spring Boot de gestion de produits (`Product`), intégrant une base de données en mémoire **H2** ainsi qu'une interface **REST API**.

---


## 📋 Table des Matières

- [Aperçu du Projet](#-aperçu-du-projet)
- [Technologies Utilisées](#-technologies-utilisées)
- [Architecture du Projet](#-architecture-du-projet)
- [Modèle de Données & Entités](#-modèle-de-données--entités)
- [Couche Repository (Spring Data JPA)](#-couche-repository-spring-data-jpa)
- [Couche Contrôleur REST](#-couche-contrôleur-rest)
- [Configuration](#-configuration)
- [Résultats & Captures d'Écran](#-résultats--captures-décran)
- [Installation & Démarrage](#-installation--démarrage)

---

## 🎯 Aperçu du Projet

L'objectif de cette activité pratique est de :
1. **Configurer un environnement Spring Boot** avec les dépendances JPA, H2 et Web.
2. **Définir des entités JPA** annotées (`@Entity`, `@Id`, `@GeneratedValue`, etc.) avec simplification du code via **Lombok**.
3. **Exploiter la puissance de Spring Data JPA** via l'interface `JpaRepository` pour exécuter des requêtes standards et personnalisées (`@Query`, méthodes dérivées).
4. **Initialiser et tester les données** au démarrage de l'application avec un `CommandLineRunner`.
5. **Exposer des API REST** pour interagir avec la base de données et tester l'accès via le navigateur.
6. **Visualiser les données** dans la console H2 Database.

---

## 🛠 Technologies Utilisées

- **Langage** : Java 17
- **Framework** : Spring Boot
- **ORM / Persistance** : Spring Data JPA, Hibernate
- **Base de données** : H2 Database (In-Memory) *(connecteur MySQL inclus)*
- **Productivité** : Lombok (`@Getter`, `@Setter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@ToString`)
- **Gestionnaire de dépendances** : Apache Maven
- **Protocole Web / API** : Spring MVC (RESTful Web Services)

---

## 🏗 Architecture du Projet

Le projet suit une architecture multicouche standard :

```
src/main/java/org/sekhsoukhi/ormfirstproject/
├── OrmFirstProjectApplication.java   # Classe principale + CommandLineRunner d'initialisation
├── entities/
│   └── Product.java                  # Entité JPA représentant la table Product
├── repository/
│   └── ProductRepository.java        # Interface Spring Data JPA pour les accès aux données
└── web/
    └── ProductRestController.java     # Contrôleur REST pour exposer les endpoints HTTP
```

---

## 📦 Modèle de Données & Entités

### Entité `Product`
L'entité JPA `Product` modélise les produits enregistrés en base :

- `id` (`Long`) : Clé primaire auto-générée (`IDENTITY`)
- `name` (`String`) : Nom du produit
- `price` (`double`) : Prix unitaire
- `quantity` (`int`) : Quantité en stock

```java
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
```

---

## 🔍 Couche Repository (Spring Data JPA)

L'interface `ProductRepository` étend `JpaRepository<Product, Long>` et permet :
- Les opérations CRUD par défaut (`save`, `findAll`, `findById`, `deleteById`, etc.)
- La recherche par mot-clé insensible à la casse :
  ```java
  List<Product> findByNameContainsIgnoreCase(String kw);
  ```
- Des requêtes JPQL personnalisées avec paramètres :
  ```java
  @Query("select p from Product p where p.name like :x and p.price >:y")
  List<Product> search(@Param("x") String mc, @Param("y") double price);
  ```

---

## 🌐 Couche Contrôleur REST

Le contrôleur `ProductRestController` expose les endpoints REST suivants :

| Méthode | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/products` | Retourne la liste complète des produits au format JSON |
| `GET` | `/products/{id}` | Retourne les détails d'un produit spécifique par son identifiant |

---

## ⚙ Configuration

Le fichier `src/main/resources/application.properties` définit la configuration de l'application :

```properties
spring.application.name=ORM-First-Project
server.port=8087
spring.datasource.url=jdbc:h2:mem:products-db
spring.h2.console.enabled=true
```

- **Port HTTP** : `8087`
- **URL H2 Console** : `http://localhost:8087/h2-console`
- **JDBC URL** : `jdbc:h2:mem:products-db`

---

## 📸 Résultats & Captures d'Écran

### 1. Console H2 Database
Visualisation de la table `PRODUCT` et vérification des données insérées via le `CommandLineRunner` (`Computer`, `Printer`, `Smart Phone`) :

![H2 Database Console](Img/Screen%201.png)

---

### 2. Consultation de la liste des produits (`GET /products`)
Réponse JSON obtenue via le navigateur lors de l'accès à `http://localhost:8087/products` :

![Liste des Produits](Img/Screen%202.png)

---

### 3. Consultation d'un produit par ID (`GET /products/1`)
Réponse JSON obtenue pour le produit d'identifiant `1` (`http://localhost:8087/products/1`) :

![Détail du Produit par ID](Img/Screen%203.png)

---

## 🚀 Installation & Démarrage

### Prérequis
- **JDK 17** ou supérieur installé
- **Maven** installé (ou utilisation du wrapper `mvnw`)

### Étapes d'exécution

1. **Cloner le projet** :
   ```bash
   git clone https://github.com/Ilyas-SEKHSOUKHI/Activite-Pratique-2-ORM-JPA-Hibernate-Spring-Data.git
   cd ORM-First-Project
   ```

2. **Compiler le projet** :
   ```bash
   ./mvnw clean install
   ```

3. **Lancer l'application** :
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Accéder aux services** :
   - **API - Tous les produits** : [http://localhost:8087/products](http://localhost:8087/products)
   - **API - Produit par ID** : [http://localhost:8087/products/1](http://localhost:8087/products/1)
   - **Console H2** : [http://localhost:8087/h2-console](http://localhost:8087/h2-console)  
     *(JDBC URL: `jdbc:h2:mem:products-db`, Nom d'utilisateur: `sa`, sans mot de passe)*

---

## 👤 Auteur

- **Nom :** Ilyas SEKHSOUKHI
- **GitHub :** [@Ilyas-SEKHSOUKHI](https://github.com/Ilyas-SEKHSOUKHI)

