package model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Hand2Test {

    private static Hand hand = new Hand();
    private static Hand hand2 = new Hand();
    private static Hand hand3 = new Hand();
    private static Hand hand4 = new Hand();

    private static Hand hand5 = new Hand();
    private static Hand hand6 = new Hand();
    private static Hand hand7 = new Hand();
    private static Hand hand8 = new Hand();

    @BeforeAll
    static void setTestDB() {
        CasinoDAO.setTestmode(true);
    }

    @AfterAll
    static void setTestDBOFF(){
        CasinoDAO.setTestmode(false);
    }

    @BeforeAll
    static void addCards() {
        hand.addCard(new Card(1, 1));
        hand.addCard(new Card(12, 1));
        hand.addCard(new Card(3, 1));

        hand2.addCard(new Card(1, 1));
        hand2.addCard(new Card(2, 1));
        hand2.addCard(new Card(7, 1));

        hand3.addCard(new Card(11, 1));
        hand3.addCard(new Card(12, 1));
        hand3.addCard(new Card(5, 1));

        hand4.addCard(new Card(5,1));
        hand4.addCard(new Card(6,1));
        hand4.addCard(new Card(11,1));
        hand4.addCard(new Card(7,1));

        //////

        hand5.addCardToSplittedHand(new Card(1, 1));
        hand5.addCardToSplittedHand(new Card(12, 1));
        hand5.addCardToSplittedHand(new Card(3, 1));

        hand6.addCardToSplittedHand(new Card(1, 1));
        hand6.addCardToSplittedHand(new Card(2, 1));
        hand6.addCardToSplittedHand(new Card(7, 1));

        hand7.addCardToSplittedHand(new Card(11, 1));
        hand7.addCardToSplittedHand(new Card(12, 1));
        hand7.addCardToSplittedHand(new Card(5, 1));

        hand8.addCardToSplittedHand(new Card(5,1));
        hand8.addCardToSplittedHand(new Card(6,1));
        hand8.addCardToSplittedHand(new Card(11,1));
        hand8.addCardToSplittedHand(new Card(7,1));

    }

    @ParameterizedTest
    @MethodSource("generateData")
    void TestCalculateTotal(int first, Hand second) {
        assertEquals(first, second.calculateTotal(), "The total value of Hand was not correct");

    }
    @ParameterizedTest
    @MethodSource("generateSplittedData")
    void TestCalculateSplittedTotal(int first, Hand second) {
        assertEquals(first, second.calculateSplittedTotal(), "The total value of Hand was not correct");

    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(14, hand),
                Arguments.of(20, hand2),
                Arguments.of(25, hand3),
                Arguments.of(28, hand4)
        );
    }
    static Stream<Arguments> generateSplittedData() {
        return Stream.of(
                Arguments.of(14, hand5),
                Arguments.of(20, hand6),
                Arguments.of(25, hand7),
                Arguments.of(28, hand8)
        );
    }
}