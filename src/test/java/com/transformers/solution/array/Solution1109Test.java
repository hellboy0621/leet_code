package com.transformers.solution.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Solution1109Test {

    @Test
    void test() {
        int[][] bookings = new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int[] result = new Solution1109().corpFlightBookings(bookings, 5);
        Assertions.assertThat(result).isEqualTo(new int[]{10, 55, 45, 25, 25});
    }
}