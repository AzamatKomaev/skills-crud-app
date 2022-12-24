package com.azamat_komaev.crudapp;

import com.azamat_komaev.crudapp.util.ViewUtil;
import com.azamat_komaev.crudapp.view.GenericView;

public class AppRunner {
    public static void main(String[] args) {
        System.out.println("""
            Hello! It is console application to work with different entities.\s
            If you want to get full information about it you can read README.md.\s
            """);

        while (true) {
            String entityName = ViewUtil.askForEntityName();
            GenericView view = ViewUtil.getViewModelByEntityName(entityName);

            if (view == null) {
                continue;
            }

            ViewUtil.askForCommandAndRunIt(view);
        }
    }
}
