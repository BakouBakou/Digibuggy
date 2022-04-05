package com.switchfully.digibuggy.books;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LendABookUnitTest {

    @Test
    public void givenNewLendABook_ThenDueDateIsTodayPlus3Weeks() {
        //GIVEN
        LendABook lendABook = new LendABook("AAAA", "BBBB");

        //THEN
        Assertions.assertThat(lendABook.getDueDate()).isEqualTo(LocalDate.now().plusWeeks(3));
    }

}
