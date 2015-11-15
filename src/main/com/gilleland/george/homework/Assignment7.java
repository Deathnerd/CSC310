package com.gilleland.george.homework;

import com.gilleland.george.exceptions.EmptyTreeException;
import com.gilleland.george.exceptions.NodeNotFoundException;
import com.gilleland.george.objects.*;
import com.gilleland.george.utils.Menu;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Wes Gilleland on 11/9/2015.
 */
public class Assignment7 extends HomeworkAssignment {

    /**
     * The menu interaction system
     */
    private Menu menu = new Menu();

    private Scanner in = new Scanner(System.in);

    /**
     * The current tree object
     */
    private BinarySearchTree<Game> tree = new BinarySearchTree<>();

    /**
     * The main entry method
     */
    @Override
    public void run() {
        Choice choice;
        while (true) {
            choice = this.menu.display(
                    new Choice("insert", "Insert a new game"),
                    new Choice("search", "Search for games"),
                    new Choice("list_games", "List games")
            );
            if (choice.getIndex() > 0) {
                this._tryMethodCall(choice.getName().toLowerCase());
            } else {
                return;
            }
        }
    }

    public void insert() {
        System.out.print("Enter the name of a game: ");
        String game_name = this.in.nextLine();
        Game temp = new Game(game_name);
        this.tree.insert(new BinarySearchTreeNode<>(temp));
        System.out.println();
    }

    public void search() {
        if (this.tree.isEmpty()) {
            System.out.println("There are currently no games in the system!");
            return;
        }

        System.out.print("Enter the game (or prefix) of a game to search for: ");
        String search = this.in.nextLine();

        try {
            this.tree.searchAll(new Game(search)).stream().forEach(g -> System.out.println(g.toString()));
        } catch (NodeNotFoundException e) {
            System.out.println("\nNo game found matching that search term!");
        } catch (EmptyTreeException e) {
            //
        }
    }

    public void list_games() {
        tree.display();
        System.out.println();
    }
}
