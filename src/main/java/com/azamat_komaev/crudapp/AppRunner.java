package com.azamat_komaev.crudapp;

import com.azamat_komaev.crudapp.view.SkillView;

import java.util.Scanner;

public class AppRunner {
    public static void main(String[] args) {
        SkillView view = new SkillView();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter commmand!");
            String command = scanner.nextLine();
            view.runCommand(command);
        }
    }
}
