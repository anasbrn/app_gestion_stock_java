package com.stockapp;

import java.util.Scanner;

public class GestionStock {
    private static Product[] products = new Product[2];
    private static int nbProducts = 0;

    public static void printMenu() {
        System.out.println("\n--- Gestion de Stock ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("7. Quitter");
        System.out.print("Choisissez une option: ");
    }

    public static void add(Product product) {
        if (nbProducts < products.length) {
            if (product.getQuantity() < 0 || product.getPrice() < 0) {
                System.out.println("Erreur: la quantité et le prix doivent être positifs.");
                return;
            }
            products[nbProducts++] = product;
            System.out.println("Produit ajouté avec succès !");
        } else {
            System.out.println("Le stock est plein, impossible d'ajouter le produit.");
        }
    }

    public static void update(int code, String newName, int newQuantity, double newPrice) {
        if (newQuantity < 0 || newPrice < 0) {
            System.out.println("Erreur: la quantité et le prix doivent être positifs.");
            return;
        }
        for (int i = 0; i < nbProducts; i++) {
            if (products[i].getCode() == code) {
                products[i].setName(newName);
                products[i].setQuantity(newQuantity);
                products[i].setPrice(newPrice);
                System.out.println("Produit modifié avec succès !");

                return;
            }
        }
        System.out.println("Produit non trouvé !");
    }

    // Méthode pour supprimer un produit
    public static void destroy(int code) {
        for (int i = 0; i < nbProducts; i++) {
            if (products[i].getCode() == code) {
                for (int j = i; j < nbProducts - 1; j++) {
                    products[j] = products[j + 1]; // Décalage des produits
                }
                products[--nbProducts] = null; // Supprimer le dernier produit
                System.out.println("Produit supprimé avec succès !");
                return;
            }
        }
        System.out.println("Produit non trouvé !");
    }

    // Méthode pour afficher la liste des produits
    public static void show() {
        if (nbProducts == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }
        for (int i = 0; i < nbProducts; i++) {
            System.out.println(products[i].toString());
        }
    }

    // Méthode pour rechercher un produit par son nom
    public static void search(String nom) {
        for (int i = 0; i < nbProducts; i++) {
            if (products[i].getName().equalsIgnoreCase(nom)) {
                System.out.println(products[i].toString());
                return;
            }
        }
        System.out.println("Produit non trouvé !");
    }

    // Méthode pour calculer la valeur totale du stock
    public static void calculateStockValue() {
        double total = 0.0;
        for (int i = 0; i < nbProducts; i++) {
            total += products[i].calculateTotal();
        }
        System.out.println("Valeur totale du stock : " + total);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menuListNumber;
        do {
            printMenu();
            menuListNumber = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (menuListNumber) {
                case 1:
                    System.out.print("Entrez le code du produit: ");
                    int code = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    System.out.print("Entrez le nom du produit: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la quantité en stock: ");
                    int quantite = scanner.nextInt();
                    System.out.print("Entrez le prix unitaire: ");
                    double prix = scanner.nextDouble();
                    // Vérifier si le code existe déjà
                    boolean existe = false;
                    for (int i = 0; i < nbProducts; i++) {
                        if (products[i].getCode() == code) {
                            existe = true;
                            break;
                        }
                    }
                    if (existe) {
                        System.out.println("Erreur: le code produit existe déjà.");
                    } else {
                        Product nouveauProduit = new Product(code, nom, quantite, prix);
                        add(nouveauProduit);
                    }
                    break;

                case 2:
                    System.out.print("Entrez le code du produit à modifier: ");
                    int newCode = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                    System.out.print("Entrez le nouveau nom: ");
                    String newName = scanner.nextLine();
                    System.out.print("Entrez la nouvelle quantité: ");
                    int newQuantity = scanner.nextInt();
                    System.out.print("Entrez le nouveau prix: ");
                    double newPrice = scanner.nextDouble();
                    update(newCode, newName, newQuantity, newPrice);
                    break;

                case 3:
                    System.out.print("Entrez le code du produit à supprimer: ");
                    int codeDel = scanner.nextInt();
                    destroy(codeDel);
                    break;

                case 4:
                    show();
                    break;

                case 5:
                    System.out.print("Entrez le nom du produit à rechercher: ");
                    String searchName = scanner.nextLine();
                    search(searchName);
                    break;

                case 6:
                    calculateStockValue();
                    break;

                case 7:
                    System.out.println("Au revoir!");
                    break;

                default:
                    System.out.println("Option invalide, essayez de nouveau.");
            }
        } while (menuListNumber != 7);

        scanner.close();
    }
}
