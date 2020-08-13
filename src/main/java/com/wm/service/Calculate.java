/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wm.service;

import com.wm.entity.Denominations;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author WM
 */
public class Calculate {

    public Calculate() {
    }

    final ConfigController cf = new ConfigController();

    public double calculateDenomination(List<Double> den, double value) {
        return round(den.stream(), value);
    }

    public double calculateDenomination(double value) throws FileNotFoundException {
        return round(getDenominations().stream()
                .map(Denominations::getValue), value);
    }

    public double calculateDenominationLevel(double value) throws FileNotFoundException {
        return round(getDenominations().stream()
                .filter(n -> n.getLevel().equals("NORMAL"))
                .map(Denominations::getValue), value);
    }

    public double calculateDenominationCuantity(double value) throws FileNotFoundException {

        List<Denominations> denoms = getDenominations();

        DoubleSummaryStatistics stats = denoms.stream().map(d -> d.getCuantity() * d.getValue())
                .collect(Collectors.summarizingDouble(Double::valueOf));

        Optional<Denominations> nom = denoms.stream().sorted(Comparator.comparingDouble(Denominations::getValue))
                .filter(n -> n.getCuantity() > 0)
                .reduce((n1, n2) -> {
                    double dif1 = n1.getValue() - value % n1.getValue();
                    double dif2 = n2.getValue() - value % n2.getValue();
                    return dif1 < dif2 ? n1 : n2;
                });
        if (nom.isPresent()) {
            Denominations activeNom = nom.get();
            double cashToServe = getChange(value, activeNom.getValue());
            double smallChange = cashToServe % 100;
            activeNom.setCuantity(activeNom.getCuantity() - (int) (smallChange / activeNom.getValue()));

            List<Denominations> update = new ArrayList<>();
            update.add(activeNom);
            double cashLeft = cashToServe - smallChange;
            if (cashLeft > 0) {
                denoms.sort(Comparator.comparingDouble(Denominations::getValue).reversed());
                int totalSum = (int) stats.getSum();
                for (Denominations nom1 : denoms) {
                    if (nom1.getCuantity() > 0 && cashLeft >= nom1.getValue()) {
                        int general = (int) ((nom1.getCuantity() * nom1.getValue()) * 100 / totalSum);
                        int needed = (int) (cashLeft / nom1.getValue());
                        int percent = getPercent(cashLeft, nom1, needed);
                        int limit = (int) ((cashLeft * 100) / totalSum);
                        while (percent >= limit && needed > 0
                                && needed <= nom1.getCuantity() && general > 5) {
                            needed--;
                            nom1.setCuantity(nom1.getCuantity() - 1);
                            cashLeft -= nom1.getValue();
                            totalSum -= nom1.getValue();
                            if (needed > 0) {
                                percent = getPercent(cashLeft, nom1, needed);
                            }
                        }
                        update.add(nom1);
                    }
                }
            }
            if (cashLeft == 0) {
                cf.writeToFile(update);
                return activeNom.getValue();
            }
        }
        return 1d;
    }

    private int getPercent(double cash, Denominations n, int needed) {
        return needed + needed * 100 / n.getCuantity();
    }

    private List<Denominations> getDenominations() throws FileNotFoundException {
        return cf.readFromFile();
    }

    private double round(Stream<Double> stream, double value) {
        return stream.sorted()
                .reduce((n1, n2) -> {
                    double a = n1 - value % n1;
                    double b = n2 - value % n2;
                    return a < b ? n1 : n2;
                }).orElse(0d);
    }

    public double getChange(double value, double denomination) {
        return Math.ceil(value / denomination) * denomination;
    }

}
